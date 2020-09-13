package com.zmn.census.action.service;

import com.zmn.census.action.api.HouseHoldService;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.mapper.HouseHoldMapper;
import com.zmn.census.common.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName HouseHoldServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class HouseHoldServiceImpl extends BaseService<HouseHoldEntity> implements HouseHoldService {

    @Autowired
    private HouseHoldMapper houseHoldMapper;

    @Override
    public void deleteByRoomAddressId(Integer roomAddressId) {

    }
}
