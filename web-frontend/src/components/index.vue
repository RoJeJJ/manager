<template>
  <div id="app" class="main-sidebar sidebar-dark-primary elevation-4">
    <div class="wrapper">
      <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <a href="#" class="brand-link">111111</a>
        <div class="sidebar">
          <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                data-accordion="false">
              <li class="nav-item has-treeview">
                <a href="#" class="nav-link">
                  <i class="nav-icon fa fa-cog"></i>
                  <p>系统管理<i class="right fas fa-angle-left"></i>
                  </p>
                </a>
                <ul class="nav nav-treeview">
                  <li class="nav-item">
                    <a href="#" class="nav-link" style="padding-left: 24px">
                      <i class="far fa-user nav-icon"></i>
                      <p>用户管理</p>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a href="#" class="nav-link" style="padding-left: 24px">
                      <i class="fa fa-id-card nav-icon"></i>
                      <p>角色管理</p>
                    </a>
                  </li>
                  <li class="nav-item">
                    <a href="#" class="nav-link" style="padding-left: 24px">
                      <i class="far fa-file nav-icon"></i>
                      <p>权限管理</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="nav-icon fas fa-th"></i>
                  <p>Widgets</p>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </aside>
      <div class="content-wrapper">
        <section class="content-header"></section>
        <section class="content">
        </section>
      </div>
    </div>
  </div>
</template>

<script>
  import '../assets/fontawesome/css/all.min.css'
  import '../assets/css/adminlte.min.css'
  import 'overlayscrollbars/css/OverlayScrollbars.css'
  //js
  import '../assets/js/adminlte.min'

  export default {
    name: 'Index',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<style scoped>
</style>
