<template>
  <div>
    <h2>班级管理</h2>
    <el-button type="primary" @click="openDialog">新增班级</el-button>
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="班级名称" prop="clazzName"/>
      <el-table-column label="所属专业" prop="majorName"/>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="edit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible" title="班级">
      <el-form :model="form">
        <el-form-item label="班级名称">
          <el-input v-model="form.clazzName"></el-input>
        </el-form-item>
        <!-- 新增专业下拉选择框，解决major_id不能为空报错 -->
        <el-form-item label="所属专业">
          <el-select v-model="form.majorId" placeholder="请选择专业" style="width:100%">
            <el-option
              v-for="item in majorList"
              :key="item.id"
              :label="item.majorName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'

const tableData = ref([])
const dialogVisible = ref(false)
// 表单新增majorId字段
const form = ref({ id: null, clazzName: '', majorId: null })
// 存储专业下拉列表
const majorList = ref([])

// 加载班级列表
const load = async () => {
  const res = await request.get('/clazz/list')
  if (res.code === 200) tableData.value = res.data
}

// 加载所有专业，给下拉框使用
const loadMajor = async () => {
  const res = await request.get('/major/list')
  if (res.code === 200) majorList.value = res.data
}

// 打开新增弹窗，清空表单
const openDialog = () => {
  form.value = { id: null, clazzName: '', majorId: null }
  dialogVisible.value = true
}

// 编辑回显数据
const edit = (row) => {
  form.value = { ...row }
  dialogVisible.value = true
}

// 保存新增/编辑
const save = async () => {
  // 简单前端校验，防止专业为空提交
  if (!form.value.majorId) {
    alert('请选择所属专业')
    return
  }
  if (form.value.id) {
    await request.put('/clazz/update', form.value)
  } else {
    await request.post('/clazz/add', form.value)
  }
  dialogVisible.value = false
  load()
}

// 删除班级
const del = async (id) => {
  await request.delete(`/clazz/delete/${id}`)
  load()
}

// 页面初始化同时加载班级、专业数据
onMounted(() => {
  load()
  loadMajor()
})
</script>