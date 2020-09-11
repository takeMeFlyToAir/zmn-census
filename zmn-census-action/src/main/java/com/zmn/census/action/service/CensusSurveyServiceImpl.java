package com.zmn.census.action.service;

import com.zmn.census.action.api.CensusSurveyService;
import com.zmn.census.action.api.HouseHoldService;
import com.zmn.census.action.api.PersonInfoService;
import com.zmn.census.action.api.RoomAddressService;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.api.vo.CensusSurveyAddVO;
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
    private RoomAddressService roomAddressService;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private PersonInfoService personInfoService;

    @Override
    @Transactional
    public void save(CensusSurveyAddVO censusSurveyAddVO) {
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getRoomAddress(), RoomAddressEntity.class);
        HouseHoldEntity houseHoldEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getHouseHold(), HouseHoldEntity.class);
        List<PersonInfoEntity> personInfoEntityList = VoAndBeanUtils.fromVOList(censusSurveyAddVO.getPersonInfoList(), PersonInfoEntity.class);
        roomAddressService.save(roomAddressEntity);
        houseHoldEntity.setRoomAddressId(roomAddressEntity.getId());
        houseHoldService.save(houseHoldEntity);
        for (PersonInfoEntity personInfoEntity : personInfoEntityList) {
            personInfoEntity.setRoomAddressId(roomAddressEntity.getId());
        }
        personInfoService.saveList(personInfoEntityList);
    }
}
