package com.zmn.census.action.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.UserService;
import com.zmn.census.action.entity.UserEntity;
import com.zmn.census.action.mapper.UserMapper;
import com.zmn.census.api.qo.UserQO;
import com.zmn.census.api.vo.UserAddVO;
import com.zmn.census.api.vo.UserChangePasswordVO;
import com.zmn.census.api.vo.UserEditVO;
import com.zmn.census.api.vo.UserVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.core.service.BaseService;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by zhaozhirong on 2019/11/25.
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserVO add(UserAddVO userAddVO) {
        UserEntity userEntity = VoAndBeanUtils.fromVO(userAddVO, UserEntity.class);
        this.save(userEntity);
        return VoAndBeanUtils.toVO(userEntity,UserVO.class);
    }

    @Override
    public void updateNotNull(UserEditVO userEditVO) {
        this.updateNotNull(VoAndBeanUtils.fromVO(userEditVO, UserEntity.class));
    }

    @Override
    public UserVO getById(Integer id) {
        return VoAndBeanUtils.toVO(userMapper.selectByPrimaryKey(id), UserVO.class);
    }


    @Override
    public PagerResult<UserVO> findPage(Pager<UserQO> pager) {
        UserQO condition = pager.getCondition();
        Example example = new Example(UserEntity.class);
        example.setOrderByClause(" created_date desc ");
        Example.Criteria criteria = example.createCriteria().andEqualTo("deleted", 0);

        if(StrUtil.isNotEmpty(condition.getUserName())){
            criteria.andLike("userName", "%"+condition.getUserName()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getNickName())){
            criteria.andLike("nickName", "%"+condition.getNickName()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getPhone())){
            criteria.andLike("phone", "%"+condition.getPhone()+"%");
        }
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize());
        List<UserEntity> filterList = this.selectByExample(example);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(filterList);
        PagerResult<UserEntity> pagerResult = new PagerResult<>(pageInfo.getList(), pageInfo.getTotal());
        return PagerResult.toVOPagerResult(pagerResult, UserVO.class);
    }

    @Override
    public List<UserVO> findList(UserQO userQO) {
        UserQO condition = userQO;
        Example example = new Example(UserEntity.class);
        example.setOrderByClause(" created_date desc ");
        Example.Criteria criteria = example.createCriteria().andEqualTo("deleted", 0);

        if(StrUtil.isNotEmpty(condition.getUserName())){
            criteria.andLike("userName", "%"+condition.getUserName()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getNickName())){
            criteria.andLike("nickName", "%"+condition.getNickName()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getPhone())){
            criteria.andLike("phone", "%"+condition.getPhone()+"%");
        }
        List<UserEntity> filterList = this.selectByExample(example);
        return VoAndBeanUtils.toVOList(filterList,UserVO.class);

    }

    @Override
    public UserVO getByUserName(String userName) {
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("deleted", 0).andEqualTo("userName",userName);
        return VoAndBeanUtils.toVO(userMapper.selectOneByExample(example), UserVO.class);

    }

    @Override
    public UserVO getByPhone(String phone) {
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("deleted", 0).andEqualTo("phone",phone);
        return VoAndBeanUtils.toVO(userMapper.selectOneByExample(example), UserVO.class);
    }

    @Override
    public UserVO login(String userName, String password) {
        UserVO userVO = this.getByUserName(userName);
        if(userVO == null){
            return null;
        }
        if(!userVO.getPassword().equals(SecureUtil.md5(password))){
            return null;
        }
        return userVO;
    }


    @Override
    public void changePassword(UserChangePasswordVO userChangePasswordVO) {
        UserEntity userEntity = userMapper.selectByPrimaryKey(userChangePasswordVO.getUserId());
        userEntity.setPassword(userChangePasswordVO.getNewPassword());
        this.updateNotNull(userEntity);
    }
}
