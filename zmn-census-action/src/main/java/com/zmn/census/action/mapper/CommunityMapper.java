package com.zmn.census.action.mapper;

import com.zmn.census.action.entity.CommunityEntity;
import com.zmn.census.api.qo.CommunityQueryQO;
import com.zmn.census.common.core.dao.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName CommunityMapper
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:50
 * Version 1.0
 **/
@Mapper
public interface CommunityMapper  extends MyMapper<CommunityEntity> {

    List<String> findAllTown();

    List<String> findVillageByTown(String town);

    List<String> findCommunityByTownAndVillage(CommunityQueryQO communityQueryQO);

}
