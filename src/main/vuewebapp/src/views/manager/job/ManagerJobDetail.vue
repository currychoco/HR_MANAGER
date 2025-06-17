<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'
import { useRoute } from 'vue-router'

interface Job {
  jobCode: string
  jobName: string
  jobNameEn: string
  jobLevel: string
}

const form = ref<Job>({
  jobCode: '',
  jobName: '',
  jobNameEn: '',
  jobLevel: ''
})

// URL 파라미터에서 jobCode 가져오기
const route = useRoute()
const jobCode = route.query.jobCode as string

// 초기 데이터 로딩
const loadJob = async () => {
  try {
    const res = await api.get<Job>('/api/job/detail', {
      params: { jobCode }
    })
    form.value = res.data
  } catch (err) {
    console.error('직책 정보 조회 실패', err)
  }
}

onMounted(() => {
  loadJob()
})

// 수정 요청
const onSubmit = async () => {
  const { jobCode, jobName, jobNameEn, jobLevel } = form.value
  if (!jobCode || !jobName || !jobNameEn || !jobLevel) {
    alert('누락된 정보가 있습니다.')
    return
  }

  if (!confirm('수정하시겠습니까?')) return

  try {
    await api.post('/job/modify', form.value)
    alert('직책 정보 수정이 완료되었습니다 :)')
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
    <form @submit.prevent="onSubmit">
      <div class="form-group">
        <input
          v-model="form.jobCode"
          type="text"
          class="form-control"
          placeholder="직책코드"
          readonly
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
        <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
      </div>
    </form>
  </div>
</template>
