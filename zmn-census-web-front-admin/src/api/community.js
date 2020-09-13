import api from '@/utils/api'

export function apiCommunityGetList(params) {
  return api({
    url: '/community/findPage',
    method: 'get',
    params: params
  })
}

export function apiCommunityAdd(params) {
  return api({
    url: '/community/save',
    method: 'post',
    params: params
  })
}


export function apiCommunityEdit(params) {
  return api({
    url: '/community/edit',
    method: 'post',
    params: params
  })
}

export function apiCommunityDelete(params) {
  return api({
    url: '/community/delete',
    method: 'post',
    params: params

  })
}
