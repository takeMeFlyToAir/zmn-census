<template>
 <div class="dashboard-editor-container">
   <panel-group :business="commonCount"/>
   <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
     <line-chart :chartData="surveyCount"/>
   </el-row>
   <!--<el-row :gutter="32">-->
     <!--<el-col :xs="24" :sm="24" :lg="12">-->
       <!--<div class="chart-wrapper">-->
         <!--<line-chart :chartData="jjpc_registerTypeRatio"/>-->
       <!--</div>-->
     <!--</el-col>-->
     <!--<el-col :xs="24" :sm="24" :lg="12">-->
       <!--<div class="chart-wrapper">-->
         <!--<bar-for-x-chart :chartData="jjpc_fiveUpAndDownRatio"/>-->
       <!--</div>-->
     <!--</el-col>-->
   <!--</el-row>-->
   <!--<el-row :gutter="32">-->
     <!--<el-col :xs="24" :sm="24" :lg="8">-->
       <!--<div class="chart-wrapper">-->
         <!--<pie-chart :chartData="jjpc_twoAndThreeRatio"/>-->
       <!--</div>-->
     <!--</el-col>-->
     <!--<el-col :xs="24" :sm="24" :lg="8">-->
       <!--<div class="chart-wrapper">-->
         <!--<pie-chart :chartData="jjpc_parkRatio"/>-->
       <!--</div>-->
     <!--</el-col>-->
     <!--<el-col :xs="24" :sm="24" :lg="8">-->
       <!--<div class="chart-wrapper">-->
         <!--<pie-chart :chartData="jjpc_sizeRatio"/>-->
       <!--</div>-->
     <!--</el-col>-->
   <!--</el-row>-->
 </div>
</template>

<script>
  import PanelGroup from './PanelGroup'
  import PieChart from '@/components/echarts/PieChart'
  import BarForXChart from '@/components/echarts/BarForXChart'
  import LineChart from '@/components/echarts/LineChart'

  import { apiCensusSurveyCommonCount, apiCensusSurveySurveyCount} from '@/api/censusSurvey'

 import {jjpc_panelGroupData,jjpc_twoAndThreeRatio,jjpc_tradeRatio,jjpc_parkRatio,jjpc_sizeRatio,jjpc_registerTypeRatio,jjpc_fiveUpAndDownRatio} from './businessJJPCData'
  export default {
    name: 'DashboardAdmin',
    components: {
      PanelGroup,
      LineChart,
      PieChart,
      BarForXChart,
    },
    data() {
      return {
        commonCount:{
          allCommunityCount: 0,
          allSurveyCount: 0,
          allPersonCount: 0,
          yesterdaySurveyCount: 0,
          todaySurveyCount: 0,
        },
        surveyCount:{
          title:'问卷数和人数',
          name:'问卷数和人数',
          legend: {
            data: ['问卷数', '人数']
          },
          xData: [],
          series:[
            {
              name: '问卷数',
              type: 'line',
              data: [],
              animationDuration:6000
            },
            {
              name: '人数',
              type: 'line',
              data: [],
              animationDuration:6000
            }
          ]
        },
        jjpc_panelGroupData:jjpc_panelGroupData,
        jjpc_twoAndThreeRatio:jjpc_twoAndThreeRatio,
        jjpc_tradeRatio:jjpc_tradeRatio,
        jjpc_parkRatio:jjpc_parkRatio,
        jjpc_sizeRatio:jjpc_sizeRatio,
        jjpc_registerTypeRatio:jjpc_registerTypeRatio,
        jjpc_fiveUpAndDownRatio:jjpc_fiveUpAndDownRatio,
      }
    },
    methods: {
      getCommonCount(){
        let param = {}
        apiCensusSurveyCommonCount(param).then((res) => {
          if(res.code == 200){
            this.commonCount = res.data
          }
        })
      },
      getSurveyCount(){
        let param = {}
        apiCensusSurveySurveyCount(param).then((res) => {
          if(res.code == 200){
            let xData = [];
            let surveyData = [];
            let personData = [];
            res.data.forEach(item => {
              xData.push(item.time);
              surveyData.push(item.surveyCount);
              personData.push(item.personCount);
            })
            this.surveyCount.xData = xData;
            this.surveyCount.series[0].data = surveyData;
            this.surveyCount.series[1].data = personData;
          }
        })
      },
    },
    created() {
      this.getCommonCount();
      this.getSurveyCount();
    }
  }
</script>


<style lang="scss" scoped>
  .dashboard-editor-container {
    padding: 32px;
    background-color: rgb(240, 242, 245);
    position: relative;

    .github-corner {
      position: absolute;
      top: 0px;
      border: 0;
      right: 0;
    }

    .chart-wrapper {
      background: #fff;
      padding: 16px 16px 0;
      margin-bottom: 32px;
    }
  }

  @media (max-width:1024px) {
    .chart-wrapper {
      padding: 8px;
    }
  }
</style>
