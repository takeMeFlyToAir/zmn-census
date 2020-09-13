package com.zmn.census.api.qo;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CensusSurveyQueryQO
 * Description 调查问卷
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CensusSurveyQueryQO {

    @ApiModelProperty("街道")
    private String town;

    @ApiModelProperty("普查区")
    private String village;

    @ApiModelProperty("小区名字")
    private String community;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("楼栋号")
    private String buildNum;

    @ApiModelProperty("单元号")
    private String unitNum;

    @ApiModelProperty("楼层")
    private String floorNum;

    @ApiModelProperty("房间号")
    private String roomNum;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

}
