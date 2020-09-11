package com.zmn.census.api.common;

import com.zmn.census.api.vo.UserVO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LoginUser
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/11 12:10
 * Version 1.0
 **/
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public Integer id;
    private String nickName;
    public String userName;
    private String phone;

    private List<String> roles = new ArrayList<>();
    private List<String> permissions = new ArrayList<>();
    private List<String> menus = new ArrayList<>();

    public LoginUser() {
    }

    public LoginUser(Integer id, String nickName, String userName, String phone) {
        this.id = id;
        this.nickName = nickName;
        this.userName = userName;
        this.phone = phone;
    }

    public static LoginUser fromUserVO(UserVO userVO){
        return new LoginUser(userVO.getId(),userVO.getNickName(),userVO.getUserName(),userVO.getPhone());
    }

}

