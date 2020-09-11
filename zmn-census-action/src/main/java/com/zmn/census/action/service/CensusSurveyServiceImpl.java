package com.zmn.census.action.service;

import com.zmn.census.action.api.CensusSurveyApi;
import com.zmn.census.action.api.HouseHoldApi;
import com.zmn.census.action.api.PersonInfoApi;
import com.zmn.census.action.api.RoomAddressApi;
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
public class CensusSurveyServiceImpl implements CensusSurveyApi {

    @Autowired
    private RoomAddressApi roomAddressApi;

    @Autowired
    private HouseHoldApi houseHoldApi;

    @Autowired
    private PersonInfoApi personInfoApi;

    @Override
    @Transactional
    public void save(CensusSurveyAddVO censusSurveyAddVO) {
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getRoomAddress(), RoomAddressEntity.class);
        HouseHoldEntity houseHoldEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getHouseHold(), HouseHoldEntity.class);
        List<PersonInfoEntity> personInfoEntityList = VoAndBeanUtils.fromVOList(censusSurveyAddVO.getPersonInfoList(), PersonInfoEntity.class);
        roomAddressApi.save(roomAddressEntity);
        houseHoldEntity.setRoomAddressId(roomAddressEntity.getId());
        houseHoldApi.save(houseHoldEntity);
        for (PersonInfoEntity personInfoEntity : personInfoEntityList) {
            personInfoEntity.setRoomAddressId(roomAddressEntity.getId());
        }
        personInfoApi.saveList(personInfoEntityList);
    }
}
