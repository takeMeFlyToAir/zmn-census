package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CommunityVO extends BaseVO {

    @ApiModelProperty("街道")
    private String town;

    @ApiModelProperty("普查区")
    private String village;

    @ApiModelProperty("园办")
    private String area;

    @ApiModelProperty("小区名字")
    private String name;

    @ApiModelProperty("计划统计户数")
    private String planHoldCount;

    @ApiModelProperty("实际统计户数")
    private String realHoldCount;

    @ApiModelProperty("计划统计人数")
    private String planPersonCount;

    @ApiModelProperty("实际统计人数")
    private String realPersonCount;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("负责人姓名")
    private String chargePersonName;

}
