package com.zmn.census.action.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmn.census.action.api.*;
import com.zmn.census.action.entity.HouseHoldEntity;
import com.zmn.census.action.entity.PersonInfoEntity;
import com.zmn.census.action.entity.RoomAddressEntity;
import com.zmn.census.action.mapper.RoomAddressMapper;
import com.zmn.census.api.common.CensusConstant;
import com.zmn.census.api.qo.CensusSurveyCountQO;
import com.zmn.census.api.qo.CensusSurveyQueryQO;
import com.zmn.census.api.vo.*;
import com.zmn.census.common.core.result.Pager;
import com.zmn.census.common.core.result.PagerResult;
import com.zmn.census.common.utils.object.VoAndBeanUtils;
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
        CommunityVO communityVO = communityService.get(roomAddressEntity.getCommunityId());
        if (communityVO != null) {
            roomAddressEntity.setArea(communityVO.getArea());
        }
        roomAddressApi.save(roomAddressEntity);
        houseHoldEntity.setRoomAddressId(roomAddressEntity.getId());
        houseHoldService.save(houseHoldEntity);
        for (PersonInfoEntity personInfoEntity : personInfoEntityList) {
            personInfoEntity.setRoomAddressId(roomAddressEntity.getId());
        }
        //更新小区对应的户数和人数
        synchronized (this) {
            communityService.updateCount(censusSurveyAddVO.getCommunityUpdateCountVO());
        }
        personInfoService.saveList(personInfoEntityList);
    }

    @Override
    public PagerResult<CensusSurveyVO> findPage(Pager<CensusSurveyQueryQO> pager) {
        CensusSurveyQueryQO condition = pager.getCondition();
        PageHelper.startPage(pager.getPageNo(), pager.getPageSize());
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
        synchronized (this) {
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
            censusDownloadVO.setCommunity("小区" + i);
            censusDownloadVO.setBuildNum("楼栋" + i);
            censusDownloadVO.setUnitNum("单元" + i);
            censusDownloadVO.setFloorNum("楼层" + i);
            censusDownloadVO.setRoomNum("房间" + i);
            censusDownloadVO.setExaminePersonName("检查人" + i);
            censusDownloadVO.setFillPersonPhone("填报人电话" + i);
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
//        // 提交可分解的ForkJoinTask任务
//        ForkJoinTask<List<ExportHouseHoldVO>> future = pool.submit(forkJoinTaskForHouseHold);
//        try {
//            exportHouseHoldVOList = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        // 关闭线程池
//        pool.shutdown();


        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<List<ExportHouseHoldVO>>> futureList = new ArrayList<>();

        int totalSize = personInfoList.size();
        final int THRESHOLD = 1000;
        int count = totalSize / THRESHOLD;
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
        Comparator<ExportHouseHoldVO> byAreaAsc = Comparator.comparing(ExportHouseHoldVO::getArea);
        Comparator<ExportHouseHoldVO> byCommunityAsc = Comparator.comparing(ExportHouseHoldVO::getCommunity);
        Comparator<ExportHouseHoldVO> byBuildNumAsc = Comparator.comparing(ExportHouseHoldVO::getBuildNumInt);
        Comparator<ExportHouseHoldVO> byUnitNumAsc = Comparator.comparing(ExportHouseHoldVO::getUnitNumInt);
        Comparator<ExportHouseHoldVO> byFloorNumAsc = Comparator.comparingInt(ExportHouseHoldVO::getFloorNumInt);
        Comparator<ExportHouseHoldVO> byRoomAsc = Comparator.comparing(ExportHouseHoldVO::getRoomNumInt);

        // 联合排序
        Comparator<ExportHouseHoldVO> finalComparator = byAreaAsc
                .thenComparing(byCommunityAsc)
                .thenComparing(byBuildNumAsc)
                .thenComparing(byUnitNumAsc)
                .thenComparing(byFloorNumAsc)
                .thenComparing(byRoomAsc);

        List<ExportHouseHoldVO> result = exportHouseHoldVOList.stream().sorted(finalComparator).collect(Collectors.toList());


        for (int i = 0; i < result.size(); i++) {
            result.get(i).setIndex(i + 1);
        }
        long end = System.currentTimeMillis();
        System.out.println("personInfoSize=" + personInfoList.size() + ",result.size=" + result.size() + ",costTime=" + (end - start));
        return result;
    }

    private List<List<ExportHouseHoldDataVO>> collectionToList(Collection<List<ExportHouseHoldDataVO>> personInfoCollection) {
        ArrayList<List<ExportHouseHoldDataVO>> lists = new ArrayList<>();
        lists.addAll(personInfoCollection);
        return lists;
    }

    private static class FutureTaskForHouseHold implements Callable<List<ExportHouseHoldVO>> {


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

        private List<ExportHouseHoldVO> dealExportHouseHoldDataVO() {
            ArrayList<ExportHouseHoldVO> exportHouseHoldVOS = new ArrayList<>();
            for (List<ExportHouseHoldDataVO> exportHouseHoldDataVOList : personInfoList) {
                ExportHouseHoldDataVO exportHouseHoldDataVO = exportHouseHoldDataVOList.get(0);
                ExportHouseHoldVO exportHouseHoldVO = new ExportHouseHoldVO();
                exportHouseHoldVO.setRoomAddressId(exportHouseHoldDataVO.getRoomAddressId());
                String area = exportHouseHoldDataVO.getArea();
                if (StrUtil.isEmpty(area)) {
                    area = "";
                }
                exportHouseHoldVO.setArea(area);
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
                exportHouseHoldVO.setM6(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D71, D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM7(getM7(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM8(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D71, D85).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM9(exportHouseHoldDataVO.getH2NoLive());
                exportHouseHoldVO.setM10(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D72, D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM11(getM11(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM12(exportHouseHoldDataVO.getH3());
                exportHouseHoldVO.setM13(exportHouseHoldDataVO.getH4());
                exportHouseHoldVO.setM16(exportHouseHoldDataVO.getFillPersonPhone());
                exportHouseHoldVOS.add(exportHouseHoldVO);
            }
            return exportHouseHoldVOS;
        }

        private String getM4(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
            for (ExportHouseHoldDataVO exportHouseHoldDataVO : exportHouseHoldDataVOList) {
                if ("户主".endsWith(exportHouseHoldDataVO.getD2())) {
                    return exportHouseHoldDataVO.getD1();
                }
            }
            return "无";
        }

        private long getM7(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D71.equals(d7) && (D82.equals(d8) || D83.equals(d8) || D84.equals(d8));
                }
            }).count();
        }

        private long getM11(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
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

    private static class ForkJoinTaskForHouseHold extends RecursiveTask<List<ExportHouseHoldVO>> {

        private static final int THRESHOLD = 1000;


        private List<List<ExportHouseHoldDataVO>> personInfoList;
        private int start;
        private int end;

        public ForkJoinTaskForHouseHold(List<List<ExportHouseHoldDataVO>> personInfoList, int start, int end) {
            this.personInfoList = personInfoList;
            this.start = start;
            this.end = end;
        }

        private List<ExportHouseHoldVO> dealExportHouseHoldDataVO(int start, int end) {
            ArrayList<ExportHouseHoldVO> exportHouseHoldVOS = new ArrayList<>();
            for (; start < end; start++) {
                List<ExportHouseHoldDataVO> exportHouseHoldDataVOList = this.personInfoList.get(start);
                ExportHouseHoldDataVO exportHouseHoldDataVO = exportHouseHoldDataVOList.get(0);
                ExportHouseHoldVO exportHouseHoldVO = new ExportHouseHoldVO();
                exportHouseHoldVO.setM3(exportHouseHoldDataVO.getRoomNum());
                //设置户主姓名
                exportHouseHoldVO.setM4(getM4(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM5(exportHouseHoldDataVO.getH2Live());
                exportHouseHoldVO.setM6(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D71, D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM7(getM7(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM8(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D71, D85).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM9(exportHouseHoldDataVO.getH2NoLive());
                exportHouseHoldVO.setM10(exportHouseHoldDataVOList.stream().filter(item -> StrUtil.join(CensusConstant.JOIN_CHAR, D72, D81).equals(item.getD7AndD8())).count());
                exportHouseHoldVO.setM11(getM11(exportHouseHoldDataVOList));
                exportHouseHoldVO.setM12(exportHouseHoldDataVO.getH3());
                exportHouseHoldVO.setM13(exportHouseHoldDataVO.getH4());
                exportHouseHoldVO.setM16(exportHouseHoldDataVO.getFillPersonPhone());
                exportHouseHoldVOS.add(exportHouseHoldVO);
            }
            return exportHouseHoldVOS;
        }

        private String getM4(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
            for (ExportHouseHoldDataVO exportHouseHoldDataVO : exportHouseHoldDataVOList) {
                if ("户主".endsWith(exportHouseHoldDataVO.getD2())) {
                    return exportHouseHoldDataVO.getD1();
                }
            }
            return "无";
        }

        private long getM7(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
            return exportHouseHoldDataVOList.stream().filter(new Predicate<ExportHouseHoldDataVO>() {
                @Override
                public boolean test(ExportHouseHoldDataVO exportHouseHoldDataVO) {
                    String d7 = exportHouseHoldDataVO.getD7();
                    String d8 = exportHouseHoldDataVO.getD8();
                    return D71.equals(d7) && (D82.equals(d8) || D83.equals(d8) || D84.equals(d8));
                }
            }).count();
        }

        private long getM11(List<ExportHouseHoldDataVO> exportHouseHoldDataVOList) {
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
            if ((end - start) <= THRESHOLD) {
                return dealExportHouseHoldDataVO(start, end);
            }

            int middle = (start + end) / 2;
            ForkJoinTaskForHouseHold left = new ForkJoinTaskForHouseHold(personInfoList, start, middle);
            ForkJoinTaskForHouseHold right = new ForkJoinTaskForHouseHold(personInfoList, middle, end);
            left.fork();
            right.fork();
            List<ExportHouseHoldVO> leftJoin = left.join();
            leftJoin.addAll(right.join());
            return leftJoin;
        }
    }

    @Override
    public List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfo(CensusSurveyQueryQO censusSurveyQueryQO) {
        List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfo = roomAddressMapper.findExportHouseHoldAndPersonInfo(censusSurveyQueryQO);
        List<ExportHouseHoldAndPersonInfoVO> result = new ArrayList<>(exportHouseHoldAndPersonInfo.size() * 2);
        int index = 1;
        List<Integer> idList = new ArrayList<>();
        Set<Integer> idSet = new HashSet<>();
        for (ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO : exportHouseHoldAndPersonInfo) {
            if(!idSet.contains(exportHouseHoldAndPersonInfoVO.getRoomAddressId())){
                idSet.add(exportHouseHoldAndPersonInfoVO.getRoomAddressId());
                idList.add(exportHouseHoldAndPersonInfoVO.getRoomAddressId());
            }

        }
        Map<Integer, List<ExportHouseHoldAndPersonInfoVO>> integerListMap = exportHouseHoldAndPersonInfo.stream().collect(Collectors.groupingBy(ExportHouseHoldAndPersonInfoVO::getRoomAddressId));
        for (Integer id : idList) {
            List<ExportHouseHoldAndPersonInfoVO> tempResultList = new ArrayList<>();
            List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOS = integerListMap.get(id);
            ExportHouseHoldAndPersonInfoVO first = null;
            ExportHouseHoldAndPersonInfoVO second = null;
            for (ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO : exportHouseHoldAndPersonInfoVOS) {
                exportHouseHoldAndPersonInfoVO.setIndex(index++);
                if (first ==  null && "户主".equals(exportHouseHoldAndPersonInfoVO.getD2())) {
                    first = exportHouseHoldAndPersonInfoVO;
                } else if (second == null && "配偶".equals(exportHouseHoldAndPersonInfoVO.getD2())) {
                    second = exportHouseHoldAndPersonInfoVO;
                } else {
                    tempResultList.add(dealMerge(exportHouseHoldAndPersonInfoVO));
                }
            }

            Boolean isFirstNull = first == null;
            if (first == null) {
                first = exportHouseHoldAndPersonInfoVOS.get(0);
            }

            first.setH2LiveCompare(getH2LiveCompare(exportHouseHoldAndPersonInfoVOS));
            first.setH2Live(getH2LiveCompare(exportHouseHoldAndPersonInfoVOS).toString());

            first.setH2NoLiveCompare(getH2NoLiveCompare(exportHouseHoldAndPersonInfoVOS));
            first.setH2NoLive(getH2NoLiveCompare(exportHouseHoldAndPersonInfoVOS).toString());

            first.setH2LiveCompareResult(getH2LiveCompareResult(first.getH2Live(), first.getH2LiveCompare().toString()));

            first.setH2NoLiveCompareResult(getH2NoLiveCompareResult(first.getH2NoLive(), first.getH2NoLiveCompare().toString()));
            if(isFirstNull){
                tempResultList.remove(0);
            }
            if (second != null) {
                second = dealMerge(second);
                tempResultList.add(0, second);
            }
            if((Integer.parseInt(first.getH2Live())+Integer.parseInt(first.getH2NoLive()) == 1)){
                first.setH1("家庭户");
            }
            first.setH3Man(getH3Man(exportHouseHoldAndPersonInfoVOS).toString());
            first.setH3Woman(getH3Woman(exportHouseHoldAndPersonInfoVOS).toString());
            tempResultList.add(0, first);

            result.addAll(tempResultList);
        }
        return result;
    }

    /**
     *                and zmn_census_person_info.d4 = '男' and zmn_census_person_info.d5 >='2019-11-1' and zmn_census_person_info <='2020-10-31' );
     * @param exportHouseHoldAndPersonInfoVOS
     * @return
     */

    private static final String startTime = "2019-10";
    private static final String endTime = "2020-11";

    private Long getH3Man(List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOS) {
        return exportHouseHoldAndPersonInfoVOS.stream().filter(new Predicate<ExportHouseHoldAndPersonInfoVO>() {
            @Override
            public boolean test(ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO) {
                String d4 = exportHouseHoldAndPersonInfoVO.getD4();
                String d5 = exportHouseHoldAndPersonInfoVO.getD5();
                return "男".equals(d4) && (d5.compareTo(startTime) > 0) && (endTime.compareTo(d5) > 0);
            }
        }).count();
    }

    private Long getH3Woman(List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOS) {
        return exportHouseHoldAndPersonInfoVOS.stream().filter(new Predicate<ExportHouseHoldAndPersonInfoVO>() {
            @Override
            public boolean test(ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO) {
                String d4 = exportHouseHoldAndPersonInfoVO.getD4();
                String d5 = exportHouseHoldAndPersonInfoVO.getD5();
                return "女".equals(d4) && (d5.compareTo(startTime) > 0) && (endTime.compareTo(d5) > 0);
            }
        }).count();
    }

    private Long getH2LiveCompare(List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOS) {
        return exportHouseHoldAndPersonInfoVOS.stream().filter(new Predicate<ExportHouseHoldAndPersonInfoVO>() {
            @Override
            public boolean test(ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO) {
                String d7 = exportHouseHoldAndPersonInfoVO.getD7();
                return D71.equals(d7);
            }
        }).count();
    }


    private String getH2LiveCompareResult(String h2Live, String h2LiveCompare) {
        return h2LiveCompare.equals(h2Live) ? "是" : "否";
    }


    private Long getH2NoLiveCompare(List<ExportHouseHoldAndPersonInfoVO> exportHouseHoldAndPersonInfoVOS) {
        return exportHouseHoldAndPersonInfoVOS.stream().filter(new Predicate<ExportHouseHoldAndPersonInfoVO>() {
            @Override
            public boolean test(ExportHouseHoldAndPersonInfoVO exportHouseHoldDataVO) {
                String d7 = exportHouseHoldDataVO.getD7();
                String d8 = exportHouseHoldDataVO.getD8();
                return !D71.equals(d7) && D81.equals(d8);
            }
        }).count();
    }

    private String getH2NoLiveCompareResult(String h2NoLive, String h2NoLiveCompare) {
        return h2NoLiveCompare.equals(h2NoLive) ? "是" : "否";
    }

    private ExportHouseHoldAndPersonInfoVO dealMerge(ExportHouseHoldAndPersonInfoVO exportHouseHoldAndPersonInfoVO) {
//        exportHouseHoldAndPersonInfoVO.setCommunity("");
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
