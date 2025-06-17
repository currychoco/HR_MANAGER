<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'
import {useRouter} from 'vue-router'

const router = useRouter()

interface Employee {
  empNo: string
  empName: string
  gender: 'M' | 'F'
  deptName: string
  positionName: string
}

const keyword = ref<string>('')
const employees = ref<Employee[]>([])
const searched = ref<boolean>(false)

const search = async () => {
  if (!keyword.value.trim()) {
    alert('사원명이나 사번을 입력해 주세요')
    return
  }

  try {
    const res = await api.get<Employee[]>('/get/name/empno', {
      params: { data: keyword.value }
    })
    employees.value = res.data
    searched.value = true
  } catch (err: any) {
    if (err.response?.data?.message) {
      alert(err.response.data.message)
    } else {
      console.error(err)
    }
  }
}
</script>

<template>
  <div class="container body-container">
    <h3 class="title">사원 관리</h3>
    <div class="text-center mb-4">
      <form @submit.prevent>
        <input
          v-model="keyword"
          class="form-control"
          placeholder="이름 or 사번"
          @keyup.enter="search"
        />
        <button type="button" class="btn btn-primary mt-2" @click="search">검색</button>
      </form>
    </div>

    <table class="table table-hover">
      <colgroup>
        <col style="width:10%" />
        <col style="width:20%" />
        <col style="width:10%" />
        <col style="width:40%" />
        <col style="width:20%" />
      </colgroup>
      <thead>
        <tr>
          <th>사번</th>
          <th>이름</th>
          <th>성별</th>
          <th>부서</th>
          <th>직책</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="employees.length === 0 && searched">
          <td colspan="5" class="text-center">검색된 사원이 없습니다</td>
        </tr>
        <tr v-for="emp in employees" :key="emp.empNo" @click="router.push(`/manager/employee/detail?empNo=${emp.empNo}`)">
          <td>{{ emp.empNo }}</td>
          <td>{{ emp.empName }}</td>
          <td>{{ emp.gender === 'M' ? '남' : '여' }}</td>
          <td>{{ emp.deptName }}</td>
          <td>{{ emp.positionName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>