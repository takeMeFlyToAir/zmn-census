package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PersonInfoAddVO
 * Description 个人信息
 * Author zhaozhirong
 * Date 2020/9/9 17;48
 * Version 1.0
 **/
@Data
public class PersonInfoAddVO {

    @ApiModelProperty("姓名")
    private String d1;

    @ApiModelProperty("与户主关系")
    private String d2;

    @ApiModelProperty("公民身份号码")
    private String d3;

    @ApiModelProperty("性别")
    private String d4;

    @ApiModelProperty("出生年月")
    private String d5;

    @ApiModelProperty("民族")
    private String d6;

    @ApiModelProperty("普查时点居住地")
    private String d7;

    @ApiModelProperty("普查时点居住地不在本地的省")
    private String d7Province;

    @ApiModelProperty("普查时点居住地不在本地的市")
    private String d7City;

    @ApiModelProperty("普查时点居住地不在本地的县")
    private String d7County;

    @ApiModelProperty("户口登记地")
    private String d8;

    @ApiModelProperty("户口登记地不在本地的省")
    private String d8Province;

    @ApiModelProperty("户口登记地不在本地的市")
    private String d8City;

    @ApiModelProperty("户口登记地不在本地的县")
    private String d8County;

    @ApiModelProperty("离开户口登记地时间")
    private String d9;

    @ApiModelProperty("离开户口登记地原因")
    private String d10;

    @ApiModelProperty("是否识字")
    private String d11;

    @ApiModelProperty("受教育程度")
    private String d12; 

}
