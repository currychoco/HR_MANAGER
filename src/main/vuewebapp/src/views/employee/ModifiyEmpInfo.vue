<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const empNo = ref('')
const empName = ref('')
const empNameEn = ref('')
const gender = ref('M')
const email = ref('')
const phone = ref('')
const address1 = ref('')
const address2 = ref('')
const zipCode = ref('')

const original = ref({})

onMounted(async () => {
  try {
    const res = await api.get(`/get/employee/all/info`)
    const emp = res.data
    empNo.value = emp.empNo
    empName.value = emp.empName
    empNameEn.value = emp.empNameEn
    gender.value = emp.gender
    email.value = emp.email
    phone.value = emp.phone
    address1.value = emp.address1
    address2.value = emp.address2
    zipCode.value = emp.zipCode
    original.value = { ...emp }
  } catch (err) {
    console.error(err)
  }
})

function searchPostcode() {
  new window.daum.Postcode({
    oncomplete: function (data) {
      address1.value = data.address
      zipCode.value = data.zonecode
    }
  }).open()
}

function openEnNameRecommend() {
  window.name = 'parentForm'
  window.open(`/englishname?empName=${empName.value}`, 'enName', 'width=600, height=400')
}

async function updateEmployee() {
  if (!empName.value || !empNameEn.value || !gender.value || !email.value || !phone.value || !address1.value || !address2.value || !zipCode.value) {
    alert('누락된 정보가 있습니다.')
    return
  }

  const cur = {
    empNo: empNo.value,
    empName: empName.value,
    empNameEn: empNameEn.value,
    gender: gender.value,
    email: email.value,
    phone: phone.value,
    address1: address1.value,
    address2: address2.value,
    zipCode: zipCode.value
  }

  const isSame = Object.keys(cur).every(key => cur[key] === original.value[key])

  if (isSame) {
    alert('기존 정보와 같습니다.')
    return
  }

  await api.post('/my-info/create', cur)
    .then(() => {
      alert('신청이 완료되었습니다')
      router.push('/')
    })
    .catch(err => console.error(err))
}
</script>

<template>
  <div class="container body-container">
    <h3 class="title">나의 인사정보 수정</h3>
    <form @submit.prevent="updateEmployee">
      <input type="hidden" v-model="empNo" />

      <div class="form-group">
        <input type="text" class="form-control" v-model="empName" placeholder="이름" required />
      </div>

      <div class="form-group form-inline">
        <input type="text" class="form-control" v-model="empNameEn" placeholder="영문명" required />
        <button type="button" class="btn btn-primary" @click="openEnNameRecommend">영문명 추천</button>
      </div>

      <div class="form-group">
        <select class="form-control" v-model="gender" required>
          <option value="M">남성</option>
          <option value="F">여성</option>
        </select>
      </div>

      <div class="form-group">
        <input type="email" class="form-control" v-model="email" placeholder="이메일" required />
      </div>

      <div class="form-group">
        <input type="text" class="form-control" v-model="phone" placeholder="휴대폰" required />
      </div>

      <div class="form-inline" style="margin-bottom: 15px">
        <input type="text" class="form-control" v-model="zipCode" placeholder="우편번호" required />
        <button type="button" class="btn" @click="searchPostcode">주소검색</button>
      </div>

      <div class="form-group">
        <input type="text" class="form-control" v-model="address1" placeholder="주소" required />
      </div>

      <div class="form-group">
        <input type="text" class="form-control" v-model="address2" placeholder="상세 주소" required />
      </div>

      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">나의 인사정보 수정요청</button>
      </div>
    </form>
  </div>
</template>

<style scoped>
.form-inline input {
  margin-right: 10px;
}
</style>
