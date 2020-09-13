package com.zmn.census.action.api;

import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.common.core.service.IService;

/**
 * @ClassName PersonInfoService
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 8:41
 * Version 1.0
 **/
public interface PersonInfoService extends IService<PersonInfoEntity> {

    void deleteByRoomAddressId(Integer roomAddressId);

}
