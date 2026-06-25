<template>
  <div style="padding:30px;background:#f5f7fa;min-height:100vh;">
    <h2>本班学生成绩</h2>
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="学生姓名" prop="studentName"/>
      <el-table-column label="课程名称" prop="courseName"/>
      <el-table-column label="分数" prop="score"/>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])

const load = async () => {
  const loginUser = JSON.parse(localStorage.getItem('userInfo'))
  const res = await request.get('/score/teacher', {
    params: { teacherId: loginUser.id }
  })
  if (res.code === 200) {
    tableData.value = res.data
  }
}

onMounted(() => load())
</script>