package com.zmn.census.api.vo;

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

    private RoomAddressAddVO roomAddress;

    private HouseHoldAddVO houseHold;

    private List<PersonInfoAddVO> personInfoList;

}
