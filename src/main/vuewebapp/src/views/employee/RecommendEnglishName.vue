<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '@/lib/axios'
import { useRoute } from 'vue-router'

const route = useRoute()
const korName = ref(route.query.empName || '')
const results = ref<any[]>([])

const recommend = async () => {
  try {
    const res = await api.get('/englishname/recommand', {
      params: { korName: korName.value },
    })
    results.value = res.data.aItems
  } catch (error) {
    console.error(error)
  }
}

const select = (name: string) => {
  window.opener.document.getElementById('empNameEn').value = name
  window.close()
}

onMounted(() => {
  if (korName.value) {
    recommend()
  }
})
</script>

<template>
  <div class="container text-center">
    <input
      id="name"
      v-model="korName"
      class="form-control mb-3"
      placeholder="한글 이름 입력"
      @keyup.enter="recommend"
    />
    <table class="table table-hover">
      <colgroup>
        <col style="width: 70%" />
        <col style="width: 30%" />
      </colgroup>
      <thead>
        <tr>
          <th>영문명</th>
          <th>사용 빈도</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in results" :key="item.name">
          <td>
            <a href="#" @click.prevent="select(item.name)">
              {{ item.name }}
            </a>
          </td>
          <td>{{ item.score }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
