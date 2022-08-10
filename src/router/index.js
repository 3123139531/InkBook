import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/HomeView.vue'
import Team from '../views/TeamView.vue'
import Project from '../views/ProjectView.vue'
import File from '../views/FileView.vue'
import Login from '../views/LoginView.vue'
import Register from '../views/RegisterView.vue'
import ProManage from "@/views/ProManage";
import DocCenter from "@/views/DocCenter";
import HomeInit from "@/views/HomeInit.vue";
import ForSkip from "@/views/ForSkip.vue";
import Design from "../views/DesignView.vue"
import SkipInvite from "@/views/SkipInvite";

const routes = [
  {
    path: '/',
    name: 'init',
    component: HomeInit
  },
  {
    path: '/skip',
    name: 'skip',
    component: ForSkip
  },
  {
    path: '/skipInvite',
    name: 'skipInvite',
    component: SkipInvite
  },
  {
    path: '/login',
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
  {
    path: '/design',
    name: 'design',
    component: Design
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
