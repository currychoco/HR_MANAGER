<script setup lang="ts">
import { onMounted, ref } from 'vue'
import api from '@/lib/axios'
import {useRouter} from 'vue-router'

const router = useRouter()

interface AllowItem {
  no: number
  empNo: string
  empName: string
  allow: string
  requestDateToString: string
  allowDateToString: string
}

const allowList = ref<AllowItem[]>([])

const getAllowList = async () => {
  try {
    const res = await api.get('/allow/list')
    allowList.value = res.data
  } catch (err: any) {
    alert(err.response?.data?.message || '데이터 조회 실패')
  }
}

onMounted(() => {
  getAllowList()
})
</script>


<template>
  <div class="container body-container">
    <h3 class="title">인사정보 수정신청 관리</h3>
    <div>
      <table class="table table-hover">
        <thead>
          <tr>
            <th>번호</th>
            <th>사번</th>
            <th>한글명</th>
            <th>상태</th>
            <th>신청일</th>
            <th>승인일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="allowList.length === 0">
            <td colspan="6" class="text-center">조회된 데이터가 없습니다</td>
          </tr>
          <tr v-for="item in allowList" :key="item.no" @click="router.push(`/manager/allow/detail?no=${item.no}&empNo=${item.empNo}`)">
            <td>{{ item.no }}</td>
            <td>{{ item.empNo }}</td>
            <td>{{ item.empName }}</td>
            <td>{{ item.allow }}</td>
            <td>{{ item.requestDateToString }}</td>
            <td>{{ item.allowDateToString }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>