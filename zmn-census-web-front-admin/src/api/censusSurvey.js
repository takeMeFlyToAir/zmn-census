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

export function apiCensusSurveyCommonCount(params) {
  return api({
    url: '/censusSurvey/commonCount',
    method: 'get',
    params: params
  })
}


export function apiCensusSurveySurveyCount(params) {
  return api({
    url: '/censusSurvey/surveyCount',
    method: 'get',
    params: params
  })
}

export function apiCensusSurveyEditRoomAddress(params) {
  return api({
    url: '/censusSurvey/editRoomAddress',
    method: 'post',
    params: params
  })
}


export function apiCensusSurveyEditHouseHold(params) {
  return api({
    url: '/censusSurvey/editHouseHold',
    method: 'post',
    params: params
  })
}

export function apiCensusSurveyEditPersonInfo(params) {
  return api({
    url: '/censusSurvey/editPersonInfo',
    method: 'post',
    params: params
  })
}
