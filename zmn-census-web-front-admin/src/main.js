import Vue from 'vue'
import 'normalize.css/normalize.css'// A modern alternative to CSS resets
import './assets/scss/base.scss';

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import App from './App'
import router from './router'
import store from './store'
import '@/icons' // icon
import '@/permission' // 权限
import {default as api} from './utils/api'
import {hasPermission} from "./utils/hasPermission";
Vue.use(ElementUI, {locale})
Vue.prototype.api = api

import Highchart from "highcharts/highcharts";
import HighchartsVue from "highcharts-vue";

import stockInit from "highcharts/modules/stock";
import exportingInit from 'highcharts/modules/exporting'
import sankeyInit from 'highcharts/modules/sankey'
import mapInit from 'highcharts/modules/map'
import addWorldMap from './data/worldmap'

import VueAMap from 'vue-amap'
Vue.use(VueAMap)
VueAMap.initAMapApiLoader({
  key: 'Xd7Qfmr9COAYauqwAryb6Lk8pgOXrQ6M',
  plugin: ['AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PlaceSearch', 'AMap.Geolocation', 'AMap.Geocoder'],
  v: '1.4.4',
  uiVersion: '1.0'
})

import BaiduMap from 'vue-baidu-map'
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: '7Zcsov0z6X24qpCfkQ9KbsDBhbQZT31k'
})


import darkUnicaInit from 'highcharts/themes/dark-unica'

exportingInit(Highchart)
stockInit(Highchart);
mapInit(Highchart);
sankeyInit(Highchart);

addWorldMap(Highchart)


darkUnicaInit(Highchart);
Vue.use(HighchartsVue);

//全局的常量
Vue.prototype.hasPerm = hasPermission
//生产环境时自动设置为 false 以阻止 vue 在启动时生成生产提示。
Vue.config.productionTip = (process.env.NODE_ENV != 'production')
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
