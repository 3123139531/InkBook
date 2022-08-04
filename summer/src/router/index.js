import { createRouter, createWebHashHistory } from 'vue-router'

const Login = () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
const Register = () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
const Homepage = () => import(/* webpackChunkName: "about" */ '../views/Homepage.vue')
const ProjectInfo = () => import(/* webpackChunkName: "about" */ '../views/ProjectInfo.vue')
const TeamInfo = () => import(/* webpackChunkName: "about" */ '../views/TeamInfo.vue')

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/Homepage',
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
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
