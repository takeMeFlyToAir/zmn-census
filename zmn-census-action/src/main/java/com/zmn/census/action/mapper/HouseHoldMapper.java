package com.zmn.census.action.mapper;

import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.api.vo.HouseHoldVO;
import com.zmn.census.common.core.dao.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName HouseHoldMapper
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:50
 * Version 1.0
 **/
@Mapper
public interface HouseHoldMapper extends MyMapper<HouseHoldEntity> {

    HouseHoldVO queryByRoomAddressId(@Param("roomAddressId") Integer roomAddressId);

    void deleteByRoomAddressId(@Param("roomAddressId") Integer roomAddressId);


}
