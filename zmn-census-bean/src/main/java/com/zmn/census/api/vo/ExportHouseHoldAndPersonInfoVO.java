package com.zmn.census.api.vo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.util.Date;

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


    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private String createdDateStr;

    @ExcelProperty("普查员")
    @ColumnWidth(20)
    private String examinePersonName;

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
    @ExcelProperty("h2居住核对数")
    private Long h2LiveCompare;
    @ExcelProperty("h2居住核对数核对结果")
    private String h2LiveCompareResult;
    @ExcelProperty("户口在本户，2020年10月31日晚未住本户的人数")
    private String h2NoLive;
    @ExcelProperty("h2未居住核对数")
    private Long h2NoLiveCompare;
    @ExcelProperty("h2未居住核对数核对结果")
    private String h2NoLiveCompareResult;
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


    @ExcelProperty("承重类型")
    private String h10;
    @ExcelProperty("住房建成年代")
    private String h11;
    @ExcelProperty("住房所在建筑有无电梯")
    private String h12;
    @ExcelProperty("主要炊事燃料")
    private String h13;
    @ExcelProperty("住房内有无管道自来水")
    private String h14;
    @ExcelProperty("住房内有无厨房")
    private String h15;
    @ExcelProperty("住房内有无厕所")
    private String h16;
    @ExcelProperty("住房内有无洗澡设施")
    private String h17;
    @ExcelProperty("住房来源")
    private String h18;
    @ExcelProperty("月租房费用")
    private String h19;
    @ExcelProperty("拥有全部家用汽车的总价")
    private String h20;
    @ExcelProperty("住房所在建筑的总层数")
    private String h21;

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

    @ExcelProperty("户口登记地类型")
    private String d13;
    @ExcelProperty("是否有农村土地承包经营权")
    private String d14;
    @ExcelProperty("出生地")
    private String d15;
    @ExcelProperty("省（区、市）")
    private String d15Province;
    @ExcelProperty("五年前常住地")
    private String d16;
    @ExcelProperty("省")
    private String d16Province;
    @ExcelProperty("市")
    private String d16City;
    @ExcelProperty("县")
    private String d16County;
    @ExcelProperty("学业完成情况")
    private String d18;
    @ExcelProperty("工作情况")
    private String d20;
    @ExcelProperty("上周工作时间-小时")
    private String d20Hour;
    @ExcelProperty("工作单位或生产经营活动所属类型")
    private String d21;
    @ExcelProperty("行业:单位详细名称")
    private String d22Name;
    @ExcelProperty("行业:主要产品或主要业务")
    private String d22Business;
    @ExcelProperty("本人从事的具体工作")
    private String d23;
    @ExcelProperty("未工作原因")
    private String d24;
    @ExcelProperty("主要生活来源")
    private String d25;
    @ExcelProperty("婚姻状况")
    private String d26;
    @ExcelProperty("初婚:年")
    private String d27Year;
    @ExcelProperty("初婚:月")
    private String d27Month;
    @ExcelProperty("生育子女数")
    private String d28;
    @ExcelProperty("生过几个孩子:男")
    private String d28BirthMan;
    @ExcelProperty("生过几个孩子:女")
    private String d28BirthWoman;
    @ExcelProperty("现在存活几个孩子:男")
    private String d28LiveMan;
    @ExcelProperty("现在存活几个孩子:女")
    private String d28LiveWoman;
    @ExcelProperty("过去一年的生育状况")
    private String d29;
    @ExcelProperty("1胎生育时间:月")
    private String d29FirstBirth;
    @ExcelProperty("1胎:性别")
    private String d29FirstGender;
    @ExcelProperty("2胎生育时间:月")
    private String d29SecondBirth;
    @ExcelProperty("2胎:性别")
    private String d29SecondGender;
    @ExcelProperty("居住状况")
    private String d30;
    @ExcelProperty("身体健康状况")
    private String d31;


    /**
     * 创建日期
     */
    private Date createdDate;




    public String getCreatedDateStr() {
        if(this.createdDate != null){
            return DateUtil.formatDateTime(this.createdDate);
        }
        return "";
    }

}
