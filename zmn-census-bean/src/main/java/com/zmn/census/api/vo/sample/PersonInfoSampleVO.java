package com.zmn.census.api.vo.sample;

import com.zmn.census.common.core.model.BaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName PersonInfoAddVO
 * Description 个人信息
 * Author zhaozhirong
 * Date 2020/9/9 17;48
 * Version 1.0
 **/
@Data
public class PersonInfoSampleVO extends BaseIdVO {

    @ApiModelProperty("公民身份号码")
    private String d3;

    @ApiModelProperty("性别")
    private String d4;

    @ApiModelProperty("出生年月")
    private String d5;

    private String d13;
    private String d14;
    private String d15;
    private String d15Province;
    private String d16;
    private String d16Province;
    private String d16City;
    private String d16County;
    private String d18;
    private String d20;
    private String d20Hour;
    private String d21;
    private String d22Name;
    private String d22Business;
    private String d23;
    private String d24;
    private String d25;
    private String d26;
    private String d27Year;
    private String d27Month;
    private String d28;
    private String d28BirthMan;
    private String d28BirthWoman;
    private String d28LiveMan;
    private String d28LiveWoman;
    private String d29;
    private String d29FirstBirth;
    private String d29FirstGender;
    private String d29SecondBirth;
    private String d29SecondGender;
    private String d30;
    private String d31;

}
