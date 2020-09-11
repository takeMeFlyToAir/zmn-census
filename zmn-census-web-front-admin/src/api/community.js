import api from '@/utils/api'

export function apiGetList(params) {
  return api({
    url: '/community/findPage',
    method: 'get',
    params: params
  })
}

export function apiAdd(params) {
  return api({
    url: '/community/save',
    method: 'post',
    params: params
  })
}


export function apiEdit(params) {
  return api({
    url: '/community/edit',
    method: 'post',
    params: params
  })
}

export function apiDelete(params) {
  return api({
    url: '/community/delete',
    method: 'post',
    params: params

  })
}
