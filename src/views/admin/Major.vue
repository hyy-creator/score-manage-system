<template>
  <div class="major-box">
    <h2>专业管理</h2>
    <el-button type="primary" @click="openAddDialog">新增专业</el-button>

    <el-table :data="tableData" border style="margin-top:20px">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="专业名称" prop="majorName"/>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="openEditDialog(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/编辑弹窗 -->
    <el-dialog v-model="dialogVisible" title="专业信息">
      <el-form :model="form">
        <el-form-item label="专业名称">
          <el-input v-model="form.majorName"></el-input>
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
  majorName: ''
})

// 加载列表
const loadList = async () => {
  const res = await request.get('/major/list')
  if (res.code === 200) {
    tableData.value = res.data
  }
}

// 新增弹窗
const openAddDialog = () => {
  form.value = { id: null, majorName: '' }
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
    // 修改
    await request.put('/major/update', form.value)
  } else {
    // 新增
    await request.post('/major/add', form.value)
  }
  dialogVisible.value = false
  loadList()
}

// 删除
const handleDelete = async (id) => {
  await request.delete(`/major/delete/${id}`)
  loadList()
}

onMounted(() => {
  loadList()
})
</script>