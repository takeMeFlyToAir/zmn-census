import api from './index'
// axios
import request from '@/utils/request'

// 查询某个小区
export function community_getById(params) {
  return request({
    url: api.community.getById,
    method: 'get',
    params,
    hideloading: true
  })
}

// 查询所有街道
export function community_findAllTown(params) {
  return request({
    url: api.community.findAllTown,
    method: 'get',
    params,
    hideloading: true
  })
}

// 根据街道查询普查小区
export function community_findVillageByTown(params) {
  return request({
    url: api.community.findVillageByTown,
    method: 'get',
    params,
    hideloading: true
  })
}

// 根据街道和普查小区查询住宅小区
export function community_findCommunityByTownAndVillage(params) {
  return request({
    url: api.community.findCommunityByTownAndVillage,
    method: 'get',
    params,
    hideloading: true
  })
}

// 获取小区列表
export function community_list(params) {
  return request({
    url: api.community.list,
    method: 'get',
    params,
    hideloading: true
  })
}
