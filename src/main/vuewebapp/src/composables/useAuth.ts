import { ref } from 'vue'
import api from '@/lib/axios'

const isAuthorized = ref(false)
const empName = ref("")

async function checkSession() {
  try {
    await api.get('/get/employee/all/info')
    .then((res) => {
        empName.value = res.data.empName
    })
    isAuthorized.value = true
  } catch {
    isAuthorized.value = false
  }
}

export function useAuth() {
  return {
    isAuthorized,
    empName,
    checkSession,
  }
}