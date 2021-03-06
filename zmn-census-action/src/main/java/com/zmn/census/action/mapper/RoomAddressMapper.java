package com.zmn.census.action.mapper;

import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.dao.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName RoomAddressMapper
 * Description TODO
 * Author zhaozhirong
 * Date 2020/9/9 16:50
 * Version 1.0
 **/
@Mapper
public interface RoomAddressMapper extends MyMapper<RoomAddressEntity> {

    List<CensusSurveyVO> findCensusSurveyVOList(CensusSurveyQueryQO censusSurveyQueryQO);

    CensusSurveyVO getCensusSurveyVO(Integer id);

    int selectAllCommunityCount();

    int selectAllSurveyCount();

    int selectAllPersonCount();

    int selectYesterdaySurveyCount();

    int selectTodaySurveyCount();

    List<CensusSurveyCountVO> selectSurveyCount(CensusSurveyCountQO censusSurveyCountQO);

    List<ExportCommunityPersonInfoVO> findExportCommunityPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO);

    List<ExportHouseHoldDataVO> findExportHouseHoldInfo(CensusSurveyQueryQO censusSurveyQueryQO);

    List<ExportHouseHoldAndPersonInfoVO> findExportHouseHoldAndPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO);

}
