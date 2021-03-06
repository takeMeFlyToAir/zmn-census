package com.zmn.census.admin.controller;

import cn.hutool.crypto.SecureUtil;
import com.zmn.census.action.api.UserService;
import com.zmn.census.admin.config.shiro.UserUtil;
import com.zmn.census.api.common.LoginUser;
import com.zmn.census.api.qo.UserQO;
import com.zmn.census.api.vo.UserAddVO;
import com.zmn.census.api.vo.UserChangePasswordVO;
import com.zmn.census.api.vo.UserEditVO;
import com.zmn.census.api.vo.UserVO;
import com.zmn.census.common.core.model.BaseVO;
import com.zmn.census.common.core.result.CommonResult;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.login.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by zhaozhirong on 2019/11/25.
 */
@Slf4j
@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("分页查询用户")
    @GetMapping(value = "/findPage")
    public CommonResult<PagerResult<UserVO>> findPage(Pager<UserQO> pager, UserQO userQO){
        try {
            pager.setCondition(userQO);
            return CommonResult.success(userService.findPage(pager));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }


    @ApiOperation("查询所有用户")
    @GetMapping(value = "/findList")
    public CommonResult<List<UserVO>> findList(UserQO userQO){
        try {
            return CommonResult.success(userService.findList(userQO));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }


    @ApiOperation("添加用户")
    @PostMapping(value = "/save")
    public CommonResult add(UserAddVO userAddVO){
        try {
            UserVO userUserNameVO = userService.getByUserName(userAddVO.getUserName());
            if(!validate(userUserNameVO, userAddVO.getId())){
                return CommonResult.failed("此用户名已存在");
            }
            UserVO userPhoneVO = userService.getByPhone(userAddVO.getPhone());
            if(!validate(userPhoneVO, userAddVO.getId())){
                return CommonResult.failed("此电话号码已存在");
            }
            userAddVO.setPassword(SecureUtil.md5(Constant.DEFAULT_PASSWORD));
            userService.add(userAddVO);
            return CommonResult.success();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除用户")
    @PostMapping(value = "/delete")
    public CommonResult delete(Integer id){
        try {
            userService.delete(id);
            return CommonResult.success();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    @ApiOperation("编辑用户")
    @PostMapping(value = "/edit")
    public CommonResult edit(UserEditVO userEditVO){
        try {
            UserVO userUserNameVO = userService.getByUserName(userEditVO.getUserName());
            if(!validate(userUserNameVO, userEditVO.getId())){
                return CommonResult.failed("此用户名已存在");
            }
            UserVO userPhoneVO = userService.getByPhone(userEditVO.getPhone());
            if(!validate(userPhoneVO, userEditVO.getId())){
                return CommonResult.failed("此电话号码已存在");
            }
            userService.updateNotNull(userEditVO);
            return CommonResult.success();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    @ApiOperation("新增和编辑校验手机号码的唯一性")
    @GetMapping(value = "/existPhone")
    public CommonResult existPhone(Integer id,String phone){
        try {
            UserVO userVO = userService.getByPhone(phone);
            return CommonResult.success(!validate(userVO,id));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    @ApiOperation("新增和编辑校验用户名的唯一性")
    @GetMapping(value = "/existUserName")
    public CommonResult existUserName(Integer id,String userName){
        try {
            UserVO userVO = userService.getByUserName(userName);
            return CommonResult.success(!validate(userVO,id));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    private Boolean validate(BaseVO baseVO, Integer id){
        return (baseVO == null) ||
                (baseVO !=null && id != null && baseVO.getId().toString().equals(id.toString()));
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/changePassword")
    public CommonResult changePassword(UserChangePasswordVO userChangePasswordVO){
        try {
            LoginUser currentUser = UserUtil.getCurrentUser();
            UserVO userVO = userService.getById(currentUser.getId());
            if(!SecureUtil.md5(userChangePasswordVO.getPassword()).equals(userVO.getPassword())){
                return CommonResult.failed("原密码输入有误");
            }
            userChangePasswordVO.setUserId(currentUser.getId());
            userChangePasswordVO.setNewPassword(SecureUtil.md5(userChangePasswordVO.getNewPassword()));
            userService.changePassword(userChangePasswordVO);
            return CommonResult.success();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }
}
