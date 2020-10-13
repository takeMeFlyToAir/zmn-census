package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName CommunityEntity
 * Description 小区信息
 * Author zhaozhirong
 * Date 2020/9/9 16:48
 * Version 1.0
 **/
@Data
@Table(name = "zmn_census_community")
public class CommunityEntity extends BaseEntity {

    private String town;

    private String village;

    private String name;

    private String area;

    @ApiModelProperty("计划统计户数")
    private Integer planHoldCount;

    @ApiModelProperty("实际统计户数")
    private Integer realHoldCount;

    @ApiModelProperty("计划统计人数")
    private Integer planPersonCount;

    @ApiModelProperty("实际统计人数")
    private Integer realPersonCount;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("负责人姓名")
    private String chargePersonName;

}
