package com.zmn.census.action.api;

import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.CensusSurveyAddVO;
import com.zmn.census.api.vo.CensusSurveyVO;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;

/**
 * @ClassName CensusSurveyService
 * Description 小区
 * Author zhaozhirong
 * Date 2020/9/9 16:43
 * Version 1.0
 **/
public interface CensusSurveyService {

    void save(CensusSurveyAddVO censusSurveyAddVO);

    PagerResult<CensusSurveyVO> findPage(Pager<CensusSurveyQueryQO> pager);

    void deleteAll(Integer roomAddressId);

}
