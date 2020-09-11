import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/user',
    name: '用户',
    hidden: true,
    children: [{
      path: 'user', component: _import('user/user')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '',
    component: Layout,
    redirect: '/user/',
    name: '用户',
    children: [
      {
        path: 'user',
        name: '用户列表',
        component: _import('user/user'),
        meta: {title: '用户', icon: 'user'},
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
