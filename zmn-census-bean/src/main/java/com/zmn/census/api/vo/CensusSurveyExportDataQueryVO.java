package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CensusSurveyExportDataVO
 * Description 数据导出功能
 * Author zhaozhirong
 * Date 2020/9/9 17;48
 * Version 1.0
 **/
@Data
public class CensusSurveyExportDataQueryVO {


    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("小区名字")
    private String community;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

}
