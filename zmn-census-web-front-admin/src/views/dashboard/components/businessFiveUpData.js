const fiveUp_panelGroupData = {
  frdwsl:258,
  cyryqmrs:28101,
  // yysr:51637033000,
  yysr:51637,
  // zctj:169731536000,
  zctj:16973,
}

const fiveUp_twoAndThreeRatio = {
  title:'产业分布',
  name:'产业分布',
  legend: ['第二产业','第三产业'],
  data:[
    { value: 30, name: '第二产业' },
    { value: 228, name: '第三产业' },
  ]
}

const fiveUp_tradeRatio = {
  title:'行业分布',
  name:'行业分布',
  roseType:'',
  legend: ['房地产','工业','建筑业','批发零售业','住宿餐饮业','其他服务业'],
  data:[
    { value: 97, name: '房地产' },
    { value: 9, name: '工业' },
    { value: 20, name: '建筑业' },
    { value: 47, name: '批发零售业' },
    { value: 14, name: '住宿餐饮业' },
    { value: 72, name: '其他服务业' },
  ]
}

const fiveUp_parkRatio = {
  title:'园区分布',
  name:'园区分布',
  roseType:'area',
  legend: ['金融园区','商贸园区','湿地园区','总部园区','雁鸣湖园区','世园园区'],
  data:[
    { value: 65, name: '金融园区' },
    { value: 87, name: '商贸园区' },
    { value: 5, name: '湿地园区' },
    { value: 54, name: '总部园区' },
    { value: 13, name: '雁鸣湖园区' },
    { value: 35, name: '世园园区' },
  ]
}

const fiveUp_sizeRatio = {
  title:'企业规模',
  name:'企业规模',
  legend: ['大型','中型','小型','微型'],
  data:[
    { value: 9, name: '大型' },
    { value: 92, name: '中型' },
    { value: 77, name: '小型' },
    { value: 80, name: '微型' },
  ]
}

const fiveUp_businessDataRatio = {
  title:'企业数量',
  name:'企业数量',
  xData: ['2011','2012','2013','2014','2015','2016','2017','2018','2019'],
  series:[
    {
      type: 'line',
      barWidth: '60%',
      data: [39,60,80,130,147,187,223,258],
      animationDuration:6000
    }
  ]
}


const fiveUp_eightAreaRatio = {
  title:'八大开发区排名',
  name:'八大开发区排名',
  xData: ['航天','航空','港务','西咸新区','浐灞','曲江新区','经开','高新',],
  series:[
    {
      type: 'bar',
      barWidth: '60%',
      data: [98,131,153,201,258,296,301,372,],
      animationDuration:6000
    }
  ]
}


export {fiveUp_panelGroupData,fiveUp_twoAndThreeRatio,fiveUp_tradeRatio,fiveUp_parkRatio,fiveUp_sizeRatio,fiveUp_businessDataRatio,fiveUp_eightAreaRatio}
