<template>
  <div class="dashboard-editor-container">
    暂未开放
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
