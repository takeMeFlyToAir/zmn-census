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
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
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
    redirect: '/dashboard',
    name: '首页',
    children: [
      {
        path: 'dashboard',
        name: '首页',
        component: _import('dashboard/index'),
        meta: {title: '首页', icon: 'dashboard'},
      },
    ]
  },
  {
    path: '/census',
    component: Layout,
    redirect: '/census/',
    name: '',
    meta: {title: '问卷统计', icon: 'eye-open'},
    children: [
      {
        path: '',
        name: '问卷统计',
        component: _import('census/index'),
        meta: {title: '问卷统计', icon: 'eye-open'},
        menu: 'census'
      },
    ]
  },
  {
    path: '/community',
    component: Layout,
    redirect: '/community/',
    name: '',
    meta: {title: '小区管理', icon: 'tree'},
    children: [
      {
        path: '',
        name: '小区管理',
        component: _import('community/index'),
        meta: {title: '小区管理', icon: 'business1'},
        menu: 'community'
      },
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'user'},
    children: [
      {
        path: '',
        name: '用户列表',
        component: _import('user/user'),
        meta: {title: '用户列表', icon: 'user'},
        menu: 'user'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
