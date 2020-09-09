package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName CommunityEntity
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:48
 * Version 1.0
 **/
@Data
@Table(name = "zmn_census_community")
public class CommunityEntity extends BaseEntity {

    private String name;

}
