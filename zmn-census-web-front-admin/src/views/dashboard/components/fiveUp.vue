<template>
  <div class="dashboard-editor-container">
    <panel-group :business="fiveUp_panelGroupData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <pie-chart :chartData="fiveUp_tradeRatio" :height="'550px'"/>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <line-chart :chartData="fiveUp_businessDataRatio"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <div class="chart-wrapper">
          <bar-for-y-chart :chartData="fiveUp_eightAreaRatio" />
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chartData="fiveUp_twoAndThreeRatio"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chartData="fiveUp_parkRatio"/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chartData="fiveUp_sizeRatio"/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import PanelGroup from './PanelGroup'
  import PieChart from '@/components/echarts/PieChart'
  import BarForYChart from '@/components/echarts/BarForYChart'
  import LineChart from '@/components/echarts/LineChart'

  import {fiveUp_panelGroupData,fiveUp_twoAndThreeRatio,fiveUp_tradeRatio,fiveUp_parkRatio,fiveUp_sizeRatio,fiveUp_businessDataRatio,fiveUp_eightAreaRatio} from './businessfiveUpData'
  export default {
    name: 'DashboardAdmin',
    components: {
      PanelGroup,
      LineChart,
      PieChart,
      BarForYChart,
    },
    data() {
      return {
        fiveUp_panelGroupData:fiveUp_panelGroupData,
        fiveUp_twoAndThreeRatio:fiveUp_twoAndThreeRatio,
        fiveUp_tradeRatio:fiveUp_tradeRatio,
        fiveUp_parkRatio:fiveUp_parkRatio,
        fiveUp_sizeRatio:fiveUp_sizeRatio,
        fiveUp_businessDataRatio:fiveUp_businessDataRatio,
        fiveUp_eightAreaRatio:fiveUp_eightAreaRatio,
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
