/**
 * 基础路由
 * @type { *[] }
 */
export const constantRouterMap = [
  {
    path: '/',
    component: () => import('@/views/layouts/index'),
    redirect: '/censusSurvey',
    meta: {
      title: '人口普查',
      keepAlive: false
    },
    children: [
      {
        path: '/censusSurvey',
        name: 'censusSurvey',
        component: () => import('@/views/censusSurvey/index'),
        meta: { title: '人口普查', keepAlive: false }
      },
      {
        path: '/censusSample',
        name: 'censusSample',
        component: () => import('@/views/censusSample/index'),
        meta: { title: '人口普查抽样', keepAlive: false }
      },
      {
        path: '/home',
        name: 'Home',
        component: () => import('@/views/home/index'),
        meta: { title: '首页', keepAlive: false }
      }
    ]
  }
]
