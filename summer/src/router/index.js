import { createRouter, createWebHistory } from 'vue-router'
import Init from '../views/HomeInit.vue'
import Home from '../views/HomeView.vue'
import Team from '../views/TeamView.vue'
import Project from '../views/ProjectView.vue'
import File from '../views/FileView.vue'
import Login from '../views/LoginView.vue'
import Register from '../views/RegisterView.vue'
import Skip from '../views/ForSkip.vue'


const routes = [
{
    path: '/',
    name: 'init',
    component: Init
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
      path: '/skip',
      name: 'skip',
      component:Skip
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/project',
    name: 'project',
    component: Project
  },
  {
    path: '/team',
    name: 'team',
    component: Team
  },
  {
    path: '/file',
    name: 'file',
    component: File
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
