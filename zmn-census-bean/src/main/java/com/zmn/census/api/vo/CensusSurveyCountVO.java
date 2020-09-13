package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CensusSurveyCountVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class CensusSurveyCountVO {

    @ApiModelProperty("总小区数")
    private String time;

    @ApiModelProperty("每日问卷数")
    private Integer surveyCount;

    @ApiModelProperty("每日个人数")
    private Integer personCount;


}
