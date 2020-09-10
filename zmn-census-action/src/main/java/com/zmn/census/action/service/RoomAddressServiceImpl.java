package com.zmn.census.action.service;

import com.zmn.census.action.api.RoomAddressApi;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.common.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoomAddressServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class RoomAddressServiceImpl extends BaseService<RoomAddressEntity> implements RoomAddressApi {

    @Autowired
    private RoomAddressMapper roomAddressMapper;

}
