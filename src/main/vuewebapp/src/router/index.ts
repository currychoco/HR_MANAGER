import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', name: 'home', component: HomeView,},
    {path: '/about', name: 'about', component: () => import('../views/AboutView.vue'),},
    {path: '/login', name: 'login', component: () => import('../views/Login.vue')},
    {path: '/business-card', name: 'application', component: () => import('../views/businessCard/ApplicationCard.vue')},
    {path: '/empinfo', name: 'empinfo', component: () => import('../views/employee/EmpInfo.vue')},
    {path: '/home/employee/modify', name: 'employeeModifiy', component: () => import('../views/employee/ModifiyEmpInfo.vue')},
    {path: '/englishname', name: 'englishname', component: () => import('../views/employee/RecommendEnglishName.vue'), meta: {layout: 'none'}}
  ],
})

export default router
