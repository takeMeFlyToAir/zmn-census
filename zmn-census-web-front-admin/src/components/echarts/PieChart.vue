<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    chartData: {
      type: Object,
      required: true
    }

  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    setOptions({title='',name = '', legend, data,roseType='radius'} = {}){
      this.chart.setOption({
        title: {
          text:title,
          textAlign:'left',
          textStyle: {
            fontFamily:'sans-serif',
            fontSize:14,
            color:'#666'
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: legend
        },
        toolbox: {
          show: true,
          feature: {
            dataView: {readOnly: false},
            saveAsImage: {}
          }
        },
        series: [
          {
            name: name,
            type: 'pie',
            roseType:roseType,
            radius: ['10%', '70%'],
            center: ['50%', '50%'],
            data: data,
            animationEasing: 'cubicInOut',
            animationDuration: 2600,
            //文字调整
            label: {
              fontSize: 10
            },
            //引导线
            labelLine: {
              length: 8,
              length2: 10
            }
          }
        ]
      })
    }
  }
}
</script>
