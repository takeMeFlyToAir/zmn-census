package com.zmn.census.action.mapper;

import com.zmn.census.action.entity.UserEntity;
import com.zmn.census.common.core.dao.MyMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhaozhirong on 2019/11/25.
 */
@Mapper
public interface UserMapper extends MyMapper<UserEntity> {

}
