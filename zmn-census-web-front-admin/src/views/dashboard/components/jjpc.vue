<template>
 <div class="dashboard-editor-container">
   <panel-group :business="jjpc_panelGroupData"/>
   <!--<el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">-->
     <!--<pie-chart :chartData="jjpc_tradeRatio" :height="'550px'"/>-->
   <!--</el-row>-->
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

    },
    created() {

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
