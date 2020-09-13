package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseVO;
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
public class RoomAddressVO extends BaseVO {

    @ApiModelProperty("负责人id")
    private Integer chargePersonId;

    @ApiModelProperty("负责人姓名")
    private String chargePersonName;

    @ApiModelProperty("实际填写个人数")
    private Integer personCount;

    @ApiModelProperty("小区id")
    private Integer communityId;

    @ApiModelProperty("省")
    private String province;

    /**
     * 市或者区
     * 比如省是北京的话，这里就是北京
     * 如果陕西的话，这里就是西安
     */
    @ApiModelProperty("市或者区")
    private String city;

    @ApiModelProperty("县城，或者区")
    private String county;

    @ApiModelProperty("乡镇，街道")
    private String town;

    @ApiModelProperty("类似村级单位")
    private String village;

    @ApiModelProperty("小区")
    private String community;

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

    @ApiModelProperty("备注")
    private String remark;

}
