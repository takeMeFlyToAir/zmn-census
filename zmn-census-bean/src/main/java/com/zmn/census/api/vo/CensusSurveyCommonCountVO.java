package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CensusSurveyCommonCountVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class CensusSurveyCommonCountVO {

    @ApiModelProperty("总小区数")
    private Integer allCommunityCount;

    @ApiModelProperty("总问卷数")
    private Integer allSurveyCount;

    @ApiModelProperty("总人数")
    private Integer allPersonCount;

    @ApiModelProperty("昨日问卷数")
    private Integer yesterdaySurveyCount;

    @ApiModelProperty("今日问卷数")
    private Integer todaySurveyCount;


}
