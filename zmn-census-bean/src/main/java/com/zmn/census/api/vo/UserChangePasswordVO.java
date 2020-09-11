package com.zmn.census.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by zhaozhirong on 2019/11/25.
 */

@Data
public class UserChangePasswordVO {

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("新密码")
    private String newPassword;
}
