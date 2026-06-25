<template>
  <div style="padding:30px;background:#f5f7fa;min-height:100vh;">
    <h2>我的成绩</h2>
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="课程名称" prop="courseName"/>
      <el-table-column label="分数" prop="score"/>
      <el-table-column label="授课教师" prop="teacherName"/>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])

const loadScore = async () => {
  // 读取本地存储的登录学生ID，传给后端接口
  const loginUser = JSON.parse(localStorage.getItem('userInfo'))
  const res = await request.get('/score/student', {
    params: { studentId: loginUser.id }
  })
  if (res.code === 200) {
    tableData.value = res.data
  }
}

onMounted(() => loadScore())
</script>