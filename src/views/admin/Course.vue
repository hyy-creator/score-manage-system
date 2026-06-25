<template>
  <div class="course-box">
    <h2>课程管理</h2>
    <el-button type="primary" @click="openAddDialog">新增课程</el-button>

    <el-table :data="tableData" border style="margin-top:20px">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="课程名称" prop="courseName"/>
      <el-table-column label="学分" prop="credit"/>
      <el-table-column label="授课教师ID" prop="teacherId"/>
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 弹窗 -->
    <el-dialog v-model="dialogVisible" title="课程信息">
      <el-form :model="form">
        <el-form-item label="课程名称">
          <el-input v-model="form.courseName"></el-input>
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="form.credit"></el-input>
        </el-form-item>
        <el-form-item label="授课教师ID">
          <el-input v-model="form.teacherId"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])
const dialogVisible = ref(false)
const form = ref({
  id: null,
  courseName: '',
  credit: '',
  teacherId: null
})

// 加载课程列表
const loadList = async () => {
  const res = await request.get('/course/list')
  if (res.code === 200) {
    tableData.value = res.data
  }
}

// 新增弹窗
const openAddDialog = () => {
  form.value = { id: null, courseName: '', credit: '', teacherId: null }
  dialogVisible.value = true
}

// 编辑弹窗
const openEditDialog = (row) => {
  form.value = { ...row }
  dialogVisible.value = true
}

// 提交保存
const submitForm = async () => {
  if (form.value.id) {
    await request.put('/course/update', form.value)
  } else {
    await request.post('/course/add', form.value)
  }
  dialogVisible.value = false
  loadList()
}

// 删除
const handleDelete = async (id) => {
  await request.delete(`/course/delete/${id}`)
  loadList()
}

onMounted(() => {
  loadList()
})
</script>