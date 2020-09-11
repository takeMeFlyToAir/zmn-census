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
public class HouseHoldEntity  extends BaseEntity {

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
    private Integer roomAddressId;
    private String h1;
    private String h2Live;
    private String h2NoLive;
    private String h3Man;
    private String h3Woman;
    private String h4Man;
    private String h4Woman;
    private String h5;
    private String h6;
    private String h7;
    private String h8;
    private String h9;

}
