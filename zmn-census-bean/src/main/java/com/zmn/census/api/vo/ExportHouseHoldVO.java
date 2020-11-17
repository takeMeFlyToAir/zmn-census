package com.zmn.census.api.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



/**
 * @ClassName ExportHouseHoldDataVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class ExportHouseHoldVO {

    @ExcelProperty("序号")
    private int index;


    @ExcelProperty("唯一编号")
    @ColumnWidth(20)
    private Integer roomAddressId;


    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private String createdDateStr;


    @ExcelProperty("普查员")
    @ColumnWidth(20)
    private String examinePersonName;


    @ExcelProperty("园办")
    @ColumnWidth(20)
    private String area;

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

    @ExcelProperty("m3")
    @ColumnWidth(20)
    private String m3;

    @ExcelProperty("m4")
    private String m4;

    @ExcelProperty("m5")
    private Integer m5;

    @ExcelProperty("m6")
    private long m6;

    @ExcelProperty("m7")
    private long m7;

    @ExcelProperty("m8")
    private long m8;

    @ExcelProperty("m9")
    private Integer m9;

    @ExcelProperty("m10")
    private long m10;

    @ExcelProperty("m11")
    private long m11;

    @ExcelProperty("m12")
    private Integer m12;

    @ExcelProperty("m13")
    private Integer m13;

    @ExcelProperty("m16")
    @ColumnWidth(15)
    private String m16;


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


    public Integer getBuildNumInt() {
        Integer buildNumInt = 0;
        try {
            buildNumInt = Integer.parseInt(this.buildNum);
        }catch (Exception e){
            buildNumInt = 0;
        }
        return buildNumInt;
    }

    public Integer getUnitNumInt() {
        Integer unitNumInt = 0;
        try {
            unitNumInt = Integer.parseInt(this.unitNum);
        }catch (Exception e){
            unitNumInt = 0;
        }
        return unitNumInt;
    }

    public Integer getFloorNumInt() {
        Integer floorNumInt = 0;
        try {
            floorNumInt = Integer.parseInt(this.floorNum);
        }catch (Exception e){
            floorNumInt = 0;
        }
        return floorNumInt;
    }

    public Integer getRoomNumInt() {
        Integer roomNumInt = 0;
        try {
            roomNumInt = Integer.parseInt(this.roomNum);
        }catch (Exception e){
            roomNumInt = 0;
        }
        return roomNumInt;
    }
}