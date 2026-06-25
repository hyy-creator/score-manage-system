<template>
  <el-container style="height:100vh; width:100%; display:flex;">
    <!-- 侧边栏 -->
    <el-aside width="200px" style="background:#304156 !important;">
      <!-- 侧边栏顶部标题 -->
      <div class="side-title">大学成绩管理系统</div>
      <el-menu
        text-color="#ffffff"
        active-text-color="#ffd04b"
        router
        style="background:transparent;"
      >
        <el-menu-item index="/admin/index">管理员首页</el-menu-item>
        <el-menu-item index="/admin/major">专业管理</el-menu-item>
        <el-menu-item index="/admin/clazz">班级管理</el-menu-item>
        <el-menu-item index="/admin/user">用户管理</el-menu-item>
        <el-menu-item index="/admin/course">课程管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main class="main-box">
      <!-- 顶部欢迎横幅 -->
      <div class="welcome-banner">
        <h1>管理员后台 - 欢迎使用大学成绩管理系统</h1>
        <p>当前登录账号：{{ loginUser.username }} | 角色：管理员</p>
      </div>
      <!-- 数据统计卡片 -->
      <div class="card-row">
        <el-card class="stat-card" shadow="hover">
          <div class="card-content">
            <h3>全部用户</h3>
            <span class="num">{{ userCount }}</span>
          </div>
        </el-card>
        <el-card class="stat-card" shadow="hover">
          <div class="card-content">
            <h3>全部班级</h3>
            <span class="num">{{ clazzCount }}</span>
          </div>
        </el-card>
        <el-card class="stat-card" shadow="hover">
          <div class="card-content">
            <h3>全部课程</h3>
            <span class="num">{{ courseCount }}</span>
          </div>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

// 获取登录用户信息
const loginUser = ref(JSON.parse(localStorage.getItem('userInfo')))
// 统计数字
const userCount = ref(0)
const clazzCount = ref(0)
const courseCount = ref(0)

// 获取各项总数
const getTotal = async () => {
  const userRes = await request.get('/user/list')
  userCount.value = userRes.data.length

  const clazzRes = await request.get('/clazz/list')
  clazzCount.value = clazzRes.data.length

  const courseRes = await request.get('/course/list')
  courseCount.value = courseRes.data.length
}

onMounted(() => {
  getTotal()
})
</script>

<style scoped>
/* 主内容区域背景 */
.main-box {
  background-color: #f5f7fa;
  padding: 30px;
}
/* 侧边栏标题 */
.side-title {
  height: 60px;
  line-height: 60px;
  text-align: center;
  color: #fff;
  font-size: 17px;
  font-weight: bold;
  border-bottom: 1px solid #48576a;
}
/* 顶部渐变欢迎栏 */
.welcome-banner {
  background: linear-gradient(120deg, #409eff, #66b1ff);
  color: #fff;
  padding: 32px 40px;
  border-radius: 12px;
  margin-bottom: 30px;
  box-shadow: 0 4px 14px rgba(64, 158, 255, 0.2);
}
.welcome-banner h1 {
  margin: 0 0 10px;
  font-size: 26px;
}
.welcome-banner p {
  margin: 0;
  font-size: 16px;
  opacity: 0.9;
}
/* 卡片一行三列布局 */
.card-row {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}
.stat-card {
  height: 160px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s;
}
.stat-card:hover {
  transform: translateY(-6px);
}
.card-content h3 {
  color: #606266;
  font-size: 18px;
  margin: 0 0 12px;
}
.num {
  font-size: 44px;
  font-weight: bold;
  color: #409eff;
}
</style>