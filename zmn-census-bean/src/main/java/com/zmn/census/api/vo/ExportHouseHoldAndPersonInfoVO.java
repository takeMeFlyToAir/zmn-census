package com.zmn.census.api.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @ClassName ExportHouseHoldAndPersonInfoVO
 * Description 地址信息
 * Author zhaozhirong
 * Date 2020/9/9 17:48
 * Version 1.0
 **/
@Data
public class ExportHouseHoldAndPersonInfoVO{


    @ExcelProperty("序号")
    private Integer index;

    @ExcelProperty("唯一编号")
    @ColumnWidth(20)
    private Integer roomAddressId;

    @ExcelProperty("小区名称")
    @ColumnWidth(20)
    private String community;

    @ExcelProperty("楼栋号")
    private String buildNum;

    @ExcelProperty("单元号")
    private String unitNum;

    @ExcelProperty("楼层")
    private String floorNum;

    @ExcelProperty("房间号")
    private String roomNum;

    @ExcelProperty("户别")
    private String h1;
    @ExcelProperty("本户应登记人数,2020年10月31日晚居住本户的人数")
    private String h2Live;

    @ExcelProperty("户口在本户，2020年10月31日晚未住本户的人数")
    private String h2NoLive;
    @ExcelProperty("户2019年11月1日至2020年10月31日期间的出生人口,男人")
    private String h3Man;
    @ExcelProperty("本户2019年11月1日至2020年10月31日期间的出生人口,女人")
    private String h3Woman;
    @ExcelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,男人")
    private String h4Man;
    @ExcelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,女人")
    private String h4Woman;
    @ExcelProperty("住房类型")
    private String h5;
    @ExcelProperty("本户住房建筑面积")
    private String h6;
    @ExcelProperty("本户住房间数")
    private String h7;
    @ExcelProperty("您家中是否有6个月及以上的孕妇")
    private String h8;
    @ExcelProperty("填报人类型")
    private String h9;

    @ExcelProperty("姓名")
    private String d1;

    @ExcelProperty("与户主关系")
    private String d2;

    @ExcelProperty("公民身份号码")
    private String d3;

    @ExcelProperty("性别")
    private String d4;

    @ExcelProperty("出生年月")
    private String d5;

    @ExcelProperty("民族")
    private String d6;

    @ExcelProperty("普查时点居住地")
    private String d7;

    @ExcelProperty("普查时点居住地不在本地的省")
    private String d7Province;

    @ExcelProperty("普查时点居住地不在本地的市")
    private String d7City;

    @ExcelProperty("普查时点居住地不在本地的县")
    private String d7County;

    @ExcelProperty("户口登记地")
    private String d8;

    @ExcelProperty("户口登记地不在本地的省")
    private String d8Province;

    @ExcelProperty("户口登记地不在本地的市")
    private String d8City;

    @ExcelProperty("户口登记地不在本地的县")
    private String d8County;

    @ExcelProperty("离开户口登记地时间")
    private String d9;

    @ExcelProperty("离开户口登记地原因")
    private String d10;

    @ExcelProperty("受教育程度")
    private String d11;

    @ExcelProperty("是否识字")
    private String d12;


}
