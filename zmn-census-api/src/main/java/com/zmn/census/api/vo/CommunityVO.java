package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommunityVO
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
@Data
public class CommunityVO extends BaseIdVO {

    @ApiModelProperty("小区名字")
    private String name;

}
