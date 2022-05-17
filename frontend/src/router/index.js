import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/ProductInventory.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Product Inventory',
    component: () => import(/* webpackChunkName: "about" */ '../views/ProductInventory.vue')
  },
  {
    path: '/home',
    name: 'Home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
