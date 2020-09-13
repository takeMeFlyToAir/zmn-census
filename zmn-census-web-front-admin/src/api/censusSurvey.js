import api from '@/utils/api'

export function apiCensusSurveyGetList(params) {
  return api({
    url: '/censusSurvey/findPage',
    method: 'post',
    params: params
  })
}
export function apiCensusSurveyDelete(params) {
  return api({
    url: '/censusSurvey/delete',
    method: 'post',
    params: params
  })
}
