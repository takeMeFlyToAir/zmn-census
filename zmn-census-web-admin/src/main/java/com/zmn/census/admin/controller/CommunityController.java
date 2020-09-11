package com.zmn.census.admin.controller;

import com.zmn.census.action.api.CommunityService;
import com.zmn.census.api.qo.CommunityQueryQO;
import com.zmn.census.api.vo.CommunityAddVO;
import com.zmn.census.api.vo.CommunityEditVO;
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
@RequestMapping(value = "/community")
@Api(tags = "小区")
@Slf4j
public class CommunityController {


    @Autowired
    private CommunityService communityService;

    @ApiOperation("分页查询小区")
    @GetMapping(value = "/findPage")
    public CommonResult<PagerResult<CommunityVO>> findPage(Pager<CommunityQueryQO> pager, CommunityQueryQO communityQueryQO){
        try {
            pager.setCondition(communityQueryQO);
            return CommonResult.success(communityService.findPage(pager));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return CommonResult.failed();
        }
    }


    @ApiOperation("查询所有街道")
    @GetMapping(value = "/findAllTown")
    public CommonResult<List<String>> findAllTown(){
        return CommonResult.success(communityService.findAllTown());
    }

    @ApiOperation("根据街道查询普查小区")
    @GetMapping(value = "/findVillageByTown")
    public CommonResult<List<String>> findVillageByTown(String town){
        return CommonResult.success(communityService.findVillageByTown(town));
    }

    @ApiOperation("根据街道和普查小区查询住宅小区")
    @GetMapping(value = "/findCommunityByTownAndVillage")
    public CommonResult<List<String>> findCommunityByTownAndVillage(CommunityQueryQO communityQueryQO){
        return CommonResult.success(communityService.findCommunityByTownAndVillage(communityQueryQO));
    }

    @ApiOperation("查询所有小区")
    @GetMapping(value = "/list")
    public CommonResult<List<CommunityVO>> findList(){
       return CommonResult.success(communityService.findList());
    }

    @ApiOperation("保存小区")
    @PostMapping(value = "/save")
    public CommonResult<CommunityVO> save(CommunityAddVO communityAddVO){
        return CommonResult.success(communityService.save(communityAddVO));
    }

    @ApiOperation("修改小区")
    @PostMapping(value = "/edit")
    public CommonResult<CommunityVO> edit(CommunityEditVO communityEditVO){
        return CommonResult.success(communityService.edit(communityEditVO));
    }

    @ApiOperation("删除小区")
    @PostMapping(value = "/delete")
    public CommonResult<CommunityVO> delete(Integer id){
        communityService.delete(id);
        return CommonResult.success();
    }


}
