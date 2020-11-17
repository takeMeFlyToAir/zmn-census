package com.zmn.census.action.api;

import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
import com.zmn.census.api.vo.sample.CensusSurveySampleVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;

import java.util.List;

/**
 * @ClassName CensusSurveyService
 * Description 小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
public interface CensusSurveyService {

    void save(CensusSurveyAddVO censusSurveyAddVO);

    void update(CensusSurveySampleVO censusSurveySampleVO);

    CensusSurveyVO getById(Integer id);

    PagerResult<CensusSurveyVO> findPage(Pager<CensusSurveyQueryQO> pager);

    void deleteAll(Integer roomAddressId);

    CensusSurveyCommonCountVO getCommonCount();

    List<CensusSurveyCountVO> findListSurveyCount(CensusSurveyCountQO censusSurveyCountQO);

    List<CensusDownloadVO> findDownloadData(CensusSurveyQueryQO censusSurveyQueryQO);

    List<ExportCommunityPersonInfoVO> exportCommunityPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO);

    List<ExportHouseHoldVO> exportHouseHoldInfo(CensusSurveyQueryQO censusSurveyQueryQO);

    List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO);

}
