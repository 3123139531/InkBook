import { createRouter, createWebHashHistory } from 'vue-router'

const Homepage = () => import(/* webpackChunkName: "about" */ '../views/Homepage.vue')
const ProjectInfo = () => import(/* webpackChunkName: "about" */ '../views/ProjectInfo.vue')
const TeamInfo = () => import(/* webpackChunkName: "about" */ '../views/TeamInfo.vue')

const routes = [
  {
    path: '/',
    name: 'Homepage',
    component: Homepage
  },
  {
    path: '/ProjectInfo',
    name: 'ProjectInfo',
    component: ProjectInfo
  },
  {
    path: '/TeamInfo',
    name: 'TeamInfo',
    component: TeamInfo
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
