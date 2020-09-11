const getters = {
  sidebar: state => state.app.sidebar,
  visitedViews: state => state.app.visitedViews,

  nickName: state => state.user.nickName,
  userName: state => state.user.userName,
  userId: state => state.user.userId,
  avatar: state => state.user.avatar,
  roles: state => state.user.roles,
  menus: state => state.user.menus,
  permissions: state => state.user.permissions,

  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters
}
export default getters
