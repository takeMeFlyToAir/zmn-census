package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName ExportHouseHoldDataPersonVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class ExportHouseHoldDataPersonVO {

    @ApiModelProperty("普查时点居住地")
    private String d7;

    @ApiModelProperty("户口登记地")
    private String d8;

}
