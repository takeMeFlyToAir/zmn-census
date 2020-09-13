package com.zmn.census.action.api;

import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.api.vo.CommunityEditVO;
import com.zmn.census.common.core.service.IService;

/**
 * @ClassName RoomAddressService
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 8:41
 * Version 1.0
 **/
public interface RoomAddressService extends IService<RoomAddressEntity> {

    void updateCommunity(CommunityEditVO communityEditVO);

}
