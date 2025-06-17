<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'
import { useRoute } from 'vue-router'

interface Employee {
  empNo: string
  empName: string
  empNameEn: string
  id: string
  gender: string
  deptCode: string
  jobCode: string
  positionCode: string
  email: string
  phone: string
  zipCode: string
  address1: string
  address2: string
}

const route = useRoute()
const employee = ref<Employee>({
  empNo: '', empName: '', empNameEn: '', id: '', gender: 'M',
  deptCode: '', jobCode: '', positionCode: '', email: '',
  phone: '', zipCode: '', address1: '', address2: ''
})

const departments = ref<any[]>([])
const jobs = ref<any[]>([])
const positions = ref<any[]>([])
const salaryAmount = ref('')

const hasGrant = true // 실제 권한 체크 로직 필요
const hasSalaryAccess = true // 실제 권한 체크 로직 필요

function updateEmployee() {
  const emp = employee.value
  if (!emp.empNo || !emp.deptCode || !emp.jobCode || !emp.positionCode || !emp.empName || !emp.empNameEn || !emp.gender || !emp.email || !emp.phone || !emp.zipCode || !emp.address1 || !emp.address2) {
    alert('누락된 정보가 있습니다.')
    return
  }

  api.post('/employee/update', emp)
    .then(() => alert('회원 정보 수정이 완료되었습니다 :)'))
    .catch(err => {
      if (err.response?.data?.message) alert(err.response.data.message)
    })
}

function openSalaryPopup() {
  window.open(`/manager/salary/detail?empNo=${employee.value.empNo}`, 'salary', 'width=600, height=400')
}

onMounted(async () => {
  const empNo = route.query.empNo || ''
  if (typeof empNo === 'string' && empNo) {
    try {
      const res = await api.get(`/manager/employee2/${empNo}`)
      employee.value = res.data
    } catch (err: any) {
      alert(err.response?.data?.message || '사원 정보를 불러오는 데 실패했습니다.')
    }
  }

  // 옵션 목록도 동시에 조회하는 예시
  const [deptRes, jobRes, posRes] = await Promise.all([
    api.get('/department/all'),
    api.get('/job/all/read'),
    api.get('/position/all/read')
  ])
  departments.value = deptRes.data
  jobs.value = jobRes.data
  positions.value = posRes.data
})
</script>

<template>
  <div class="container body-container">
    <h3 class="title">사원 관리</h3>
    <form @submit.prevent="updateEmployee">
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.empNo" placeholder="사번" readonly />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.empName" placeholder="이름" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.empNameEn" placeholder="영문명" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" :value="employee.id || '-'" placeholder="아이디" readonly />
      </div>
      <div class="form-group">
        <select class="form-control" v-model="employee.gender" :disabled="!hasGrant" required>
          <option value="M">남성</option>
          <option value="F">여성</option>
        </select>
      </div>
      <div class="form-group">
        <select class="form-control" v-model="employee.deptCode" :disabled="!hasGrant" required>
          <option v-for="d in departments" :key="d.deptCode" :value="d.deptCode">{{ d.deptName }}</option>
        </select>
      </div>
      <div class="form-group">
        <select class="form-control" v-model="employee.jobCode" :disabled="!hasGrant" required>
          <option v-for="j in jobs" :key="j.jobCode" :value="j.jobCode">{{ j.jobName }}</option>
        </select>
      </div>
      <div class="form-group">
        <select class="form-control" v-model="employee.positionCode" :disabled="!hasGrant" required>
          <option v-for="p in positions" :key="p.positionCode" :value="p.positionCode">{{ p.positionName }}</option>
        </select>
      </div>
      <div class="form-group">
        <input type="email" class="form-control" v-model="employee.email" placeholder="이메일" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.phone" placeholder="휴대폰" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.zipCode" placeholder="우편번호" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.address1" placeholder="주소" :readonly="!hasGrant" required />
      </div>
      <div class="form-group">
        <input type="text" class="form-control" v-model="employee.address2" placeholder="상세 주소" :readonly="!hasGrant" required />
      </div>
      <div v-if="hasSalaryAccess" class="form-group">
        <button type="button" class="btn btn-basic btn-block" @click="openSalaryPopup">연봉정보 조회</button>
      </div>
      <div class="form-group">
        <input type="hidden" v-model="salaryAmount" />
      </div>
      <div v-if="hasGrant" class="form-group">
        <button type="submit" class="btn btn-primary btn-block">수정</button>
      </div>
    </form>
  </div>
</template>

<style scoped>
.body-container {
  padding: 2rem;
}
</style>
