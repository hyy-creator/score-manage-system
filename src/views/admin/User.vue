<template>
  <div>
    <h2>用户管理</h2>
    <el-button type="primary" @click="openDialog">新增用户</el-button>
    <!-- 搜索框：实时输入触发搜索，带一键清空按钮 -->
    <el-input 
      v-model="searchName" 
      placeholder="输入用户名搜索" 
      style="width:300px;margin-left:10px" 
      @input="load"
      clearable
      @clear="load"
    />
    <el-table :data="tableData" border style="margin-top:20px;">
      <el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="用户名" prop="username"/>
      <el-table-column label="密码" prop="password"/>
      <el-table-column label="角色" prop="role">
        <template #default="scope">
          {{ scope.row.role === 0 ? '管理员' : scope.row.role === 1 ? '教师' : '学生' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="edit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 用户弹窗 -->
    <el-dialog v-model="dialogVisible" title="用户信息">
      <el-form :model="form">
        <el-form-item label="用户名">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <!-- value强制传数字，和数据库匹配 -->
          <el-select v-model="form.role" placeholder="请选择角色" style="width:100%">
            <el-option label="管理员" :value="0"/>
            <el-option label="教师" :value="1"/>
            <el-option label="学生" :value="2"/>
          </el-select>
        </el-form-item>
        <!-- 仅选学生时显示班级下拉 -->
        <el-form-item label="所属班级" v-if="form.role === 2">
          <el-select v-model="form.clazzId" placeholder="选择班级" style="width:100%">
            <el-option v-for="c in clazzList" :key="c.id" :label="c.clazzName" :value="c.id"/>
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
import { ElMessageBox, ElMessage } from 'element-plus'

const tableData = ref([])
const dialogVisible = ref(false)
const searchName = ref('')
// 表单role存数字，新增clazzId班级id
const form = ref({
  id: null,
  username: '',
  password: '',
  role: null,
  clazzId: null
})
// 班级下拉列表
const clazzList = ref([])

// 加载用户列表/搜索用户
const load = async () => {
  // 传参username，后端模糊查询
  const res = await request.get('/user/list', {
    params: {
      username: searchName.value
    }
  })
  if (res.code === 200) tableData.value = res.data
}

// 加载班级下拉（学生需要选班级）
const loadClazz = async () => {
  const res = await request.get('/clazz/list')
  if(res.code === 200) clazzList.value = res.data
}

// 打开新增弹窗
const openDialog = () => {
  form.value = { id: null, username: '', password: '', role: null, clazzId: null }
  dialogVisible.value = true
}

// 编辑回显
const edit = (row) => {
  form.value = { ...row }
  dialogVisible.value = true
}

// 保存提交
const save = async () => {
  // 前端基础校验
  if(!form.value.username || !form.value.password || form.value.role == null){
    ElMessage.warning('用户名、密码、角色不能为空')
    return
  }
  // 学生必须选班级
  if(form.value.role === 2 && !form.value.clazzId){
    ElMessage.warning('学生必须选择所属班级')
    return
  }
  try{
    if (form.value.id) {
      await request.put('/user/update', form.value)
      ElMessage.success('修改成功')
    } else {
      await request.post('/user/add', form.value)
      ElMessage.success('新增成功')
    }
    dialogVisible.value = false
    load()
  }catch(err){
    ElMessage.error('操作失败，请检查数据或后端服务')
  }
}

// 删除用户：增加确认弹窗，防止误删
const del = async (id) => {
  await ElMessageBox.confirm('确定要删除该用户吗？删除后无法恢复', '删除提示', {
    type: 'warning'
  })
  await request.delete(`/user/delete/${id}`)
  ElMessage.success('删除成功')
  load()
}

onMounted(() => {
  load()
  loadClazz()
})
</script>