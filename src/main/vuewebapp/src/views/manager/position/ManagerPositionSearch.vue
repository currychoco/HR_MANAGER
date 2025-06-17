<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

interface Position {
  positionCode: string
  positionName: string
}

const query = ref('')
const positions = ref<Position[]>([])

const search = async () => {
  if (!query.value) {
    alert('직급명이나 직급코드를 입력해 주세요')
    return
  }

  try {
    const res = await api.get('/position/search', {
      params: { data: query.value },
      headers: { 'Content-Type': 'application/json' }
    })
    positions.value = res.data
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
    <div class="text-center">
      <form class="form-inline" @submit.prevent="search">
        <input
          type="text"
          class="form-control"
          v-model="query"
          placeholder="직급명 or 직급코드"
          @keyup.enter="search"
        />
        <button type="submit" class="btn btn-primary">검색</button>
      </form>
    </div>
    <div>
      <table class="table table-hover">
        <colgroup>
          <col style="width: 50%" />
          <col style="width: 50%" />
        </colgroup>
        <thead>
          <tr>
            <th>직급코드</th>
            <th>직급명</th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="positions.length === 0">
            <td colspan="2" class="text-center">검색된 직급이 없습니다</td>
          </tr>
          <tr v-for="pos in positions" :key="pos.positionCode">
            <td>
              <router-link :to="`/manager/position/detail?positionCode=${pos.positionCode}`">
                {{ pos.positionCode }}
              </router-link>
            </td>
            <td>{{ pos.positionName }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
