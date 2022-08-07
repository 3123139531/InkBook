import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'
import Team from '../views/TeamView.vue'
import Project from '../views/ProjectView.vue'
import File from '../views/FileView.vue'
import Login from '../views/LoginView.vue'
import Register from '../views/RegisterView.vue'
import ProManage from "@/views/ProManage";
import DocCenter from "@/views/DocCenter";

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
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
  },
  {
    path: '/proManage',
    name: 'proManage',
    component: ProManage
  },
  {
    path: '/docCenter',
    name: 'docCenter',
    component: DocCenter
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
