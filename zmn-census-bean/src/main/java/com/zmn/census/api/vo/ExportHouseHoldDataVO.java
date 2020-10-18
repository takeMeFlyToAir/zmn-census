package com.zmn.census.api.vo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.zmn.census.api.common.CensusConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @ClassName ExportHouseHoldDataVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/10 10:05
 * Version 1.0
 **/
@Data
public class ExportHouseHoldDataVO {

    /**
     * 创建日期
     */
    private Date createdDate;

    private Integer roomAddressId;

    private String community;

    @ApiModelProperty("楼栋号")
    private String buildNum;

    @ApiModelProperty("单元号")
    private String unitNum;

    @ApiModelProperty("楼层")
    private String floorNum;

    private String roomNum;

    private String fillPersonPhone;

    @ApiModelProperty("本户应登记人数,2020年10月31日晚居住本户的人数")
    private Integer h2Live;
    @ApiModelProperty("户口在本户，2020年10月31日晚未住本户的人数")
    private Integer h2NoLive;
    @ApiModelProperty("户2019年11月1日至2020年10月31日期间的出生人口,男人")
    private Integer h3Man;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的出生人口,女人")
    private Integer h3Woman;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,男人")
    private Integer h4Man;
    @ApiModelProperty("本户2019年11月1日至2020年10月31日期间的死亡人口,女人")
    private Integer h4Woman;

    @ApiModelProperty("姓名")
    private String d1;

    @ApiModelProperty("与户主关系")
    private String d2;

    @ApiModelProperty("普查时点居住地")
    private String d7;

    @ApiModelProperty("户口登记地")
    private String d8;

    private List<ExportHouseHoldDataPersonVO> holdDataPersonVOList;


    public String getM3(){
        return StrUtil.join(CensusConstant.JOIN_CHAR,this.buildNum,this.unitNum,this.roomNum);
    }

    public Integer getH3(){
        return NumberUtil.add(h3Man+h3Woman).intValue();
    }

    public Integer getH4(){
        return NumberUtil.add(h4Man+h4Woman).intValue();
    }

    public String getD7AndD8(){
        return StrUtil.join(CensusConstant.JOIN_CHAR,d7,d8);
    }

    public String getCreatedDateStr() {
        if(this.createdDate != null){
            return DateUtil.formatDateTime(this.createdDate);
        }
        return "";
    }
}
