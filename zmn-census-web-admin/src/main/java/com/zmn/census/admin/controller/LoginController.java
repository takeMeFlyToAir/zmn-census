package com.zmn.census.admin.controller;

import cn.hutool.core.collection.CollUtil;
import com.zmn.census.admin.config.shiro.UserUtil;
import com.zmn.census.api.common.LoginUser;
import com.zmn.census.common.core.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 登录相关
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Api(tags = "用户登录")
public class LoginController {


	/**
	 * 登录
	 */
	@PostMapping("/auth")
	@ApiOperation("登录")
	public CommonResult authLogin(
			@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password
	) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			subject.login(token);
			return CommonResult.success(subject.getSession().getId());
		} catch (IncorrectCredentialsException e) {
			return CommonResult.failed("用户名或密码错误");
		} catch (AuthenticationException e) {
			return CommonResult.failed("用户名或密码错误");
		} catch (Exception e) {
			log.error("登录出错", e);
			e.printStackTrace();
		}
		return CommonResult.failed("登录出错");
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@ApiOperation("获取登录用户信息")
	@GetMapping("/getInfo")
	public CommonResult getInfo() {
		LoginUser currentUser = UserUtil.getCurrentUser();
		ArrayList<String> roles = CollUtil.newArrayList("admin");
		currentUser.setRoles(roles);
		return CommonResult.success(UserUtil.getCurrentUser());
	}

	/**
	 * 登出
	 */
	@ApiOperation("登出")
	@GetMapping("/logout")
	public CommonResult logout() {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return CommonResult.success();
	}
}
