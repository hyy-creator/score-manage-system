<template>
  <div style="padding:30px;background:#f5f7fa;min-height:100vh;">
    <h2>我教授的课程</h2>
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="课程名称" prop="courseName"/>
      <el-table-column label="学分" prop="credit"/>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'
import { ElMessage } from 'element-plus'

const tableData = ref([])

const loadList = async () => {
  // 从本地存储取教师ID，调用新增的teacherById接口，不再依赖session
  const loginUser = JSON.parse(localStorage.getItem('userInfo'))
  const res = await request.get('/course/teacherById', {
    params: { teacherId: loginUser.id }
  })
  if (res.code === 200) {
    tableData.value = res.data
  } else {
    ElMessage.warning(res.msg)
  }
}

onMounted(() => loadList())
</script>