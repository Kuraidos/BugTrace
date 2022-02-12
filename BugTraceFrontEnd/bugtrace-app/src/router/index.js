import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from "@/views/Register";
import NotFound from "@/views/NotFound"
import MainPage from "@/views/MainPage";
import Profile from "@/views/Profile";

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
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
