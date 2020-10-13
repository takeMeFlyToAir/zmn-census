package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityEditVO
 * Description 新增小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CommunityEditVO extends BaseIdVO {

    @ApiModelProperty("街道")
    private String town;

    @ApiModelProperty("普查区")
    private String village;

    @ApiModelProperty("园办")
    private String area;

    @ApiModelProperty("小区名字")
    private String name;

    @ApiModelProperty("计划统计户数")
    private Integer planHoldCount;

    @ApiModelProperty("计划统计人数")
    private Integer planPersonCount;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("负责人姓名")
    private String chargePersonName;

}
