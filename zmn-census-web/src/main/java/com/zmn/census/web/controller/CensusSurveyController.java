package com.zmn.census.web.controller;

import com.zmn.census.action.api.CensusSurveyService;
import com.zmn.census.api.vo.CensusSurveyAddVO;
import com.zmn.census.api.vo.CensusSurveyVO;
import com.zmn.census.api.vo.sample.CensusSurveySampleVO;
import com.zmn.census.common.core.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaozhirong on 2020/5/6.
 */
@Slf4j
@RestController
@RequestMapping(value = "/censusSurvey")
@Api(tags = "保存问卷")
public class CensusSurveyController {


    @Autowired
    private CensusSurveyService censusSurveyService;

    @ApiOperation("保存问卷")
    @PostMapping(value = "/save")
    public CommonResult save(@RequestBody CensusSurveyAddVO censusAddVO){
        log.info("====================={}",censusAddVO);
        censusSurveyService.save(censusAddVO);
        return CommonResult.success();
    }

    @ApiOperation("根据唯一码查询问卷信息")
    @GetMapping(value = "/getById")
    public CommonResult getById(Integer id){
        CensusSurveyVO censusSurveyVO = censusSurveyService.getById(id);
        return CommonResult.success(censusSurveyVO);
    }

    @ApiOperation("更新问卷")
    @PostMapping(value = "/update")
    public CommonResult update(@RequestBody CensusSurveySampleVO censusSurveySampleVO){
        System.out.println(1111);
        censusSurveyService.update(censusSurveySampleVO);
        return CommonResult.success();
    }

}