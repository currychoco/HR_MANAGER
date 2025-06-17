<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'

// 데이터 모델 정의
interface Department {
  deptCode: string
  deptName: string
}

interface Job {
  jobCode: string
  jobName: string
}

interface Position {
  positionCode: string
  positionName: string
}

// 입력 필드
const form = ref({
  empName: '',
  empNameEn: '',
  gender: '',
  deptCode: '',
  jobCode: '',
  positionCode: '',
  email: '',
  phone: '',
  zipCode: '',
  address1: '',
  address2: ''
})

const textFields = {
  empName: '',
  empNameEn: '',
  email: '',
  phone: ''
}

const placeholders: Record<string, string> = {
  empName: '이름',
  empNameEn: '영문명',
  email: '이메일',
  phone: '휴대폰',
  zipCode: '우편번호',
  address1: '주소',
  address2: '상세주소'
}

const readonlyFields = ['zipCode', 'address1']

const departments = ref<Department[]>([])
const jobs = ref<Job[]>([])
const positions = ref<Position[]>([])

onMounted(async () => {
  departments.value = await fetchData('/department/all')
  jobs.value = await fetchData('/job/all/read')
  positions.value = await fetchData('/position/all/read')
})

async function fetchData<T>(url: string): Promise<T[]> {
  try {
    const res = await api.get<T[]>(url)
    return res.data
  } catch (err) {
    console.error(`Failed to fetch ${url}`, err)
    return []
  }
}

// 다음 주소 검색
function searchAddress() {
  new (window as any).daum.Postcode({
    oncomplete: (data: any) => {
      form.value.address1 = data.address
      form.value.zipCode = data.zonecode
    }
  }).open()
}

// 유효성 검사 + 전송
async function createNewEmployee() {
  const values = Object.values(form.value)
  const hasEmpty = values.some((v) => !v || v === '')
  if (hasEmpty) {
    alert('누락된 내용이 있습니다.')
    return
  }

  try {
    await api.post('/employee/add', form.value)
    alert('생성완료')
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
    <h3 class="title">사원 관리</h3>
    <form @submit.prevent="createNewEmployee">
      <div class="form-group" v-for="(value, key) in textFields" :key="key">
        <input
          :type="key === 'email' ? 'email' : 'text'"
          class="form-control"
          :id="key"
          v-model="form[key]"
          :placeholder="placeholders[key]"
          :readonly="readonlyFields.includes(key)"
          required
        />
      </div>

      <div class="form-group">
        <select v-model="form.gender" class="form-control" required>
          <option value="">성별</option>
          <option value="M">남성</option>
          <option value="F">여성</option>
        </select>
      </div>

      <div class="form-group">
        <select v-model="form.deptCode" class="form-control" required>
          <option disabled value="">부서 선택</option>
          <option v-for="dept in departments" :key="dept.deptCode" :value="dept.deptCode">
            {{ dept.deptName }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <select v-model="form.jobCode" class="form-control" required>
          <option disabled value="">직무 선택</option>
          <option v-for="job in jobs" :key="job.jobCode" :value="job.jobCode">
            {{ job.jobName }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <select v-model="form.positionCode" class="form-control" required>
          <option disabled value="">직책 선택</option>
          <option v-for="position in positions" :key="position.positionCode" :value="position.positionCode">
            {{ position.positionName }}
          </option>
        </select>
      </div>

      <div class="form-inline mb-2">
        <input
          v-model="form.zipCode"
          type="text"
          class="form-control"
          placeholder="우편번호"
          readonly
          required
        />
        <button type="button" class="btn btn-secondary" @click="searchAddress">주소검색</button>
      </div>

      <div class="form-group">
        <input
          v-model="form.address1"
          type="text"
          class="form-control"
          placeholder="주소"
          readonly
          required
        />
      </div>

      <div class="form-group">
        <input
          v-model="form.address2"
          type="text"
          class="form-control"
          placeholder="상세주소"
          required
        />
      </div>

      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">생성</button>
      </div>
    </form>
  </div>
</template>
