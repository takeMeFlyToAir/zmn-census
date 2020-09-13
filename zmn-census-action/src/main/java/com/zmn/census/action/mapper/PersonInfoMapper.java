package com.zmn.census.action.mapper;

import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.api.vo.PersonInfoVO;
import com.zmn.census.common.core.dao.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName PersonInfoMapper
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:50
 * Version 1.0
 **/
@Mapper
public interface PersonInfoMapper extends MyMapper<PersonInfoEntity> {

    List<PersonInfoVO> queryByRoomAddressId(@Param("roomAddressId") Integer roomAddressId);

    void deleteByRoomAddressId(@Param("roomAddressId") Integer roomAddressId);

}
