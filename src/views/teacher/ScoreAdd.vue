<template>
  <div class="wrap">
    <h2>录入学生成绩</h2>
    <!-- 原有成绩录入表单 -->
    <el-card class="form-card">
      <el-form :model="form" label-width="100px" style="width:500px;">
        <el-form-item label="学生ID">
          <el-input v-model="form.studentId" placeholder="点击下方表格自动填充"></el-input>
        </el-form-item>
        <el-form-item label="课程ID">
          <el-input v-model="form.courseId" placeholder="点击下方表格自动填充"></el-input>
        </el-form-item>
        <el-form-item label="分数">
          <el-input v-model="form.score" placeholder="0~100数字"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">提交成绩</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <!-- 下方：当前教师授课选课学生列表 -->
    <div class="table-title">我的授课课程 - 选课学生名单（点击行自动填充ID）</div>
    <el-card class="table-card">
      <el-table :data="stuList" border stripe @row-click="fillInput">
        <el-table-column label="学生ID" prop="studentId" width="100"/>
        <el-table-column label="学生姓名" prop="studentName"/>
        <el-table-column label="课程ID" prop="courseId" width="110"/>
        <el-table-column label="课程名称" prop="courseName"/>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '@/utils/request'
import { ElMessage } from 'element-plus'

// 登录教师信息
const loginUser = ref(JSON.parse(localStorage.getItem('userInfo')))

// 原有表单
const form = ref({
  studentId: '',
  courseId: '',
  score: ''
})

// 教师授课学生列表
const stuList = ref([])

// 加载当前教师名下所有选课学生
const loadStuList = async () => {
  const res = await request.get('/score/teacher-student', {
    params: { teacherId: loginUser.value.id }
  })
  if (res.code === 200) {
    stuList.value = res.data
  }
}

// 点击表格行，自动回填学生ID、课程ID
const fillInput = (row) => {
  form.value.studentId = row.studentId
  form.value.courseId = row.courseId
}

// 原有提交逻辑不变
const submit = async () => {
  if (!form.value.studentId || !form.value.courseId || form.value.score === '') {
    ElMessage.warning('学生ID、课程ID、分数不能为空')
    return
  }
  if (form.value.score < 0 || form.value.score > 100) {
    ElMessage.warning('分数范围必须0~100')
    return
  }
  try {
    // 自动带上当前登录教师id
    const submitData = {
      ...form.value,
      teacherId: loginUser.value.id
    }
    const res = await request.post('/score/add', submitData)
    if (res.code === 200) {
      ElMessage.success(res.msg)
      form.value = { studentId: '', courseId: '', score: '' }
      loadStuList()
    } else {
      // 重复提交时这里弹出友好文字提示，不会爆红页面
      ElMessage.error(res.msg)
    }
  } catch (e) {
    ElMessage.error('提交失败，请检查服务或数据')
  }
}

onMounted(() => {
  loadStuList()
})
</script>

<style scoped>
.wrap {
  padding: 30px;
  background: #f5f7fa;
  min-height: 100vh;
}
.form-card {
  width: 520px;
  padding: 20px;
  margin: 20px 0 40px;
}
.table-title {
  font-size: 19px;
  font-weight: bold;
  color: #304156;
  margin-bottom: 12px;
}
.table-card {
  padding: 10px;
}
</style>