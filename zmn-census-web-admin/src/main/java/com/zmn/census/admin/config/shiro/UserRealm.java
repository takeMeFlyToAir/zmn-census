package com.zmn.census.admin.config.shiro;

import com.zmn.census.action.api.UserApi;
import com.zmn.census.api.common.LoginUser;
import com.zmn.census.api.vo.UserVO;
import com.zmn.census.common.login.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserApi userApi;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户的权限
		List<String> permissionList = (List<String>) session.getAttribute(Constant.SESSION_USER_PERMISSION);
		//为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(permissionList);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginController.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("-------身份认证方法--------");
		String userName = (String) authenticationToken.getPrincipal();
		String password = new String((char[]) authenticationToken.getCredentials());
		log.info("===============login info===userName is {}, password is {}",userName,password);
		UserVO userVO = userApi.login(userName,password);
		if (userVO == null) {
			throw new UnknownAccountException();
		}

		//将用户信息放入session中
		LoginUser loginUser = new LoginUser(userVO.getId(),userVO.getNickName(),userVO.getUserName(),userVO.getPhone());
		SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_USER_INFO, loginUser);
		return new SimpleAuthenticationInfo(loginUser, password,getName());
	}
}
