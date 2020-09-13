package com.zmn.census.api.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RoomAddressQueryQO
 * Description 查询房间
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class RoomAddressQueryQO {

    @ApiModelProperty("街道")
    private String town;

    @ApiModelProperty("普查区")
    private String village;

    @ApiModelProperty("小区名字")
    private String name;

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;
}
