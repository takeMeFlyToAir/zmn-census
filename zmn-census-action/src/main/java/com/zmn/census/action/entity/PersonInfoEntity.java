package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName PersonInfoEntity
 * Description 个人信息
 * Author zhaozhirong
 * Date 2020/9/9 17:58
 * Version 1.0
 **/
@Data
@Table(name = "zmn_census_person_info")
public class PersonInfoEntity extends BaseEntity {

    private Integer roomAddressId;

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

    @ApiModelProperty("受教育程度")
    private String d11;

    @ApiModelProperty("是否识字")
    private String d12;

    private String d13;
    private String d14;
    private String d15;
    private String d15Province;
    private String d16;
    private String d16Province;
    private String d16City;
    private String d16County;
    private String d18;
    private String d20;
    private String d20Hour;
    private String d21;
    private String d22Name;
    private String d22Business;
    private String d23;
    private String d24;
    private String d25;
    private String d26;
    private String d27Year;
    private String d27Month;
    private String d28;
    private String d28BirthMan;
    private String d28BirthWoman;
    private String d28LiveMan;
    private String d28LiveWoman;
    private String d29;
    private String d29FirstBirth;
    private String d29FirstGender;
    private String d29SecondBirth;
    private String d29SecondGender;
    private String d30;
    private String d31;
}
