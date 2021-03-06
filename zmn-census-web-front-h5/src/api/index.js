const api = {
  Login: '/user/login',
  UserInfo: '/user/userinfo',
  UserName: '/user/name',
  community: {
    getById:  '/community/getById',
    findAllTown:  '/community/findAllTown',
    findVillageByTown:  '/community/findVillageByTown',
    findCommunityByTownAndVillage:  '/community/findCommunityByTownAndVillage',
    list:  '/community/list',
  },
  censusSurvey: {
    save: '/censusSurvey/save',
    update: '/censusSurvey/update',
    getById: '/censusSurvey/getById'
  }
}

export default api
