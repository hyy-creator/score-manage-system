<template>
  <el-container style="height:100vh; width:100%; display:flex;">
    <el-aside width="200px" style="background:#304156 !important;">
      <!-- 侧边栏标题 -->
      <div class="side-title">大学成绩管理系统</div>
      <el-menu
        text-color="#ffffff"
        active-text-color="#ffd04b"
        router
        style="background:transparent;"
      >
        <el-menu-item index="/teacher/course">我的课程</el-menu-item>
        <el-menu-item index="/teacher/score-add">成绩录入</el-menu-item>
        <el-menu-item index="/teacher/score-list">学生成绩查询</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main class="main-box">
      <!-- 顶部渐变欢迎横幅 -->
      <div class="welcome-banner">
        <h1>教师后台管理中心</h1>
        <p>欢迎你，{{ loginUser.username }} | 教师身份</p>
      </div>
      <!-- 功能快捷卡片 -->
      <div class="card-row">
        <el-card class="stat-card" shadow="hover" @click="$router.push('/teacher/course')">
          <div class="card-content">
            <h3>我的课程</h3>
            <span class="desc">查看本人授课课程</span>
          </div>
        </el-card>
        <el-card class="stat-card" shadow="hover" @click="$router.push('/teacher/score-add')">
          <div class="card-content">
            <h3>成绩录入</h3>
            <span class="desc">录入学生考试分数</span>
          </div>
        </el-card>
        <el-card class="stat-card" shadow="hover" @click="$router.push('/teacher/score-list')">
          <div class="card-content">
            <h3>成绩查询</h3>
            <span class="desc">查看全部学生成绩</span>
          </div>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script setup>
import { ref } from 'vue'
// 获取登录教师信息
const loginUser = ref(JSON.parse(localStorage.getItem('userInfo')))
</script>

<style scoped>
.main-box {
  background-color: #f5f7fa;
  padding: 30px;
}
.side-title {
  height: 60px;
  line-height: 60px;
  text-align: center;
  color: #fff;
  font-size: 17px;
  font-weight: bold;
  border-bottom: 1px solid #48576a;
}
.welcome-banner {
  background: linear-gradient(120deg, #66bb6a, #a5d6a7);
  color: #fff;
  padding: 32px 40px;
  border-radius: 12px;
  margin-bottom: 30px;
  box-shadow: 0 4px 14px rgba(102, 187, 106, 0.2);
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
.card-row {
  display: grid;
  grid-template-columns: repeat(3, 300px);
  gap: 24px;
}
.stat-card {
  height: 160px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.3s;
}
.stat-card:hover {
  transform: translateY(-6px);
}
.card-content h3 {
  color: #333;
  font-size: 20px;
  margin: 0 0 10px;
}
.desc {
  color: #909399;
  font-size: 15px;
}
</style>