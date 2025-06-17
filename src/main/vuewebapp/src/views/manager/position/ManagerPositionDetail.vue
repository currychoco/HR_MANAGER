<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'
import { useRoute } from 'vue-router'

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

const route = useRoute()

const loadPosition = async () => {
  const code = route.query.positionCode
  if (!code) return

  try {
    const res = await api.get(`/position/detail`, {
      params: { positionCode: code }
    })
    form.value = res.data
  } catch (err: any) {
    alert('직급 정보를 불러오지 못했습니다.')
    console.error(err)
  }
}

const modifyPosition = async () => {
  const { positionCode, positionName, positionNameEn, positionLevel } = form.value
  if (!positionCode || !positionName || !positionNameEn || !positionLevel) {
    alert('누락된 정보가 있습니다.')
    return
  }

  if (!confirm('수정하시겠습니까?')) return

  try {
    await api.post('/position/modify', form.value)
    alert('직급 정보 수정이 완료되었습니다 :)')
  } catch (err: any) {
    console.error(err)
    if (err.response?.data?.message) {
      alert(err.response.data.message)
    }
  }
}

onMounted(() => {
  loadPosition()
})
</script>


<template>
  <div class="container body-container">
    <h3 class="title">직급 관리</h3>
    <form @submit.prevent="modifyPosition">
      <div class="form-group">
        <input
          v-model="form.positionCode"
          type="text"
          class="form-control"
          placeholder="직급코드"
          readonly
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
        <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
      </div>
    </form>
  </div>
</template>