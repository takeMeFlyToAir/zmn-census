package com.zmn.census.web.controller;

import com.zmn.census.api.service.CommunityService;
import com.zmn.census.api.vo.CommunityVO;
import com.zmn.census.common.core.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhaozhirong on 2020/5/6.
 */
@RestController
@RequestMapping(value = "/community")
@Api(tags = "小区")
public class CommunityController {


    @Autowired
    private CommunityService communityService;

    @ApiOperation("查询所有小区")
    @GetMapping(value = "/list")
    public CommonResult<List<CommunityVO>> index(){
       return CommonResult.success(communityService.findList());
    }

}
