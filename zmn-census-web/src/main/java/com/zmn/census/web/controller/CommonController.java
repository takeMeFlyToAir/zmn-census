package com.zmn.census.web.controller;

import com.zmn.census.action.api.CommunityApi;
import com.zmn.census.api.qo.CommunityQueryQO;
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
@RequestMapping(value = "/common")
@Api(tags = "提供前台地址选择项")
public class CommonController {


    @Autowired
    private CommunityApi communityApi;

    @ApiOperation("查询所有街道")
    @GetMapping(value = "/findAllTown")
    public CommonResult<List<String>> findAllTown(){
       return CommonResult.success(communityApi.findAllTown());
    }

    @ApiOperation("根据街道查询普查小区")
    @GetMapping(value = "/findVillageByTown")
    public CommonResult<List<String>> findVillageByTown(String town){
        return CommonResult.success(communityApi.findVillageByTown(town));
    }

    @ApiOperation("根据街道和普查小区查询住宅小区")
    @GetMapping(value = "/findCommunityByTownAndVillage")
    public CommonResult<List<String>> findCommunityByTownAndVillage(CommunityQueryQO communityQueryQO){
        return CommonResult.success(communityApi.findCommunityByTownAndVillage(communityQueryQO));
    }

}
