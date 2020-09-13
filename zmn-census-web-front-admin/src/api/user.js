import api from '@/utils/api'

export function apiUserGetList(params) {
  return api({
    url: '/user/findPage',
    method: 'get',
    params: params
  })
}

export function apiUserAdd(params) {
  return api({
    url: '/user/save',
    method: 'post',
    params: params
  })
}


export function apiUserEdit(params) {
  return api({
    url: '/user/edit',
    method: 'post',
    params: params
  })
}

export function apiUserDelete(params) {
  return api({
    url: '/user/delete',
    method: 'post',
    params: params

  })
}

export function apiUserExistPhone(params) {
  return api({
    url: '/user/existPhone',
    method: 'get',
    params: params
  })
}

export function apiUserExistUserName(params) {
  return api({
    url: '/user/existUserName',
    method: 'get',
    params: params
  })
}

export function apiUserChangePassword(params) {
  return api({
    url: '/user/changePassword',
    method: 'post',
    params: params
  })
}

export function apiUserFindList(params) {
  return api({
    url: '/user/findList',
    method: 'get',
    params: params
  })
}
