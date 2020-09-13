package com.zmn.census.action.service;

import com.zmn.census.action.api.RoomAddressService;
import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.api.vo.CommunityEditVO;
import com.zmn.census.common.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName RoomAddressServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class RoomAddressServiceImpl extends BaseService<RoomAddressEntity> implements RoomAddressService {

    @Autowired
    private RoomAddressMapper roomAddressMapper;


    @Override
    public void updateCommunity(CommunityEditVO communityEditVO) {
        Example example = new Example(CommunityEntity.class);
        example.createCriteria().andEqualTo("deleted", 0)
                .andEqualTo("communityId",communityEditVO.getId());

        RoomAddressEntity roomAddressEntity = new RoomAddressEntity();
        roomAddressEntity.setTown(communityEditVO.getTown());
        roomAddressEntity.setVillage(communityEditVO.getVillage());
        roomAddressEntity.setCommunity(communityEditVO.getName());
        roomAddressEntity.setChargePersonId(communityEditVO.getChargePersonId());
        roomAddressEntity.setChargePersonName(communityEditVO.getChargePersonName());
        roomAddressMapper.updateByExampleSelective(roomAddressEntity,example);
    }
}
