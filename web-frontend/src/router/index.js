import Vue from 'vue'
import Router from 'vue-router'
import index from '../components/index'
import login from "../components/login";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: login
    },
    {
      path: '/index',
      name: 'Index',
      component: index
    }
  ],
});
