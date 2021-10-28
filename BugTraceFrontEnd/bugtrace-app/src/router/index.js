import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from "@/views/Register";
import NotFound from "@/views/NotFound"
import MainPage from "@/views/MainPage";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: "/register",
    name: 'Register',
    component: Register
  },
  {
    path: "/:catchAll(.*)",
    name:'NotFound',
    component: NotFound
  },
  {
    path: '/app',
    name: 'MainPage',
    component: MainPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
