package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityVO
 * Description 新增住户信息
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class HouseHoldVO extends BaseVO {

    private Integer roomAddressId;


    /**
     * h1: "",//户别
     * h2Live: "1",//本户应登记人数,2020年10月31日晚居住本户的人数 _____人
     * h2NoLive: "0",//户口在本户，2020年10月31日晚未住本户的人数 _____人
     * h3Man: "0",//本户2019年11月1日至2020年10月31日期间的出生人口,男人
     * h3Woman: "0",//本户2019年11月1日至2020年10月31日期间的出生人口,女人
     * h4Man: "0",//本户2019年11月1日至2020年10月31日期间的死亡人口,男人
     * h4Woman: "0",//本户2019年11月1日至2020年10月31日期间的死亡人口,女人
     * h5: "",//住房类型,h5List,（选择3-6的，跳至个人项目。）
     * h6: "1",//本户住房建筑面积
     * h7: "1",//本户住房间数
     * h8: "1",//您家中是否有6个月及以上的孕妇（是/否）
     * h9: "1",//填报人类型：住户，租户
     */

    @ApiModelProperty("户别")
    private String h1;
    @ApiModelProperty("本户应登记人数,2020年10月31日晚居住本户的人数")
    private String h2Live;

    @ApiModelProperty("户口在本户，2020年10月31日晚未住本户的人数")
    private String h2NoLive;
    @ApiModelProperty("户2019年11月1日至2020年10月31日期间的出生人口,男人")
    private String h3Man;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的出生人口,女人")
    private String h3Woman;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,男人")
    private String h4Man;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,女人")
    private String h4Woman;
    @ApiModelProperty("住房类型,h5List,（选择3-6的，跳至个人项目。）")
    private String h5;
    @ApiModelProperty("本户住房建筑面积")
    private String h6;
    @ApiModelProperty("本户住房间数")
    private String h7;
    @ApiModelProperty("您家中是否有6个月及以上的孕妇（是/否）")
    private String h8;
    @ApiModelProperty("填报人类型：住户，租户")
    private String h9;

    private String h10;
    private String h11;
    private String h12;
    private String h13;
    private String h14;
    private String h15;
    private String h16;
    private String h17;
    private String h18;
    private String h19;
    private String h20;
    private String h21;

}
