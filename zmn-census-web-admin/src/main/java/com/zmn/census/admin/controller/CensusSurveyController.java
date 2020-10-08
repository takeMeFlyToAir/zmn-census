package com.zmn.census.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.zmn.census.action.api.CensusSurveyService;
import com.zmn.census.action.api.HouseHoldService;
import com.zmn.census.action.api.PersonInfoService;
import com.zmn.census.action.api.RoomAddressApi;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.result.CommonResult;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
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
import java.io.UnsupportedEncodingException;
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

    private static String XLSX = ".xlsx";
    private static String ZIP = ".zip";

    @Autowired
    private CensusSurveyService censusSurveyService;

    @Autowired
    private RoomAddressApi roomAddressApi;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private PersonInfoService personInfoService;

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

    @ApiOperation("更新地址信息")
    @PostMapping(value = "/editRoomAddress")
    public CommonResult editRoomAddress(RoomAddressEditVO roomAddressEditVO){
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(roomAddressEditVO, RoomAddressEntity.class);
        roomAddressApi.updateNotNull(roomAddressEntity);
        return CommonResult.success();
    }

    @ApiOperation("更新户主信息")
    @PostMapping(value = "/editHouseHold")
    public CommonResult editHouseHold(HouseHoldEditVO houseHoldEditVO){
        HouseHoldEntity houseHoldEntity = VoAndBeanUtils.fromVO(houseHoldEditVO, HouseHoldEntity.class);
        houseHoldService.updateNotNull(houseHoldEntity);
        return CommonResult.success();
    }

    @ApiOperation("更新个人信息")
    @PostMapping(value = "/editPersonInfo")
    public CommonResult editPersonInfo(PersonInfoEditVO personInfoEditVO){
        PersonInfoEntity personInfoEntity = VoAndBeanUtils.fromVO(personInfoEditVO, PersonInfoEntity.class);
        personInfoService.updateNotNull(personInfoEntity);
        return CommonResult.success();
    }

    @ApiOperation("导出小区人数的统计数据")
    @GetMapping(value = "/exportCommunityPersonInfo")
    public void exportCommunityPersonInfo(HttpServletResponse response, CensusSurveyQueryQO censusSurveyQueryQO)
            throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = getName(censusSurveyQueryQO);
            response = initResponse(response,fileName,XLSX);
            // 这里需要设置不关闭流
            List<ExportCommunityPersonInfoVO> exportCommunityPersonInfoVOList= censusSurveyService.exportCommunityPersonInfo(censusSurveyQueryQO);
            EasyExcel.write(response.getOutputStream(), ExportCommunityPersonInfoVO.class).autoCloseStream(Boolean.FALSE).sheet("小区户数和人数统计")
                    .doWrite(exportCommunityPersonInfoVOList);
        } catch (Exception e) {
            log.error("下载文件出错{}",e);
            // 重置response
            errorResponse(response);

        }
    }

    private String getName(CensusSurveyQueryQO censusSurveyQueryQO){
        if(StrUtil.isNotBlank(censusSurveyQueryQO.getStartTime()) && StrUtil.isNotBlank(censusSurveyQueryQO.getEndTime())){
            return censusSurveyQueryQO.getStartTime()+"到"+ censusSurveyQueryQO.getEndTime()+"数据";
        }else {
            return "部分数据";
        }
    }

    private HttpServletResponse initResponse(HttpServletResponse response,String fileName,String fileSuffix) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + fileSuffix);
        return response;
    }

    private HttpServletResponse errorResponse(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(JSON.toJSONString(CommonResult.failed(DOWNLOAD_FILE_ERROR)));
        return response;
    }

    @ApiOperation("导出户主底册")
    @GetMapping(value = "/exportHouseHoldInfo")
    public void exportHouseHoldInfo(HttpServletResponse response, CensusSurveyQueryQO censusSurveyQueryQO)
            throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = getName(censusSurveyQueryQO);
            response = initResponse(response,fileName,XLSX);
            // 这里需要设置不关闭流
            List<ExportHouseHoldVO> exportHouseHoldVOList = censusSurveyService.exportHouseHoldInfo(censusSurveyQueryQO);
            EasyExcel.write(response.getOutputStream(), ExportHouseHoldVO.class).autoCloseStream(Boolean.FALSE).sheet("户主姓名底层")
                    .doWrite(exportHouseHoldVOList);
        } catch (Exception e) {
            log.error("下载文件出错{}",e);
            // 重置response
            errorResponse(response);

        }
    }

    @ApiOperation("导出短表")
    @GetMapping(value = "/exportHouseHoldAndPersonInfo")
    public void exportHouseHoldAndPersonInfo(HttpServletResponse response, CensusSurveyQueryQO censusSurveyQueryQO)
            throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = getName(censusSurveyQueryQO);
            response = initResponse(response,fileName,XLSX);
            // 这里需要设置不关闭流
            List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOList = censusSurveyService.exportHouseHoldAndPersonInfo(censusSurveyQueryQO);
            //需要合并的列
            int needMergeIndex = 17;
            int[] mergeColumeIndex = new int[needMergeIndex];
            for (int i = 0; i < needMergeIndex; i++) {
                mergeColumeIndex[i] = i;
            }
            int mergeRowIndex = 1;

            EasyExcel.write(response.getOutputStream(), ExportHouseHoldAndPersonInfoVO.class)
                    .autoCloseStream(Boolean.FALSE)
//                    .registerWriteHandler(new ExcelFillCellMergeStrategy(mergeRowIndex, mergeColumeIndex))
                    .sheet("短表")
                    .doWrite(exportHouseHoldAndPersonInfoVOList);
        } catch (Exception e) {
            log.error("下载文件出错{}",e);
            // 重置response
            errorResponse(response);

        }
    }
}
