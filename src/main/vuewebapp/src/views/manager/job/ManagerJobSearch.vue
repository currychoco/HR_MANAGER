<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

interface Job {
  jobCode: string
  jobName: string
}

const keyword = ref<string>('')
const jobs = ref<Job[]>([])
const searched = ref<boolean>(false)

const search = async () => {
  if (!keyword.value.trim()) {
    alert('직책명이나 직책코드를 입력해 주세요')
    return
  }

  try {
    const res = await api.get<Job[]>('/job/search', {
      params: { data: keyword.value }
    })
    jobs.value = res.data
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
    <h3 class="title">직책 관리</h3>

    <div class="text-center mb-3">
      <div class="form-inline">
        <input
          v-model="keyword"
          class="form-control"
          type="text"
          placeholder="직책명 or 직책코드"
          @keyup.enter="search"
        />
        <button class="btn btn-primary ml-2" @click="search">검색</button>
      </div>
    </div>

    <table class="table table-hover">
      <colgroup>
        <col style="width:50%" />
        <col style="width:50%" />
      </colgroup>
      <thead>
        <tr>
          <th>직책코드</th>
          <th>직책명</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="searched && jobs.length === 0">
          <td colspan="2" class="text-center">검색된 직책이 없습니다</td>
        </tr>
        <tr v-for="job in jobs" :key="job.jobCode">
          <td>
            <a :href="`/manager/job/detail?jobCode=${job.jobCode}`">{{ job.jobCode }}</a>
          </td>
          <td>{{ job.jobName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
