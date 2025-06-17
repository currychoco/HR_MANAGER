<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

interface JobForm {
  jobCode: string
  jobName: string
  jobNameEn: string
  jobLevel: string
}

const form = ref<JobForm>({
  jobCode: '',
  jobName: '',
  jobNameEn: '',
  jobLevel: ''
})

const createJob = async () => {
  const { jobCode, jobName, jobNameEn, jobLevel } = form.value
  if (!jobCode || !jobName || !jobNameEn || !jobLevel) {
    alert('누락된 내용이 있습니다.')
    return
  }

  try {
    await api.post('/job/add', form.value)
    alert('직책 생성 성공')
    location.reload()
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
    <form @submit.prevent="createJob">
      <div class="form-group">
        <input
          v-model="form.jobCode"
          type="text"
          class="form-control"
          placeholder="직책코드"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.jobName"
          type="text"
          class="form-control"
          placeholder="직책한글명"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.jobNameEn"
          type="text"
          class="form-control"
          placeholder="직책영문명"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.jobLevel"
          type="text"
          class="form-control"
          placeholder="직책레벨"
          required
        />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-lg btn-primary btn-block">
          직책생성
        </button>
      </div>
    </form>
  </div>
</template>
