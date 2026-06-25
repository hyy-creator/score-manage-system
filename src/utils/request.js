import axios from 'axios'
const request = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000,
  withCredentials: true // 关键：跨域传递登录Session Cookie
})
request.interceptors.request.use(config => {
  return config
})
request.interceptors.response.use(res => {
  return res.data
})
export default request