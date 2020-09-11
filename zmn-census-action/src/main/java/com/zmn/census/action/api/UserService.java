package com.zmn.census.action.api;


import com.zmn.census.action.entity.UserEntity;
import com.zmn.census.api.qo.UserQO;
import com.zmn.census.api.vo.UserAddVO;
import com.zmn.census.api.vo.UserChangePasswordVO;
import com.zmn.census.api.vo.UserEditVO;
import com.zmn.census.api.vo.UserVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.core.service.IService;

/**
 * Created by zhaozhirong on 2019/11/25.
 */
public interface UserService extends IService<UserEntity> {

    UserVO add(UserAddVO userAddVO);

    void updateNotNull(UserEditVO userEditVO);

    UserVO getById(Integer id);

    PagerResult<UserVO> findPage(Pager<UserQO> pager);

    UserVO getByUserName(String userName);

    UserVO getByPhone(String phone);

    UserVO login(String userName,String password);


    void changePassword(UserChangePasswordVO userChangePasswordVO);

}
