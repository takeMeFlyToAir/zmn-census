package com.zmn.census.action.api;

import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.api.qo.CommunityQueryQO;
import com.zmn.census.api.vo.CommunityAddVO;
import com.zmn.census.api.vo.CommunityEditVO;
import com.zmn.census.api.vo.CommunityUpdateCountVO;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.core.service.IService;

import java.util.List;

/**
 * @ClassName CommunityService
 * Description 小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
public interface CommunityService extends IService<CommunityEntity> {

    CommunityVO get(Integer id);

    PagerResult<CommunityVO> findPage(Pager<CommunityQueryQO> pager);


    List<CommunityVO> findList();

    CommunityVO save(CommunityAddVO communityAddVO);

    List<String> findAllTown();

    List<String> findVillageByTown(String town);

    List<String> findCommunityByTownAndVillage(CommunityQueryQO communityQueryQO);

    CommunityVO edit(CommunityEditVO communityEditVO);

    void updateCount(CommunityUpdateCountVO communityUpdateCountVO);

}
