import api from '@/utils/api'

export function apiGetByKey(params) {
  return api({
    url: '/config/getByKey',
    method: 'get',
    params: params
  })
}

