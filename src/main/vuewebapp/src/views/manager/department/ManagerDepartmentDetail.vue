<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'
import { useRoute } from 'vue-router'

interface Department {
  deptCode: string
  deptName: string
  deptNameEn: string
  upperDeptCode: string
  headEmpNo: string
  deptLevel: string
}

const route = useRoute()
const deptCode = route.query.deptCode as string

const form = ref<Department>({
  deptCode: '',
  deptName: '',
  deptNameEn: '',
  upperDeptCode: '',
  headEmpNo: '',
  deptLevel: ''
})

const loadDepartment = async () => {
  try {
    const res = await api.get<Department>('/department/detail', {
      params: { deptCode }
    })
    form.value = res.data
  } catch (err) {
    console.error('부서 정보 로딩 실패', err)
  }
}

loadDepartment()

const onSubmit = async () => {
  const { deptCode, deptName, deptNameEn, deptLevel } = form.value
  if (!deptCode || !deptName || !deptNameEn || !deptLevel) {
    alert('누락된 정보가 있습니다.')
    return
  }

  if (!confirm('수정하시겠습니까?')) return

  try {
    await api.post('/department/modify', form.value)
    alert('부서 정보 수정이 완료되었습니다 :)')
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
    <form @submit.prevent="onSubmit">
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.deptCode"
          placeholder="부서코드"
          readonly
        />
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.deptName"
          placeholder="부서한글명"
          required
        />
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.deptNameEn"
          placeholder="부서영문명"
          required
        />
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.upperDeptCode"
          placeholder="상위부서"
        />
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.headEmpNo"
          placeholder="부서장사번"
        />
      </div>
      <div class="form-group">
        <input
          type="text"
          class="form-control"
          v-model="form.deptLevel"
          placeholder="부서레벨"
          required
        />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-lg btn-primary btn-block">수정</button>
      </div>
    </form>
  </div>
</template>