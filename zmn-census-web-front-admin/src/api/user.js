import api from '@/utils/api'

export function apiGetList(params) {
  return api({
    url: '/user/users',
    method: 'get',
    params: params
  })
}

export function apiAdd(params) {
  return api({
    url: '/user/users',
    method: 'post',
    params: params
  })
}


export function apiEdit(params) {
  return api({
    url: '/user/users',
    method: 'put',
    params: params
  })
}

export function apiDelete(id) {
  return api({
    url: '/user/users/'+id,
    method: 'delete'
  })
}

export function apiExistPhone(params) {
  return api({
    url: '/user/users/existPhone',
    method: 'get',
    params: params
  })
}

export function apiExistUserName(params) {
  return api({
    url: '/user/users/existUserName',
    method: 'get',
    params: params
  })
}

export function changePassword(params) {
  return api({
    url: '/user/users/changePassword',
    method: 'put',
    params: params
  })
}
