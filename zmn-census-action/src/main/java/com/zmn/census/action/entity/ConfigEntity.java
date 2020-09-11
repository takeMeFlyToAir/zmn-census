package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
@Table(name = "zmn_census_config")
public class ConfigEntity extends BaseEntity {

    @Column(name = "`key`")
    private String key;
    private String value;

}
