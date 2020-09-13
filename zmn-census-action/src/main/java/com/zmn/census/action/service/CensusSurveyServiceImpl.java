package com.zmn.census.action.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.*;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.CensusSurveyAddVO;
import com.zmn.census.api.vo.CensusSurveyVO;
import com.zmn.census.api.vo.CommunityUpdateCountVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CensusSurveyServiceImpl
 * Description 保存调查问卷
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class CensusSurveyServiceImpl implements CensusSurveyService {

    @Autowired
    private RoomAddressMapper roomAddressMapper;

    @Autowired
    private RoomAddressApi roomAddressApi;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private CommunityService communityService;

    @Override
    @Transactional
    public void save(CensusSurveyAddVO censusSurveyAddVO) {
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getRoomAddress(), RoomAddressEntity.class);
        HouseHoldEntity houseHoldEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getHouseHold(), HouseHoldEntity.class);
        List<PersonInfoEntity> personInfoEntityList = VoAndBeanUtils.fromVOList(censusSurveyAddVO.getPersonInfoList(), PersonInfoEntity.class);
        roomAddressApi.save(roomAddressEntity);
        houseHoldEntity.setRoomAddressId(roomAddressEntity.getId());
        houseHoldService.save(houseHoldEntity);
        for (PersonInfoEntity personInfoEntity : personInfoEntityList) {
            personInfoEntity.setRoomAddressId(roomAddressEntity.getId());
        }
        //更新小区对应的户数和人数
        synchronized (this){
            communityService.updateCount(censusSurveyAddVO.getCommunityUpdateCountVO());
        }
        personInfoService.saveList(personInfoEntityList);
    }

    @Override
    public PagerResult<CensusSurveyVO> findPage(Pager<CensusSurveyQueryQO> pager) {
        CensusSurveyQueryQO condition = pager.getCondition();
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize());
        List<CensusSurveyVO> filterList = roomAddressMapper.findCensusSurveyVOList(condition);
        PageInfo<CensusSurveyVO> pageInfo = new PageInfo<>(filterList);
        PagerResult<CensusSurveyVO> pagerResult = new PagerResult<>(pageInfo.getList(), pageInfo.getTotal());
        return pagerResult;
    }

    @Override
    @Transactional
    public void deleteAll(Integer roomAddressId) {
        RoomAddressEntity roomAddressEntity = roomAddressMapper.selectByPrimaryKey(roomAddressId);
        //删除houseHold和personInfo
        roomAddressApi.delete(roomAddressId);
        houseHoldService.deleteByRoomAddressId(roomAddressId);
        personInfoService.deleteByRoomAddressId(roomAddressId);
        //更新小区对应的户数和人数
        synchronized (this){
            CommunityUpdateCountVO communityUpdateCountVO = new CommunityUpdateCountVO();
            communityUpdateCountVO.setId(roomAddressEntity.getCommunityId());
            communityUpdateCountVO.setAddHoldCount(-1);
            communityUpdateCountVO.setAddPersonCount(-roomAddressEntity.getPersonCount());
            communityService.updateCount(communityUpdateCountVO);
        }
    }


}
