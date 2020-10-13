package com.zmn.census.action.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.RoomAddressApi;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.api.qo.RoomAddressQueryQO;
import com.zmn.census.api.vo.CommunityEditVO;
import com.zmn.census.api.vo.RoomAddressVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName RoomAddressServiceImpl
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class RoomAddressServiceApi extends BaseService<RoomAddressEntity> implements RoomAddressApi {

    @Autowired
    private RoomAddressMapper roomAddressMapper;


    @Override
    public void updateCommunityInfo(CommunityEditVO communityEditVO) {
        Example example = new Example(RoomAddressEntity.class);
        example.createCriteria().andEqualTo("deleted", 0)
                .andEqualTo("communityId",communityEditVO.getId());

        RoomAddressEntity roomAddressEntity = new RoomAddressEntity();
        roomAddressEntity.setTown(communityEditVO.getTown());
        roomAddressEntity.setVillage(communityEditVO.getVillage());
        roomAddressEntity.setCommunity(communityEditVO.getName());
        roomAddressEntity.setChargePersonId(communityEditVO.getChargePersonId());
        roomAddressEntity.setChargePersonName(communityEditVO.getChargePersonName());
        roomAddressEntity.setArea(communityEditVO.getArea());
        roomAddressMapper.updateByExampleSelective(roomAddressEntity,example);
    }

    @Override
    public PagerResult<RoomAddressVO> findPage(Pager<RoomAddressQueryQO> pager) {
        RoomAddressQueryQO condition = pager.getCondition();
        Example example = new Example(RoomAddressEntity.class);
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
        if(condition.getChargePersonId() != null){
            criteria.andEqualTo("chargePersonId", condition.getChargePersonId());
        }
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize());
        List<RoomAddressEntity> filterList = this.selectByExample(example);
        PageInfo<RoomAddressEntity> pageInfo = new PageInfo<>(filterList);
        PagerResult<RoomAddressEntity> pagerResult = new PagerResult<>(pageInfo.getList(), pageInfo.getTotal());
        return PagerResult.toVOPagerResult(pagerResult, RoomAddressVO.class);
    }
}
