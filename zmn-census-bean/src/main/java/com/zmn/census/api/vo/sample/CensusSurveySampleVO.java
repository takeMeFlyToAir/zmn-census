package com.zmn.census.api.vo.sample;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CensusAddVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class CensusSurveySampleVO {

    @ApiModelProperty("房间地址信息")
    private RoomAddressSampleVO roomAddress;

    @ApiModelProperty("住户信息")
    private HouseHoldSampleVO houseHold;

    @ApiModelProperty("个人信息")
    private List<PersonInfoSampleVO> personInfoList;

}
