package com.zmn.census.web.controller;

import com.zmn.census.action.api.CensusSurveyApi;
import com.zmn.census.api.vo.CensusSurveyAddVO;
import com.zmn.census.common.core.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaozhirong on 2020/5/6.
 */
@Slf4j
@RestController
@RequestMapping(value = "/censusSurvey")
@Api(tags = "保存问卷")
public class CensusSurveyController {


    @Autowired
    private CensusSurveyApi censusSurveyApi;

    @ApiOperation("保存小区")
    @PostMapping(value = "/save")
    public CommonResult save(@RequestBody CensusSurveyAddVO censusAddVO){
        log.info("====================={}",censusAddVO);
        censusSurveyApi.save(censusAddVO);
        return CommonResult.success();
    }

}
