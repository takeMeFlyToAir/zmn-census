package com.zmn.census.action.service;

import com.zmn.census.action.api.CensusSurveyApi;
import com.zmn.census.action.api.CommunityApi;
import com.zmn.census.action.api.RoomAddressApi;
import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.CommunityMapper;
import com.zmn.census.api.qo.CommunityQueryQO;
import com.zmn.census.api.vo.CensusSurveyAddVO;
import com.zmn.census.api.vo.CommunityAddVO;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.core.service.BaseService;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    @Transactional
    public void save(CensusSurveyAddVO censusSurveyAddVO) {
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getRoomAddress(), RoomAddressEntity.class);
        roomAddressApi.save(roomAddressEntity);
    }
}
