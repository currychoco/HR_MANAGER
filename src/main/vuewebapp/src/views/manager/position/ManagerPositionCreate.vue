<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

interface PositionForm {
  positionCode: string
  positionName: string
  positionNameEn: string
  positionLevel: string
}

const form = ref<PositionForm>({
  positionCode: '',
  positionName: '',
  positionNameEn: '',
  positionLevel: ''
})

const createPosition = async () => {
  const { positionCode, positionName, positionNameEn, positionLevel } = form.value
  if (!positionCode || !positionName || !positionNameEn || !positionLevel) {
    alert('누락된 내용이 있습니다.')
    return
  }

  try {
    await api.post('/position/add', form.value)
    alert('직급 생성 성공')
    location.reload()
  } catch (err: any) {
    console.error(err)
    if (err.response?.data?.message) {
      alert(err.response.data.message)
    }
  }
}
</script>

<template>
  <div class="container body-container">
    <h3 class="title">직급 관리</h3>
    <form @submit.prevent="createPosition">
      <div class="form-group">
        <input
          v-model="form.positionCode"
          type="text"
          class="form-control"
          placeholder="직급코드"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.positionName"
          type="text"
          class="form-control"
          placeholder="직급한글명"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.positionNameEn"
          type="text"
          class="form-control"
          placeholder="직급영문명"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="form.positionLevel"
          type="text"
          class="form-control"
          placeholder="직급레벨"
          required
        />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-lg btn-primary btn-block">직급생성</button>
      </div>
    </form>
  </div>
</template>
