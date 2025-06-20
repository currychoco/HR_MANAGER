// src/lib/axios.ts
import axios from 'axios'

const hideHeaderPaths = ['/login', '/join']

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "/api",
  withCredentials: true,
})

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      if(!hideHeaderPaths.includes(window.location.pathname)) {
        window.location.href="/login"
      }
    }
    return Promise.reject(error)
  }
)

export default api
