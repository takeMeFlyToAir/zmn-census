package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName RoomAddressEntity
 * Description 地址信息
 * Author zhaozhirong
 * Date 2020/9/9 17:48
 * Version 1.0
 **/
@Data
public class RoomAddressEditVO extends BaseIdVO {

    @ApiModelProperty("楼栋号")
    private String buildNum;

    @ApiModelProperty("单元号")
    private String unitNum;

    @ApiModelProperty("楼层")
    private String floorNum;

    @ApiModelProperty("房间号")
    private String roomNum;

    @ApiModelProperty("填报人电话")
    private String fillPersonPhone;

    @ApiModelProperty("普查员姓名")
    private String examinePersonName;

}
