import api from '@/utils/api'

export function apiGetList(params) {
  return api({
    url: '/user/findPage',
    method: 'get',
    params: params
  })
}

export function apiAdd(params) {
  return api({
    url: '/user/save',
    method: 'post',
    params: params
  })
}


export function apiEdit(params) {
  return api({
    url: '/user/edit',
    method: 'post',
    params: params
  })
}

export function apiDelete(params) {
  return api({
    url: '/user/delete',
    method: 'post',
    params: params

  })
}

export function apiExistPhone(params) {
  return api({
    url: '/user/existPhone',
    method: 'get',
    params: params
  })
}

export function apiExistUserName(params) {
  return api({
    url: '/user/existUserName',
    method: 'get',
    params: params
  })
}

export function changePassword(params) {
  return api({
    url: '/user/changePassword',
    method: 'post',
    params: params
  })
}
