package com.zmn.census.admin.config.shiro;

import com.zmn.census.api.common.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by zhaozhirong on 2020/3/8.
 */
public class UserUtil {
    public static LoginUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null) {
            return (LoginUser) subject.getPrincipal();
        }else {
            return null;
        }
    }
}
