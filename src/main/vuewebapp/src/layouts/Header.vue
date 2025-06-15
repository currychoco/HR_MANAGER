<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { useAuth } from '@/composables/useAuth'
import { onMounted } from 'vue'
import api from '@/lib/axios'
import { useRouter } from 'vue-router'

const router = useRouter()

const { isAuthorized, empName, checkSession } = useAuth()

onMounted(async () => {
  await checkSession()
})

async function logout() {
  try {
    await api.post('/account/logout')
    alert('로그아웃 완료')
    window.location.href = '/login'
  } catch (err) {
    console.error(err)
  }
}
</script>

<template>
<header class="body-header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/" style="padding: 0px 10px;">
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/empinfo">내 정보</a></li>
                <li><a href="/business-card">명함 신청</a></li>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                        관리자 페이지<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">사원</li>
                        <li><a href="/manager/employee/search">사원 검색</a></li>
                        <li><a href="/manager/employee/add">사원 추가</a></li>
                        <li class="dropdown-header">부서</li>
                        <li><a href="/manager/department/search">부서 검색</a></li>
                        <li><a href="/manager/department/create">부서 생성</a></li>
                        <li class="dropdown-header">직책</li>
                        <li><a href="/manager/job/search">직책 검색</a></li>
                        <li><a href="/manager/job/create">직책 생성</a></li>
                        <li class="dropdown-header">직급</li>
                        <li><a href="/manager/position/search">직급 검색</a></li>
                        <li><a href="/manager/position/create">직급 생성</a></li>
                        <li class="dropdown-header">권한</li>
                        <li><a href="/manager/authority/grant">권한 부여</a></li>
                        <li class="dropdown-header">신청 승인</li>
                        <li><a href="/manager/allow/list">수정 신청 허가</a></li>
                        <li><a href="/manager/busi-card/allow">명함 신청 허가</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <template v-if="isAuthorized">
                    <li>
                        <p style="margin-top:13px;">Hello, <b>{{empName}}</b></p>
                    </li>
                    <li><a @click="logout"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>
                </template>
                <template v-else>
                    <li><router-link to="/join"><span class="glyphicon glyphicon-user"></span> 회원가입</router-link></li>
                    <li><router-link to="/login"><span class="glyphicon glyphicon-log-in"></span> 로그인</router-link></li>
                </template>
            </ul>
        </div>
    </nav>

</header>
</template>

<style scoped>
.dropdown-header {
    color: #337ab7;
}
</style>
