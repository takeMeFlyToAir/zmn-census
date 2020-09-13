package com.zmn.census.admin.controller;

import com.zmn.census.action.api.CensusSurveyService;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.CensusSurveyCommonCountVO;
import com.zmn.census.api.vo.CensusSurveyCountVO;
import com.zmn.census.api.vo.CensusSurveyVO;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.core.result.CommonResult;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by zhaozhirong on 2020/5/6.
 */
@RestController
@RequestMapping(value = "/censusSurvey")
@Api(tags = "问卷")
@Slf4j
public class CensusSurveyController {


    @Autowired
    private CensusSurveyService censusSurveyService;

    @ApiOperation("分页查询问卷")
    @PostMapping(value = "/findPage")
    public CommonResult<PagerResult<CensusSurveyVO>> findPage(Pager<CensusSurveyQueryQO> pager, CensusSurveyQueryQO censusSurveyQueryQO){
        try {
            pager.setCondition(censusSurveyQueryQO);
            return CommonResult.success(censusSurveyService.findPage(pager));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }
    @ApiOperation("删除小区")
    @PostMapping(value = "/delete")
    public CommonResult<CommunityVO> delete(Integer id){
        censusSurveyService.deleteAll(id);
        return CommonResult.success();
    }

    @ApiOperation("首页统计")
    @GetMapping(value = "/commonCount")
    public CommonResult<CensusSurveyCommonCountVO> getCommonCount(){
        try {
            return CommonResult.success(censusSurveyService.getCommonCount());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

    @ApiOperation("折线图")
    @GetMapping(value = "/surveyCount")
    public CommonResult<List<CensusSurveyCountVO>> surveyCount(CensusSurveyCountQO censusSurveyCountQO){
        try {
            return CommonResult.success(censusSurveyService.findListSurveyCount(censusSurveyCountQO));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }

}
