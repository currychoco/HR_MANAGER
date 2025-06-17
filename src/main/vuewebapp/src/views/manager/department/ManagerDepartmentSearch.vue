<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

interface Department {
  deptCode: string
  deptName: string
}

const keyword = ref<string>('')
const departments = ref<Department[]>([])
const searched = ref<boolean>(false)

const search = async () => {
  if (!keyword.value.trim()) {
    alert('부서명이나 부서코드를 입력해 주세요')
    return
  }

  try {
    const res = await api.get<Department[]>('/department/search', {
      params: { data: keyword.value }
    })
    departments.value = res.data
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
    <h3 class="title">부서 관리</h3>

    <div class="text-center mb-3">
      <form @submit.prevent class="form-inline">
        <input
          type="text"
          class="form-control"
          v-model="keyword"
          placeholder="부서명 or 부서코드"
          @keyup.enter="search"
        />
        <button type="button" class="btn btn-primary ml-2" @click="search">검색</button>
      </form>
    </div>

    <table class="table table-hover">
      <colgroup>
        <col style="width:50%" />
        <col style="width:50%" />
      </colgroup>
      <thead>
        <tr>
          <th>부서코드</th>
          <th>부서명</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="searched && departments.length === 0">
          <td colspan="2" class="text-center">검색된 부서가 없습니다</td>
        </tr>
        <tr v-for="dept in departments" :key="dept.deptCode">
          <td>
            <a :href="`/manager/department/detail?deptCode=${dept.deptCode}`">{{ dept.deptCode }}</a>
          </td>
          <td>{{ dept.deptName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>