package com.zmn.census.api.vo;

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
public class CensusSurveyAddVO {

    @ApiModelProperty("房间地址信息")
    private RoomAddressAddVO roomAddress;

    @ApiModelProperty("住户信息")
    private HouseHoldAddVO houseHold;

    @ApiModelProperty("个人信息")
    private List<PersonInfoAddVO> personInfoList;

}
