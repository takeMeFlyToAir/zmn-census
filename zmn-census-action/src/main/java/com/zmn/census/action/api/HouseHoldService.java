package com.zmn.census.action.api;

import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.common.core.service.IService;

/**
 * @ClassName HouseHoldAddApi
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 8:41
 * Version 1.0
 **/
public interface HouseHoldService extends IService<HouseHoldEntity> {

    void deleteByRoomAddressId(Integer roomAddressId);

}
