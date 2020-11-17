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

// 更新问卷
export function censusSurvey_update(data) {
  return request({
    url: api.censusSurvey.update,
    method: 'post',
    data,
    hideloading: false
  })
}

// 根据id查询问卷
export function censusSurvey_getById(params) {
  return request({
    url: api.censusSurvey.getById,
    method: 'get',
    params,
    hideloading: true
  })
}
