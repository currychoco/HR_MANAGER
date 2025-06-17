<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/lib/axios'

const emp = ref<any>(null)
const router = useRouter()

onMounted(async () => {
  try {
    const response = await api.get('/get/employee/all/info')
    emp.value = response.data
  } catch (err) {
    console.error(err)
  }
})

function modifyOwnInfo() {
  if (emp.value?.empNo) {
    router.push(`/home/employee/modify?empNo=${emp.value.empNo}`)
  }
}
</script>

<template>
<div class="container body-container">
    <h3 class="title">나의 인사정보</h3>
    <div class="form-group">
        <table class="table table-hover">
        <tbody v-if="emp">
            <tr><th>사번</th><td>{{ emp.empNo }}</td></tr>
            <tr><th>이름</th><td>{{ emp.empName }}</td></tr>
            <tr><th>영문명</th><td>{{ emp.empNameEn }}</td></tr>
            <tr><th>부서</th><td>{{ emp.deptName }}</td></tr>
            <tr><th>직책</th><td>{{ emp.jobName }}</td></tr>
            <tr><th>직위</th><td>{{ emp.positionName }}</td></tr>
            <tr><th>성별</th><td>{{ emp.gender }}</td></tr>
            <tr><th>이메일</th><td>{{ emp.email }}</td></tr>
            <tr><th>휴대폰 번호</th><td>{{ emp.phone }}</td></tr>
            <tr><th>입사일</th><td>{{ emp.startDateToString }}</td></tr>
            <tr><th>아이디</th><td>{{ emp.accountId }}</td></tr>
            <tr><th>주소</th><td>{{ emp.zipCode }} {{ emp.address1 }} {{ emp.address2 }}</td></tr>
        </tbody>
        </table>
    </div>
    <div class="form-group">
        <button class="btn btn-primary btn-block" @click="modifyOwnInfo">
        나의 인사정보 수정요청
        </button>
    </div>
</div>
</template>

<style>
tbody th, td {
  padding: 20px 0px !important;
}
</style>