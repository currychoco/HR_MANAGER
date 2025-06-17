import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/', name: 'home', component: () => import('../views/employee/EmpInfo.vue')},
    {path: '/login', name: 'login', component: () => import('../views/Login.vue')},
    {path: '/empinfo', name: 'empinfo', component: () => import('../views/employee/EmpInfo.vue')},
    {path: '/home/employee/modify', name: 'employeeModifiy', component: () => import('../views/employee/ModifiyEmpInfo.vue')},
    {path: '/englishname', name: 'englishname', component: () => import('../views/employee/RecommendEnglishName.vue'), meta: {layout: 'none'}},

    {path: '/business-card', name: 'application', component: () => import('../views/businessCard/ApplicationCard.vue')},

    {path: '/manager/employee/search', name: 'managerEmployeeSearch', component: () => import('../views/manager/employee/ManagerEmployeeSearch.vue')},
    {path: '/manager/employee/add', name: 'managerEmployeeAdd', component: () => import('../views/manager/employee/ManagerEmployeeAdd.vue')},
    {path: '/manager/employee/detail', name: 'managerEmployeeDetail', component: () => import('../views/manager/employee/ManagerEmployeeDetail.vue')},

    {path: '/manager/department/search', name: 'managerDepartmentSearch', component: () => import('../views/manager/department/ManagerDepartmentSearch.vue')},
    {path: '/manager/department/create', name: 'managerDepartmentCreate', component: () => import('../views/manager/department/ManagerDepartmentCreate.vue')},
    {path: '/manager/department/detail', name: 'managerDepartmentDetail', component: () => import('../views/manager/department/ManagerDepartmentDetail.vue')},

    {path: '/manager/job/search', name: 'managerJobSearch', component: () => import('../views/manager/job/ManagerJobSearch.vue')},
    {path: '/manager/job/create', name: 'managerJobCreate', component: () => import('../views/manager/job/ManagerJobCreate.vue')},
    {path: '/manager/job/detail', name: 'managerJobDetail', component: () => import('../views/manager/job/ManagerJobDetail.vue')},

    {path: '/manager/position/search', name: 'managerPositionSearch', component: () => import('../views/manager/position/ManagerPositionSearch.vue')},
    {path: '/manager/position/create', name: 'managerPositionCreate', component: () => import('../views/manager/position/ManagerPositionCreate.vue')},
    {path: '/manager/position/detail', name: 'managerPositionDetail', component: () => import('../views/manager/position/ManagerPositionDetail.vue')},

    {path: '/manager/authority/grant', name: 'managerAuthorityGrant', component: () => import('../views/manager/authority/ManagerAuthorityGrant.vue')},

    {path: '/manager/allow/list', name: 'managerAllowList', component: () => import('../views/manager/allow/ManagerAllowList.vue')},
    {path: '/manager/allow/detail', name: 'managerAllowDetail', component: () => import('../views/manager/allow/ManagerAllowDetail.vue')},

    {path: '/manager/busi-card-allow/list', name: 'managerBusicardAllowList', component: () => import('../views/manager/allow/ManagerBusicardAllowList.vue')},
    {path: '/manager/busi-card-allow/detail', name: 'managerBusicardAllowDetail', component: () => import('../views/manager/allow/ManagerBusicardAllowDetail.vue')},
  ],
})

export default router
