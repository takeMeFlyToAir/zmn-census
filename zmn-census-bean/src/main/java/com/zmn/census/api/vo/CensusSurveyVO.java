package com.zmn.census.api.vo;

import cn.hutool.core.collection.CollUtil;
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
public class CensusSurveyVO {

    private Integer id;

    @ApiModelProperty("房间地址信息")
    private RoomAddressVO roomAddress;

    @ApiModelProperty("住户信息")
    private HouseHoldVO houseHold;

    @ApiModelProperty("个人信息")
    private List<PersonInfoVO> personInfoList;


    @ApiModelProperty("住户信息列表")
    private List<HouseHoldVO> houseHoldList;

    public List<HouseHoldVO> getHouseHoldList(){
        return CollUtil.newArrayList(this.getHouseHold());
    }

}
