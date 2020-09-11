package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import com.zmn.census.common.core.model.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
public class ConfigVO extends BaseIdVO {

    @ApiModelProperty("key")
    private String key;

    @ApiModelProperty("value")
    private String value;

}
