package com.zmn.census.api.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * @ClassName CensusAddVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class CensusDownloadVO {

    @ExcelProperty("小区")
    private String community;

    @ExcelProperty("楼栋号")
    private String buildNum;

    @ExcelProperty("单元号")
    private String unitNum;

    @ExcelProperty("楼层")
    private String floorNum;

    @ExcelProperty("房间号")
    private String roomNum;

    @ExcelProperty("填报人电话")
    private String fillPersonPhone;

    @ExcelProperty("普查员姓名")
    private String examinePersonName;

}
