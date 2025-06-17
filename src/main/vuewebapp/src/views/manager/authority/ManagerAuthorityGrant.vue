<template>
  <div class="container body-container">
    <h3 class="title">권한 관리</h3>
    <form @submit.prevent="grantAuthority">
      <div class="form-inline">
        <div class="form-group">
          <input v-model="searchKeyword" type="text" class="form-control" placeholder="사번 or 사원명" required />
          <button type="button" class="btn btn-primary" @click="searchEmployee">검색</button>
        </div>
      </div>

      <div class="form-group">
        <table class="table">
          <thead>
            <tr>
              <th>사번</th>
              <th>이름</th>
              <th>성별</th>
              <th>부서</th>
              <th>직책</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="employees.length === 0">
              <td colspan="6" class="text-center">검색된 사원이 없습니다</td>
            </tr>
            <tr v-for="emp in employees" :key="emp.empNo">
              <td>{{ emp.empNo }}</td>
              <td>{{ emp.empName }}</td>
              <td>{{ emp.gender === 'M' ? '남' : '여' }}</td>
              <td>{{ emp.deptName }}</td>
              <td>{{ emp.positionName }}</td>
              <td>
                <button
                  type="button"
                  class="btn"
                  :class="selectedEmpNo === emp.empNo ? 'btn-primary' : 'btn-basic'"
                  @click="selectEmployee(emp.empNo, emp.empName)"
                >
                  선택
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="form-group">
        <select v-model="selectedAuthCode" class="form-control" required>
          <option v-for="auth in authList" :key="auth.authCode" :value="auth.authCode">
            {{ auth.authName }}
          </option>
        </select>
        <input type="hidden" :value="selectedEmpNo" />
      </div>

      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">권한부여</button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'

interface Employee {
  empNo: number
  empName: string
  gender: string
  deptName: string
  positionName: string
}

interface Authority {
  authCode: string
  authName: string
}

const searchKeyword = ref('')
const employees = ref<Employee[]>([])
const selectedEmpNo = ref<number | null>(null)
const selectedEmpName = ref('')
const selectedAuthCode = ref('')
const accountEmpNo = Number(sessionStorage.getItem('accountEmpNo')) || 0

const authList = ref<Authority[]>([])

const searchEmployee = async () => {
  if (!searchKeyword.value) {
    alert('사원명이나 사번을 입력해 주세요')
    return
  }
  try {
    const res = await api.get('/get/name/empno', {
      params: { data: searchKeyword.value }
    })
    employees.value = res.data
  } catch (err: any) {
    alert(err.response?.data?.message || '검색 실패')
  }
}

const selectEmployee = async (empNo: number, empName: string) => {
  if (empNo === accountEmpNo) {
    alert('본인에게 권한을 부여할 수 없습니다')
    return
  }

  try {
    const res = await api.get('/account/check-is-id', {
      params: { empNo }
    })
    if (res.data === true) {
      selectedEmpNo.value = empNo
      selectedEmpName.value = empName
    } else {
      alert('회원가입이 안 된 사원입니다')
    }
  } catch (err: any) {
    alert(err.response?.data?.message || '오류 발생')
  }
}

const grantAuthority = async () => {
  if (!selectedEmpNo.value || !selectedAuthCode.value) {
    alert('사원을 선택하고 권한을 지정해 주세요.')
    return
  }

  const confirmMsg = `${selectedEmpName.value}(${selectedEmpNo.value})에게 ${getAuthName()} 권한을 부여하겠습니까?`
  if (!confirm(confirmMsg)) return

  try {
    await api.post('/account-authority/add', {
      empNo: selectedEmpNo.value,
      authCode: selectedAuthCode.value
    })
    alert('권한부여 완료:)')
  } catch (err: any) {
    alert(err.response?.data?.message || '권한 부여 실패')
  }
}

const getAuthName = () => {
  return authList.value.find((a) => a.authCode === selectedAuthCode.value)?.authName || ''
}

onMounted(async () => {
  try {
    const res = await api.get('/authority/list') // 또는 props/emit으로 authList 주입 가능
    authList.value = res.data
  } catch (e) {
    console.warn('권한 목록 조회 실패')
  }
})
</script>
