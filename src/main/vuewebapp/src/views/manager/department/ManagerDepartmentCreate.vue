<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

const form = ref<Record<string, string>>({
  deptCode: '',
  upperDeptCode: '',
  headEmpNo: '',
  deptName: '',
  deptNameEn: '',
  deptLevel: ''
})

const placeholders: Record<string, string> = {
  deptCode: '부서코드',
  upperDeptCode: '상위부서코드',
  headEmpNo: '부서장사번',
  deptName: '부서한글명',
  deptNameEn: '부서영문명',
  deptLevel: '부서레벨'
}

const requiredFields = ['deptCode', 'upperDeptCode', 'deptName', 'deptNameEn', 'deptLevel']

const createDepartment = async () => {
  const hasMissing = requiredFields.some(field => !form.value[field])
  if (hasMissing) {
    alert('누락된 내용이 있습니다.')
    return
  }

  try {
    await api.post('/department/add', form.value)
    alert('부서 생성 성공')
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
    <h3 class="title">부서 관리</h3>
    <form @submit.prevent="createDepartment">
      <div class="form-group" v-for="(value, key) in form" :key="key">
        <input
          v-model="form[key]"
          type="text"
          class="form-control"
          :placeholder="placeholders[key]"
          :required="requiredFields.includes(key)"
        />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-lg btn-primary btn-block">부서생성</button>
      </div>
    </form>
  </div>
</template>