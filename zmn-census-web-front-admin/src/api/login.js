import api from '@/utils/api'

export function login(params) {
  return api({
    url: '/login/auth',
    method: 'post',
    params: params
  })
}

export function getInfo() {
  return api({
    url: '/login/getInfo',
    method: 'get'
  })
}

export function logout() {
  return api({
    url: '/login/logout',
    method: 'get'
  })
}
