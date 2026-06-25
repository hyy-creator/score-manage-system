import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 顶层页面
import Login from './views/Login.vue'
import AdminIndex from './views/admin/Index.vue'
import TeacherIndex from './views/teacher/Index.vue'
import StudentIndex from './views/student/Index.vue'

// 管理员子页面
import Major from './views/admin/Major.vue'
import Clazz from './views/admin/Clazz.vue'
import User from './views/admin/User.vue'
import Course from './views/admin/Course.vue'

// 教师子页面
import TCourse from './views/teacher/Course.vue'
import ScoreAdd from './views/teacher/ScoreAdd.vue'
import ScoreList from './views/teacher/ScoreList.vue'

// 学生子页面
import SCourse from './views/student/Course.vue'
import MyScore from './views/student/MyScore.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/admin/index', component: AdminIndex },
  { path: '/teacher/index', component: TeacherIndex },
  { path: '/student/index', component: StudentIndex },

  // 管理员菜单路由
  { path: '/admin/major', component: Major },
  { path: '/admin/clazz', component: Clazz },
  { path: '/admin/user', component: User },
  { path: '/admin/course', component: Course },

  // 教师菜单路由
  { path: '/teacher/course', component: TCourse },
  { path: '/teacher/score-add', component: ScoreAdd },
  { path: '/teacher/score-list', component: ScoreList },

  // 学生菜单路由
  { path: '/student/course', component: SCourse },
  { path: '/student/my-score', component: MyScore }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')