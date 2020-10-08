package com.zmn.census.action.service;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.*;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName CensusSurveyServiceImpl
 * Description 保存调查问卷
 * Author zhaozhirong
 * Date 2020/9/9 16:47
 * Version 1.0
 **/
@Service
public class CensusSurveyServiceImpl implements CensusSurveyService {

    @Autowired
    private RoomAddressMapper roomAddressMapper;

    @Autowired
    private RoomAddressApi roomAddressApi;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private CommunityService communityService;

    @Override
    @Transactional
    public void save(CensusSurveyAddVO censusSurveyAddVO) {
        RoomAddressEntity roomAddressEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getRoomAddress(), RoomAddressEntity.class);
        HouseHoldEntity houseHoldEntity = VoAndBeanUtils.fromVO(censusSurveyAddVO.getHouseHold(), HouseHoldEntity.class);
        List<PersonInfoEntity> personInfoEntityList = VoAndBeanUtils.fromVOList(censusSurveyAddVO.getPersonInfoList(), PersonInfoEntity.class);
        roomAddressApi.save(roomAddressEntity);
        houseHoldEntity.setRoomAddressId(roomAddressEntity.getId());
        houseHoldService.save(houseHoldEntity);
        for (PersonInfoEntity personInfoEntity : personInfoEntityList) {
            personInfoEntity.setRoomAddressId(roomAddressEntity.getId());
        }
        //更新小区对应的户数和人数
        synchronized (this){
            communityService.updateCount(censusSurveyAddVO.getCommunityUpdateCountVO());
        }
        personInfoService.saveList(personInfoEntityList);
    }

    @Override
    public PagerResult<CensusSurveyVO> findPage(Pager<CensusSurveyQueryQO> pager) {
        CensusSurveyQueryQO condition = pager.getCondition();
        PageHelper.startPage(pager.getPageNo(),pager.getPageSize());
        List<CensusSurveyVO> filterList = roomAddressMapper.findCensusSurveyVOList(condition);
        PageInfo<CensusSurveyVO> pageInfo = new PageInfo<>(filterList);
        PagerResult<CensusSurveyVO> pagerResult = new PagerResult<>(pageInfo.getList(), pageInfo.getTotal());
        return pagerResult;
    }

    @Override
    @Transactional
    public void deleteAll(Integer roomAddressId) {
        RoomAddressEntity roomAddressEntity = roomAddressMapper.selectByPrimaryKey(roomAddressId);
        //删除houseHold和personInfo
        roomAddressApi.delete(roomAddressId);
        houseHoldService.deleteByRoomAddressId(roomAddressId);
        personInfoService.deleteByRoomAddressId(roomAddressId);
        //更新小区对应的户数和人数
        synchronized (this){
            CommunityUpdateCountVO communityUpdateCountVO = new CommunityUpdateCountVO();
            communityUpdateCountVO.setId(roomAddressEntity.getCommunityId());
            communityUpdateCountVO.setAddHoldCount(-1);
            communityUpdateCountVO.setAddPersonCount(-roomAddressEntity.getPersonCount());
            communityService.updateCount(communityUpdateCountVO);
        }
    }

    @Override
    public CensusSurveyCommonCountVO getCommonCount() {
        CensusSurveyCommonCountVO censusSurveyCommonCountVO = new CensusSurveyCommonCountVO();
        censusSurveyCommonCountVO.setAllCommunityCount(roomAddressMapper.selectAllCommunityCount());
        censusSurveyCommonCountVO.setAllSurveyCount(roomAddressMapper.selectAllSurveyCount());
        censusSurveyCommonCountVO.setAllPersonCount(roomAddressMapper.selectAllPersonCount());
        censusSurveyCommonCountVO.setYesterdaySurveyCount(roomAddressMapper.selectYesterdaySurveyCount());
        censusSurveyCommonCountVO.setTodaySurveyCount(roomAddressMapper.selectTodaySurveyCount());
        return censusSurveyCommonCountVO;
    }

    @Override
    public List<CensusSurveyCountVO> findListSurveyCount(CensusSurveyCountQO censusSurveyCountQO) {
        return roomAddressMapper.selectSurveyCount(censusSurveyCountQO);
    }

    @Override
    public List<CensusDownloadVO> findDownloadData(CensusSurveyQueryQO censusSurveyQueryQO) {
        ArrayList<CensusDownloadVO> censusDownloadVOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CensusDownloadVO censusDownloadVO = new CensusDownloadVO();
            censusDownloadVO.setCommunity("小区"+i);
            censusDownloadVO.setBuildNum("楼栋"+i);
            censusDownloadVO.setUnitNum("单元"+i);
            censusDownloadVO.setFloorNum("楼层"+i);
            censusDownloadVO.setRoomNum("房间"+i);
            censusDownloadVO.setExaminePersonName("检查人"+i);
            censusDownloadVO.setFillPersonPhone("填报人电话"+i);
            censusDownloadVOS.add(censusDownloadVO);
        }
        return censusDownloadVOS;
    }

    @Override
    public List<ExportCommunityPersonInfoVO> exportCommunityPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO) {
        return roomAddressMapper.findExportCommunityPersonInfo(censusSurveyQueryQO);
    }

    @Override
    public List<ExportHouseHoldVO> exportHouseHoldInfo(CensusSurveyQueryQO censusSurveyQueryQO) {
        long start = System.currentTimeMillis();
        List<ExportHouseHoldVO> exportHouseHoldVOList = new ArrayList<>();
        List<ExportHouseHoldDataVO> exportHouseHoldDataVOList = roomAddressMapper.findExportHouseHoldInfo(censusSurveyQueryQO);
        Map<Integer, List<ExportHouseHoldDataVO>> roomAddressMapPersonInfo =
                exportHouseHoldDataVOList.stream().collect(Collectors.groupingBy(ExportHouseHoldDataVO::getRoomAddressId));
        Collection<List<ExportHouseHoldDataVO>> personInfoCollection = roomAddressMapPersonInfo.values();
        List<List<ExportHouseHoldDataVO>> personInfoList = collectionToList(personInfoCollection);
//        ForkJoinPool pool = new ForkJoinPool(4);
//        ForkJoinTaskForHouseHold forkJoinTaskForHouseHold = new ForkJoinTaskForHouseHold(personInfoList, 0, personInfoList.size());
//        exportHouseHoldVOList = pool.invoke(forkJoinTaskForHouseHold);


        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<List<ExportHouseHoldVO>>> futureList = new ArrayList<>();

        int totalSize = personInfoList.size();
        final int THRESHOLD = 1000;
        int count = totalSize/THRESHOLD;
        for (int i = 0; i < count; i++) {
            Future<List<ExportHouseHoldVO>> submit = pool.submit(new FutureTaskForHouseHold(CollUtil.sub(personInfoList, i * THRESHOLD, (i + 1) * THRESHOLD)));
            futureList.add(submit);
        }
        Future<List<ExportHouseHoldVO>> lastSubmit = pool.submit(new FutureTaskForHouseHold(CollUtil.sub(personInfoList, count * THRESHOLD, totalSize)));
        futureList.add(lastSubmit);
        for (Future<List<ExportHouseHoldVO>> future : futureList) {
            try {
                exportHouseHoldVOList.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        CollUtil.sortByProperty(exportHouseHoldVOList, "community");
        for (int i = 0; i < exportHouseHoldVOList.size(); i++) {
            exportHouseHoldVOList.get(i).setIndex(i+1);
        }
        long end = System.currentTimeMillis();
        System.out.println("personInfoSize="+personInfoList.size()+",exportHouseHoldVOList.size="+exportHouseHoldVOList.size()+",costTime="+(end-start));
        return exportHouseHoldVOList;
    }

    private List<List<ExportHouseHoldDataVO>> collectionToList(Collection<List<ExportHouseHoldDataVO>> personInfoCollection){
        ArrayList<List<ExportHouseHoldDataVO>> lists = new ArrayList<>();
        lists.addAll(personInfoCollection);
        return lists;
    }

    private static class FutureTaskForHouseHold implements Callable<List<ExportHouseHoldVO>>{



        private static String D71 = "本普查小区";
        private static String D72 = "本村（居）委会其他普查小区";
        private static String D73 = "本乡（镇、街道）其他村（居）委会";
        private static String D74 = "本县（市、区、旗）其他乡（镇、街道）";
        private static String D75 = "其他县（市、区、旗），请在下面填写地址";
        private static String D76 = "香港特别行政区、澳门特别行政区、台湾地区）";
        private static String D77 = "国外";

        private static String D81 = "本村（居）委会";
        private static String D82 = "本乡（镇、街道）其他村（居）委会";
        private static String D83 = "本县（市、区、旗）其他乡（镇、街道）";
        private static String D84 = "其他县（市、区、旗），请在下面填写地址";
        private static String D85 = "户口待定";


        private Collection<List<ExportHouseHoldDataVO>> personInfoList;

        public FutureTaskForHouseHold(Collection<List<ExportHouseHoldDataVO>> personInfoCollection) {
            personInfoList = personInfoCollection;
        }

        private List<ExportHouseHoldVO> dealExportHouseHoldDataVO(){
            ArrayList<ExportHouseHoldVO> exportHouseHoldVOS = new ArrayList<>();
            for (List<ExportHouseHoldDataVO> exportHouseHoldDataVOList : personInfoList) {
                ExportHouseHoldDataVO exportHouseHoldDataVO = exportHouseHoldDataVOList.get(0);
                ExportHouseHoldVO exportHouseHoldVO = new ExportHouseHoldVO();
                exportHouseHoldVO.setCommunity(exportHouseHoldDataVO.getCommunity());
                exportHouseHoldVO.setBuildNum(exportHouseHoldDataVO.getBuildNum());
                exportHouseHoldVO.setUnitNum(exportHouseHoldDataVO.getUnitNum());
                exportHouseHoldVO.setFloorNum(exportHouseHoldDataVO.getFloorNum());
                exportHouseHoldVO.setRoomNum(exportHouseHoldDataVO.getRoomNum());
                exportHouseHoldVO.setM3(exportHouseHoldDataVO.getM3());
                exportHouseHoldVO.setCreatedDateStr(exportHouseHoldDataVO.getCreatedDateStr());
                //设置户主姓名
                exportHouseHoldVO.setM4(getM4(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM5(exportHouseHoldDataVO.getH2Live());
                exportHouseHoldVO.setM6(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D71,D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM7(getM7(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM8(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D71,D85).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM9(exportHouseHoldDataVO.getH2NoLive());
                exportHouseHoldVO.setM10(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D72,D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM11(getM11(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM12(exportHouseHoldDataVO.getH3());
                exportHouseHoldVO.setM13(exportHouseHoldDataVO.getH4());
                exportHouseHoldVO.setM16(exportHouseHoldDataVO.getFillPersonPhone());
                exportHouseHoldVOS.add(exportHouseHoldVO);
            }
            return exportHouseHoldVOS;
        }

        private String getM4(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            for (ExportHouseHoldDataVO exportHouseHoldDataVO : exportHouseHoldDataVOList) {
                if("户主".endsWith(exportHouseHoldDataVO.getD2())){
                    return exportHouseHoldDataVO.getD1();
                }
            }
            return "无";
        }

        private long getM7(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D71.equals(d7) && (D82.equals(d8) || D83.equals(d8) || D84.equals(d8));
                }
            }).count();
        }

        private long getM11(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D81.equals(d8) && (D73.equals(d7) || D74.equals(d7) || D75.equals(d7));
                }
            }).count();
        }

        @Override
        public List<ExportHouseHoldVO> call() throws Exception {
            return dealExportHouseHoldDataVO();
        }
    }

    private static class ForkJoinTaskForHouseHold extends RecursiveTask<List<ExportHouseHoldVO>>{

        private static final int THRESHOLD = 1000;


        private static String D71 = "本普查小区";
        private static String D72 = "本村（居）委会其他普查小区";
        private static String D73 = "本乡（镇、街道）其他村（居）委会";
        private static String D74 = "本县（市、区、旗）其他乡（镇、街道）";
        private static String D75 = "其他县（市、区、旗），请在下面填写地址";
        private static String D76 = "香港特别行政区、澳门特别行政区、台湾地区）";
        private static String D77 = "国外";

        private static String D81 = "本村（居）委会";
        private static String D82 = "本乡（镇、街道）其他村（居）委会";
        private static String D83 = "本县（市、区、旗）其他乡（镇、街道）";
        private static String D84 = "其他县（市、区、旗），请在下面填写地址";
        private static String D85 = "户口待定";


        private List<List<ExportHouseHoldDataVO>> personInfoList;
        private int start;
        private int end;

        public ForkJoinTaskForHouseHold(List<List<ExportHouseHoldDataVO>> personInfoList, int start, int end) {
            this.personInfoList = personInfoList;
            this.start = start;
            this.end = end;
        }

        private List<ExportHouseHoldVO> dealExportHouseHoldDataVO(int start,int end){
            ArrayList<ExportHouseHoldVO> exportHouseHoldVOS = new ArrayList<>();
            for (; start < end; start++) {
                List<ExportHouseHoldDataVO> exportHouseHoldDataVOList = this.personInfoList.get(start);
                ExportHouseHoldDataVO exportHouseHoldDataVO = exportHouseHoldDataVOList.get(0);
                ExportHouseHoldVO exportHouseHoldVO = new ExportHouseHoldVO();
                exportHouseHoldVO.setM3(exportHouseHoldDataVO.getRoomNum());
                //设置户主姓名
                exportHouseHoldVO.setM4(getM4(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM5(exportHouseHoldDataVO.getH2Live());
                exportHouseHoldVO.setM6(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D71,D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM7(getM7(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM8(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D71,D85).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM9(exportHouseHoldDataVO.getH2NoLive());
                exportHouseHoldVO.setM10(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(D72,D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM11(getM11(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM12(exportHouseHoldDataVO.getH3());
                exportHouseHoldVO.setM13(exportHouseHoldDataVO.getH4());
                exportHouseHoldVO.setM16(exportHouseHoldDataVO.getFillPersonPhone());
                exportHouseHoldVOS.add(exportHouseHoldVO);
            }
            System.out.println(exportHouseHoldVOS.size());
            return exportHouseHoldVOS;
        }

        private String getM4(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            for (ExportHouseHoldDataVO exportHouseHoldDataVO : exportHouseHoldDataVOList) {
                if("户主".endsWith(exportHouseHoldDataVO.getD2())){
                    return exportHouseHoldDataVO.getD1();
                }
            }
            return "无";
        }

        private long getM7(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D71.equals(d7) && (D82.equals(d8) || D83.equals(d8) || D84.equals(d8));
                }
            }).count();
        }

        private long getM11(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList){
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D81.equals(d8) && (D73.equals(d7) || D74.equals(d7) || D75.equals(d7));
                }
            }).count();
        }
        @Override
        protected List<ExportHouseHoldVO> compute() {
            ArrayList<ExportHouseHoldVO> exportHouseHoldVOS = new ArrayList<>();

            if ((end - start) <= THRESHOLD) {
                System.out.println(String.format("compute %d~%d", start, end));
//                int tempEnd = end + 1;
//                int tempStart = start + 1;
//                if(end == this.personInfoList.size()){
//                    tempEnd = end;
//                }
//                if(start == 0){
//                    tempStart = 0;
//                }

                return dealExportHouseHoldDataVO(start,end);
            }

            int middle = (start + end) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            ForkJoinTaskForHouseHold left = new ForkJoinTaskForHouseHold(personInfoList, start, middle);
            ForkJoinTaskForHouseHold right = new ForkJoinTaskForHouseHold(personInfoList, middle, end);
            invokeAll(left, right);
            List<ExportHouseHoldVO> leftJoin = left.join();
            List<ExportHouseHoldVO> rightJoin = left.join();
            System.out.println("leftJoin.size()==="+leftJoin.size());
            System.out.println("rightJoin.size()==="+rightJoin.size());
            exportHouseHoldVOS.addAll(leftJoin);
            exportHouseHoldVOS.addAll(rightJoin);
            System.out.println("result = " + exportHouseHoldVOS.size());

            return exportHouseHoldVOS;
        }
    }

    @Override
    public List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO) {
        List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfo = roomAddressMapper.findExportHouseHoldAndPersonInfo(censusSurveyQueryQO);
        List<ExportHouseHoldAndPersonInfoVO> result = new ArrayList<>(exportHouseHoldAndPersonInfo.size() * 2);
        int index = 1;
        Set<Integer> container = new HashSet<>();
        for (ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO : exportHouseHoldAndPersonInfo) {
            if(container.contains(exportHouseHoldAndPersonInfoVO.getRoomAddressId())){
                dealMerge(exportHouseHoldAndPersonInfoVO);

            }
            exportHouseHoldAndPersonInfoVO.setIndex(index++);
            result.add(exportHouseHoldAndPersonInfoVO);
            container.add(exportHouseHoldAndPersonInfoVO.getRoomAddressId());
        }
        return result;
    }

    private ExportHouseHoldAndPersonInfoVO dealMerge(ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO){
        exportHouseHoldAndPersonInfoVO.setCommunity("");
        exportHouseHoldAndPersonInfoVO.setBuildNum("");
        exportHouseHoldAndPersonInfoVO.setUnitNum("");
        exportHouseHoldAndPersonInfoVO.setFloorNum("");
        exportHouseHoldAndPersonInfoVO.setRoomNum("");
        exportHouseHoldAndPersonInfoVO.setH1("");
        exportHouseHoldAndPersonInfoVO.setH2Live("");
        exportHouseHoldAndPersonInfoVO.setH2NoLive("");
        exportHouseHoldAndPersonInfoVO.setH3Man("");
        exportHouseHoldAndPersonInfoVO.setH3Woman("");
        exportHouseHoldAndPersonInfoVO.setH4Man("");
        exportHouseHoldAndPersonInfoVO.setH4Woman("");
        exportHouseHoldAndPersonInfoVO.setH5("");
        exportHouseHoldAndPersonInfoVO.setH6("");
        exportHouseHoldAndPersonInfoVO.setH7("");
        exportHouseHoldAndPersonInfoVO.setH8("");
        exportHouseHoldAndPersonInfoVO.setH9("");
        return exportHouseHoldAndPersonInfoVO;
    }
}
