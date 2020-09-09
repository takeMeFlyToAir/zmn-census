package com.zmn.census.action.serviceImpl;

import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.action.mapper.CommunityMapper;
import com.zmn.census.api.service.CommunityService;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName CommunityServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<CommunityVO> findList() {
        Example example = new Example(CommunityEntity.class);
        example.setOrderByClause(" created_date desc ");
        example.createCriteria().andEqualTo("deleted", 0);
        List<CommunityEntity> communityEntityList = communityMapper.selectByExample(example);
        return VoAndBeanUtils.toVOList(communityEntityList, CommunityVO.class);
    }
}
