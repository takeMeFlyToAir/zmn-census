package com.zmn.census.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.zmn.census.action.api.CensusSurveyService;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import static com.zmn.census.common.core.result.ResultCode.DOWNLOAD_FILE_ERROR;


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

    @ApiOperation("下载")
    @GetMapping("/download")
    public void downloadFailedUsingJson(HttpServletResponse response,CensusSurveyQueryQO censusSurveyQueryQO) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            List<CensusDownloadVO> downloadDataList= censusSurveyService.findDownloadData(censusSurveyQueryQO);
            EasyExcel.write(response.getOutputStream(), CensusDownloadVO.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(downloadDataList);
        } catch (Exception e) {
            log.error("下载文件出错{}",e);
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(JSON.toJSONString(CommonResult.failed(DOWNLOAD_FILE_ERROR)));
        }
    }

}
