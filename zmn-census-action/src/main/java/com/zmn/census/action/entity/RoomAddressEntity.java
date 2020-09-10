package com.zmn.census.action.entity;

import com.zmn.census.common.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName RoomAddressEntity
 * Description 地址信息
 * Author zhaozhirong
 * Date 2020/9/9 17:48
 * Version 1.0
 **/
@Data
@Table(name = "zmn_census_room_address")
public class RoomAddressEntity  extends BaseEntity {

    /**
     * 省
     */
    private String province;

    /**
     * 市或者区
     * 比如省是北京的话，这里就是北京
     * 如果陕西的话，这里就是西安
     */
    private String city;

    /**
     * 县城，或者区
     */
    private String county;

    /**
     * 乡镇，街道
     */
    private String town;

    /**
     * 类似村级单位
     */
    private String village;

    /**
     * 小区
     */
    private String community;

    /**
     * 户编号
     */
    private String hubianhao;

    /**
     * 楼栋号
     */
    private String buildNum;

    /**
     * 单元号
     */
    private String unitNum;

    /**
     * 楼层
     */
    private String floorNum;

    /**
     * 房间号
     */
    private String roomNum;

    /**
     * 填报人电话
     */
    private String fillPersonPhone;


}
