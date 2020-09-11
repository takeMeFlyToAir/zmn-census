package com.zmn.census.action.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.CommunityService;
import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.action.mapper.CommunityMapper;
import com.zmn.census.api.qo.CommunityQueryQO;
import com.zmn.census.api.vo.CommunityAddVO;
import com.zmn.census.api.vo.CommunityEditVO;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.core.service.BaseService;
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
public class CommunityServiceImpl extends BaseService<CommunityEntity> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;


    @Override
    public CommunityVO get(Integer id) {
        return VoAndBeanUtils.toVO(communityMapper.selectByPrimaryKey(id), CommunityVO.class);
    }

    @Override
    public PagerResult<CommunityVO> findPage(Pager<CommunityQueryQO> pager) {
        CommunityQueryQO condition = pager.getCondition();
        Example example = new Example(CommunityEntity.class);
        example.setOrderByClause(" created_date desc ");
        Example.Criteria criteria = example.createCriteria().andEqualTo("deleted", 0);

        if(StrUtil.isNotEmpty(condition.getTown())){
            criteria.andLike("town", "%"+condition.getTown()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getVillage())){
            criteria.andLike("village", "%"+condition.getVillage()+"%");
        }
        if(StrUtil.isNotEmpty(condition.getName())){
            criteria.andLike("name", "%"+condition.getName()+"%");
        }
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize());
        List<CommunityEntity> filterList = this.selectByExample(example);
        PageInfo<CommunityEntity> pageInfo = new PageInfo<>(filterList);
        PagerResult<CommunityEntity> pagerResult = new PagerResult<>(pageInfo.getList(), pageInfo.getTotal());
        return PagerResult.toVOPagerResult(pagerResult, CommunityVO.class);
    }

    @Override
    public List<CommunityVO> findList() {
        Example example = new Example(CommunityEntity.class);
        example.setOrderByClause(" created_date desc ");
        example.createCriteria().andEqualTo("deleted", 0);
        List<CommunityEntity> communityEntityList = communityMapper.selectByExample(example);
        return VoAndBeanUtils.toVOList(communityEntityList, CommunityVO.class);
    }

    @Override
    public CommunityVO save(CommunityAddVO communityAddVO) {
        CommunityEntity communityEntity = VoAndBeanUtils.fromVO(communityAddVO, CommunityEntity.class);
        this.save(communityEntity);
        return VoAndBeanUtils.toVO(communityEntity,CommunityVO.class);
    }


    @Override
    public List<String> findAllTown() {
        return communityMapper.findAllTown();
    }

    @Override
    public List<String> findVillageByTown(String town) {
        return communityMapper.findVillageByTown(town);
    }

    @Override
    public List<String> findCommunityByTownAndVillage(CommunityQueryQO communityQueryQO) {
        return communityMapper.findCommunityByTownAndVillage(communityQueryQO);
    }

    @Override
    public CommunityVO edit(CommunityEditVO communityEditVO) {
        CommunityEntity communityEntity = VoAndBeanUtils.fromVO(communityEditVO, CommunityEntity.class);
        this.updateNotNull(communityEntity);
        return VoAndBeanUtils.toVO(communityEntity,CommunityVO.class);
    }
}
