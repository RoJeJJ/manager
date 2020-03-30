<template>
  <div id="app" class="hold-transition login-page">
    <div class="login-box">
      <h3 style="text-align: center"><b>游戏管理后台</b></h3>
    </div>
    <div class="card">
      <div class="card-body login-card-body">
        <p class="login-box-msg">请登录</p>
        <form @submit.prevent="submit($event)" >
          <div class="input-group mb-2 mt-2">
            <!--suppress HtmlFormInputWithoutLabel -->
            <input type="text" class="form-control" placeholder="用户名" name="username" id="username">
            <div class="input-group-append center-vertical">
              <div class="input-group-text">
                <span class="fa fa-user"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-2 mt-2">
            <!--suppress HtmlFormInputWithoutLabel -->
            <input type="password" class="form-control" placeholder="密码" name="password" id="password">
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fa fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row mb-2 mt-2">
              <div class="input-group col-5">
                <!--suppress HtmlFormInputWithoutLabel -->
                <input type="text" class="form-control" placeholder="验证码" name="captcha" id="captcha">
                <div class="input-group-append">
                  <div class="input-group-text">
                    <span class="fa fa-refresh"></span>
                  </div>
                </div>
              </div>
            <div class="">
              <!--suppress HtmlUnknownTarget -->
              <img :src="captchaUrl" alt="" @click="codeUpdate" title="点击刷新" id="captcha-img" style="height: 38px;width:
              100px">
<!--              <a style="margin-bottom: 0"> 刷新</a>-->
            </div>
          </div>
          <div class="row mb-2 mt-2">
            <div class="col-8">
              <div class="icheck-primary" style="text-align: left">
                <input type="checkbox" id="rememberMe" name="rememberMe">
                <label for="rememberMe">自动登录</label>
              </div>
            </div>
            <div class="col-4">
              <button class="btn btn-primary btn-block">登录</button>
            </div>
          </div>
          <p style="font-size: small;color: red;text-align: center">{{errTip}}</p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
  import '../assets/fontawesome/css/all.min.css'
  import '../assets/css/adminlte.min.css'
  import '../assets/css/icheck-bootstrap.min.css'
  //js
  import '../assets/js/adminlte.min.js'
  import captcha from '../assets/img/def_captcha.jpg'

  export default {
    name: 'Login',
    data() {
      return {
        errTip: '',
        captchaUrl: captcha
      }
    },
    mounted() {
      this.$data.captchaUrl = '/captcha';
    },
    methods: {
      codeUpdate: function() {
        console.log(111);
        this.$data.captchaUrl = '/captcha?' + new Date();
      },
      submit: function (event) {
        let formData = new FormData(event.target);
        formData.append("Content-Type","application/x-www-form-urlencoded");
        this.axios.post('/session', formData).then(rsp => {
          console.log(rsp);
          let data = rsp.data;
          if (data.code === 0) {
            this.$router.replace('/index');
          } else if (data.code === 5001) {
            this.$data.errTip = '用户名或密码错误';
          } else if (data.code === 5002) {
            this.$data.errTip = '验证码错误';
          }
        });
      }
    }
  }
</script>
<style scoped>
</style>
