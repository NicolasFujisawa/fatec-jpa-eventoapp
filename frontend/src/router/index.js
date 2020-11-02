import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Store from '../store/index';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/login',
    name: 'Login',
    meta: {
      requireAuth: false,
    },
    component: () => import('../views/Login.vue'),
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requireAuth)) {
    if (Store.getters.authStatus === 'success') {
      next();
      return;
    }
    next('/login');
  } else {
    next();
  }
});
export default router;
