package com.zmn.census.api.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityVO
 * Description 新增小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CommunityQueryQO {


    @ApiModelProperty("街道")
    private String town;

    @ApiModelProperty("园办")
    private String area;

    @ApiModelProperty("普查区")
    private String village;

    @ApiModelProperty("小区名字")
    private String name;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;
}
