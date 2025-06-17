<template>
  <div class="container body-container">
    <h3 class="title">인사정보 수정신청 관리</h3>
    <table class="table">
      <thead>
        <tr>
          <th>목록</th>
          <th>변경 전</th>
          <th>변경 후</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>한글명</td>
          <td>{{ employee.empName }}</td>
          <td>{{ myInformation.empName }}</td>
        </tr>
        <tr>
          <td>영문명</td>
          <td>{{ employee.empNameEn }}</td>
          <td>{{ myInformation.empNameEn }}</td>
        </tr>
        <tr>
          <td>성별</td>
          <td>{{ employee.gender }}</td>
          <td>{{ myInformation.gender }}</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>{{ employee.email }}</td>
          <td>{{ myInformation.email }}</td>
        </tr>
        <tr>
          <td>휴대폰번호</td>
          <td>{{ employee.phone }}</td>
          <td>{{ myInformation.phone }}</td>
        </tr>
        <tr>
          <td>주소</td>
          <td>{{ employee.address }}</td>
          <td>{{ myInformation.address }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <button class="btn btn-primary btn-block" @click="modifyAllow">승인</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '@/lib/axios'

const route = useRoute()
const router = useRouter()

const employee = ref({
  empNo: '',
  empName: '',
  empNameEn: '',
  gender: '',
  email: '',
  phone: '',
  address: ''
})

const myInformation = ref({
  no: '',
  empName: '',
  empNameEn: '',
  gender: '',
  email: '',
  phone: '',
  address: ''
})

const fetchDetailData = async () => {
  const empNo = route.query.empNo
  const no = route.query.no

  try {
    const [empRes, myRes] = await Promise.all([
      api.get(`/manager/employee/${empNo}`),
      api.get(`/manager/my-information/${no}`)
    ])

    employee.value = empRes.data
    myInformation.value = myRes.data
  } catch (err: any) {
    alert(err.response?.data?.message || '조회 실패')
  }
}

const modifyAllow = async () => {
  try {
    await api.post('/allow/update', {
      no: myInformation.value.no,
      empNo: employee.value.empNo,
      empName: myInformation.value.empName,
      empNameEn: myInformation.value.empNameEn,
      gender: myInformation.value.gender,
      email: myInformation.value.email,
      phone: myInformation.value.phone,
      address: myInformation.value.address
    })
    alert('승인완료')
    router.push('/manager/allow/list')
  } catch (err: any) {
    alert(err.response?.data?.message || '승인 실패')
  }
}

onMounted(() => {
  fetchDetailData()
})
</script>
