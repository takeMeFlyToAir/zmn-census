package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
@Table(name = "zmn_census_user")
public class UserEntity extends BaseEntity {

    private String userName;
    private String password;
    private String nickName;
    private String phone;



}
