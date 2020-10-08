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

    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private String createdDateStr;

}