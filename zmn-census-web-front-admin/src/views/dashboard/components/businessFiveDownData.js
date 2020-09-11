const jjpc_panelGroupData = {
  frdwsl:11585,
  cyryqmrs:101312,
  // yysr:95694509192,
  yysr:95694,
  // zctj:313253971786,
  zctj:31325,
}

const jjpc_twoAndThreeRatio = {
  title:'产业占比',
  name:'产业占比',
  legend: ['第二产业','第三产业'],
  data:[
    { value: 2249, name: '第二产业' },
    { value: 9956, name: '第三产业' },
  ]
}

const jjpc_tradeRatio = {
  title:'行业占比',
  name:'行业占比',
  roseType:'',
  legend: ['采矿业','电力、热力、燃气及水生产和供应业','房地产业','公共管理、社会保障和社会组织','建筑业','交通运输、仓储和邮政业',
    '教育','金融业','居民服务、修理和其他服务业','科学研究和技术服务业','农、林、牧、渔业','批发和零售业','水利、环境和公共设施管理业',
    '卫生和社会工作','文化、体育和娱乐业','信息传输、软件和信息技术服务业','制造业','住宿和餐饮业','租赁和商务服务业'],
  data:[
    { value: 5, name: '采矿业' },
    { value: 28, name: '电力、热力、燃气及水生产和供应业' },
    { value: 734, name: '房地产业' },
    { value: 35, name: '公共管理、社会保障和社会组织' },
    { value: 1749, name: '建筑业' },
    { value: 314, name: '交通运输、仓储和邮政业' },
    { value: 110, name: '教育' },
    { value: 138, name: '金融业' },
    { value: 334, name: '居民服务、修理和其他服务业' },
    { value: 590, name: '科学研究和技术服务业' },
    { value: 10, name: '农、林、牧、渔业' },
    { value: 5054, name: '批发和零售业' },
    { value: 70, name: '水利、环境和公共设施管理业' },
    { value: 93, name: '卫生和社会工作' },
    { value: 204, name: '文化、体育和娱乐业' },
    { value: 429, name: '信息传输、软件和信息技术服务业' },
    { value: 512, name: '制造业' },
    { value: 184, name: '住宿和餐饮业' },
    { value: 1212, name: '租赁和商务服务业' },
  ]
}

const jjpc_parkRatio = {
  title:'园区占比',
  name:'园区占比',
  roseType:'area',
  legend: ['金融园区','商贸园区','湿地园区','总部园区','雁鸣湖园区','世园园区'],
  data:[
    { value: 1166, name: '金融园区' },
    { value: 4584, name: '商贸园区' },
    { value: 1661, name: '湿地园区' },
    { value: 2475, name: '总部园区' },
    { value: 753, name: '雁鸣湖园区' },
    { value: 1037, name: '世园园区' },
  ]
}

const jjpc_sizeRatio = {
  title:'规模占比',
  name:'规模占比',
  legend: ['大型','中型','小型','微型'],
  data:[
    { value: 9, name: '大型' },
    { value: 109, name: '中型' },
    { value: 625, name: '小型' },
    { value: 11062, name: '微型' },
  ]
}

const jjpc_registerTypeRatio = {
  title:'注册类型',
  name:'注册类型',
  xData: ['港澳台独资','港澳台商投资股份有限公司','股份合作','股份有限公司','国有','国有独资公司','国有联营','集体','其他',
    '其他联营','其他外商投资','其他有限责任公司','私营独资','私营股份有限责任公司','私营合伙','私营有限责任公司',
    '外商投资股份有限公司','外资企业','与港澳台商合资经营','与港澳台商合作经营','中外合资经营','中外合作经营',],
  series:[
    {
      type: 'line',
      barWidth: '60%',
      data: [16,3,5,193,48,12,1,24,52,1,9,4677,70,133,63,6478,1,5,3,1,9,1],
      animationDuration:6000
    }
  ]
}


const jjpc_fiveUpAndDownRatio = {
  title:'五上企业与五下企业',
  name:'五上企业与五下企业',
  xData: ['法人单位数量占比','从业人员占比','经济总量占比'],
  formatter: '{b} <br/>{a0} : {c0}% <br/>{a1} : {c1}% ',
  series: [{
    name: '五上企业',
    type: 'bar',
    stack: 'business',
    barWidth: '60%',
    data: [2, 28, 63],
    animationDuration:6000
  }, {
    name: '五下企业',
    type: 'bar',
    stack: 'business',
    barWidth: '60%',
    data: [98, 72, 37],
    animationDuration:6000
  }]
}


export {jjpc_panelGroupData,jjpc_twoAndThreeRatio,jjpc_tradeRatio,jjpc_parkRatio,jjpc_sizeRatio,jjpc_registerTypeRatio,jjpc_fiveUpAndDownRatio}
