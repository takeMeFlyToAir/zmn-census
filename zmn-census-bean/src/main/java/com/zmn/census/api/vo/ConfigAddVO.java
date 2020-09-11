package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
public class ConfigAddVO {

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("value")
    private String value;

}
