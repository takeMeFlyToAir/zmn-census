<template>
  <div class="dashboard-container">
    <jjpc/>
    <!--<el-tabs v-model="display"  type="border-card" >-->
      <!--<el-tab-pane label="经济普查" name="jjpc" >-->
        <!--<jjpc v-if="display=='jjpc'"/>-->
      <!--</el-tab-pane>-->
      <!--<el-tab-pane label="五上企业" name="fiveUpBusiness">-->
        <!--<five-up v-if="display=='fiveUpBusiness'"/>-->
      <!--</el-tab-pane>-->
      <!--<el-tab-pane label="五下企业" name="fiveDownBusiness">-->
        <!--<five-down v-if="display=='fiveDownBusiness'"/>-->
      <!--</el-tab-pane>-->
    <!--</el-tabs>-->
  </div>
</template>

<script>
  import jjpc from './components/jjpc'
  import fiveDown from './components/fiveDown'
  import fiveUp from './components/fiveUp'
  export default {
    name: 'DashboardAdmin',
    components: {
      jjpc,
      fiveDown,
      fiveUp,
    },
    data() {
      return {
        display:'jjpc',
      }
    },
    methods: {

    },
    created() {

    }
  }
</script>

<style lang="scss" scoped>

</style>
