<script setup lang="ts">
import { ref } from 'vue'
import api from '@/lib/axios'

const id = ref('')
const password = ref('')

function login() {
  api
    .post('/account/login', {
      id: id.value,
      password: password.value,
    })
    .then((response) => {
      if (response.data) {
        alert('로그인 성공')
        window.location.href = "/empinfo"
      } else {
        alert('로그인 실패')
      }
    })
    .catch((error) => {
      console.error(error)
    })
}
</script>

<template>
  <div class="container body-container">
    <div class="text-center" style="height: 500px;">
      <img src="/favicon.png" />
    </div>
    <form @submit.prevent="login">
      <div class="form-group">
        <input
          v-model="id"
          type="text"
          class="form-control"
          name="id"
          placeholder="아이디"
          required
        />
      </div>
      <div class="form-group">
        <input
          v-model="password"
          type="password"
          class="form-control"
          name="password"
          placeholder="패스워드"
          @keyup.enter="login"
          required
        />
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block">로그인</button>
      </div>
    </form>
  </div>
</template>
