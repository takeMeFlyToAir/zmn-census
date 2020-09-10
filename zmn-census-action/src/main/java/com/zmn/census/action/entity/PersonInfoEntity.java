package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName HouseHoldEntity
 * Description 住户信息
 * Author zhaozhirong
 * Date 2020/9/9 17:58
 * Version 1.0
 **/
@Data
@Table(name = "zmn_census_house_hold")
public class PersonInfoEntity extends BaseEntity {

}
