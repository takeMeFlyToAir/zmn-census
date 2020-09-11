package com.zmn.census.api.vo;

import com.zmn.census.common.core.model.BaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
public class UserEditVO extends BaseIdVO {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("电话号码")
    private String phone;

}
