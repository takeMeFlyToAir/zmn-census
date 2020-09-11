import api from '@/utils/api'

export function apiBusinessFindPage(params) {
  return api({
    url: '/business/findPage',
    method: 'get',
    params: params
  })
}

export function apiBusinessFindByCondition(params) {
  return api({
    url: '/business/findByCondition',
    method: 'get',
    params: params
  })
}

