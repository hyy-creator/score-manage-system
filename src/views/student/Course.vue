<template>
  <div style="padding:30px;background:#f5f7fa;min-height:100vh;">
    <h2>我选修的课程</h2>
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="课程名称" prop="courseName"/>
      <el-table-column label="授课教师" prop="teacherName"/>
      <el-table-column label="学分" prop="credit"/>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])

const loadList = async () => {
  const loginUser = JSON.parse(localStorage.getItem('userInfo'))
  const res = await request.get('/course/studentById', {
    params: { studentId: loginUser.id }
  })
  if (res.code === 200) tableData.value = res.data
}

onMounted(() => loadList())
</script>