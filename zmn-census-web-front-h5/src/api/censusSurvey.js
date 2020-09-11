import api from './index'
// axios
import request from '@/utils/request'

// 保存问卷
export function censusSurvey_save(data) {
  return request({
    url: api.censusSurvey.save,
    method: 'post',
    data,
    hideloading: false
  })
}
