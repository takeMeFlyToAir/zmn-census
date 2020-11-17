package com.zmn.census.api.vo.sample;

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
public class RoomAddressSampleVO extends BaseIdVO {

    @ApiModelProperty("普查员姓名")
    private String examinePersonName;

    @ApiModelProperty("备注")
    private String remark;

}
