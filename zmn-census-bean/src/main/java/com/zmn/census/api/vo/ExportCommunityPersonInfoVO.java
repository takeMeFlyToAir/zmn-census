package com.zmn.census.api.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;


/**
 * @ClassName ExportCommunityPersonInfoVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class ExportCommunityPersonInfoVO {


    @ExcelProperty("园办")
    @ColumnWidth(20)
    private String area;

    @ExcelProperty("负责人")
    @ColumnWidth(20)
    private String chargePersonName;

    @ExcelProperty("小区名称")
    @ColumnWidth(20)
    private String community;

    @ExcelProperty("实际户数")
    @ColumnWidth(15)
    private String realHoldCount;

    @ExcelProperty("实际人数")
    @ColumnWidth(15)
    private String realPersonCount;

}
