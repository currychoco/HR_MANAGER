<template>
  <div class="container body-container">
    <h3 class="title">명함신청 관리</h3>
    <table class="table">
      <thead>
        <tr>
          <th>목록</th>
          <th>명함 신청 정보</th>
        </tr>
      </thead>
      <tbody>
        <tr><td>한글명</td><td>{{ busiCard.empName }}</td></tr>
        <tr><td>영문명</td><td>{{ busiCard.empNameEn }}</td></tr>
        <tr><td>부서한글명</td><td>{{ busiCard.deptName }}</td></tr>
        <tr><td>부서영문명</td><td>{{ busiCard.deptNameEn }}</td></tr>
        <tr><td>직책한글명</td><td>{{ busiCard.jobName }}</td></tr>
        <tr><td>직책영문명</td><td>{{ busiCard.jobNameEn }}</td></tr>
        <tr><td>직급한글명</td><td>{{ busiCard.positionName }}</td></tr>
        <tr><td>직급영문명</td><td>{{ busiCard.positionNameEn }}</td></tr>
        <tr><td>이메일</td><td>{{ busiCard.email }}</td></tr>
        <tr><td>휴대폰번호</td><td>{{ busiCard.phone }}</td></tr>
      </tbody>
    </table>
    <div>
      <button class="btn btn-primary btn-block" @click="busiCardAllow">명함 신청 승인</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '@/lib/axios'

const route = useRoute()
const router = useRouter()

const busiCard = ref({
  no: '',
  empNo: '',
  empName: '',
  empNameEn: '',
  deptName: '',
  deptNameEn: '',
  jobName: '',
  jobNameEn: '',
  positionName: '',
  positionNameEn: '',
  email: '',
  phone: ''
})

const fetchBusiCardInfo = async () => {
  const no = route.query.no
  const empNo = route.query.empNo
  try {
    const res = await api.get(`/manager/busi-card-allow/${no}`)
    busiCard.value = res.data
  } catch (err: any) {
    alert(err.response?.data?.message || '정보 조회 실패')
  }
}

const busiCardAllow = async () => {
  try {
    await api.post('/allow/busi-card', {
      ...busiCard.value
    })
    alert('명함 신청 승인 완료')
    router.push('/manager/busi-card-allow/list')
  } catch (err: any) {
    alert(err.response?.data?.message || '승인 실패')
  }
}

onMounted(() => {
  fetchBusiCardInfo()
})
</script>
