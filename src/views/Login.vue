<template>
  <div class="login-box">
    <!-- 新增页面大标题，只加这一行，其余表单全部原样保留 -->
    <h1 class="main-title">欢迎来到大学成绩管理系统</h1>
    <el-card title="大学成绩管理系统" width="400px">
      <el-form :model="form">
        <el-form-item label="账号">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-button type="primary" style="width:100%" @click="login">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>
<script setup>
// 你的原始脚本完全没改动，登录、角色跳转逻辑原样保留
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'
const router = useRouter()
const form = ref({ username: '', password: '' })
const login = async () => {
  const res = await request.get('/user/login', { params: form.value })
  if (res.code === 200) {
    localStorage.setItem('userInfo', JSON.stringify(res.data))
    ElMessage.success('登录成功')
    if (res.data.role === 0) router.push('/admin/index')
    if (res.data.role === 1) router.push('/teacher/index')
    if (res.data.role === 2) router.push('/student/index')
  } else {
    ElMessage.error(res.msg)
  }
}
</script>
<style scoped>
.login-box {
  width: 100vw;
  height: 100vh;
  /* 蓝紫渐变好看背景 */
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0;
}
/* 新增顶部大字样式 */
.main-title {
  font-size: 38px;
  color: #ffffff;
  font-weight: bold;
  text-shadow: 0 3px 10px rgba(0,0,0,0.2);
  margin-bottom: 40px;
  letter-spacing: 2px;
}
</style>