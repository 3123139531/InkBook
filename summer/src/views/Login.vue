<template>
  <div class="login" >
    <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        label-position="left"
        label-width="0px"
        class="login-form"
    >
      <h3 class="title">欢迎使用</h3>
      <el-form-item prop="username">
        <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="账号"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
        >
        </el-input>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin: 0 0 25px 0; float: left">
        记住我
      </el-checkbox>
      <el-form-item style="width: 100%">
        <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width: 100%"
            @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <a href="/#/Register" class="register">注册</a>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// 加密
// import { encrypt } from '@/utils/rsaEncrypt'
// import Cookies from 'js-cookie'

export default {
  name: 'Login',
  data() {
    return {
      codeUrl: '',
      cookiePass: '',
      loginForm: {
        username: '',
        password: '',
        rememberMe: false,
        code: '',
        uuid: ''
      },
      loginRules: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' }
        ],
        password: [
          { required: true, trigger: 'blur', message: '密码不能为空' }
        ],
        code: [{ required: true, trigger: 'change', message: '验证码不能为空' }]
      },
      loading: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        const user = {
          username: this.loginForm.username,
          password: this.loginForm.password,
          rememberMe: this.loginForm.rememberMe,
          code: this.loginForm.code,
          uuid: this.loginForm.uuid
        }
        if (user.password !== this.cookiePass) {
          user.password = encrypt(user.password)
        }
        if (valid) {
          this.loading = true
          if (user.rememberMe) {
            Cookies.set('username', user.username, {
              expires: Config.passCookieExpires
            })
            Cookies.set('password', user.password, {
              expires: Config.passCookieExpires
            })
            Cookies.set('rememberMe', user.rememberMe, {
              expires: Config.passCookieExpires
            })
          } else {
            Cookies.remove('username')
            Cookies.remove('password')
            Cookies.remove('rememberMe')
          }
          // 模拟登录成功
          console.log('登录成功')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // point() {
    //   const point = Cookies.get('point') !== undefined
    //   if (point) {
    //     this.$notify({
    //       title: '提示',
    //       message: '当前登录状态已过期，请重新登录！',
    //       type: 'warning',
    //       duration: 5000
    //     })
    //     Cookies.remove('point')
    //   }
    // }
  }
}
</script>

<style scoped rel="stylesheet/scss" lang="scss">
  .login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-size: cover;
  }
  .title {
    margin: 0 auto 30px auto;
    text-align: center;
    color: #707070;
  }

  .login-form {
    border-radius: 6px;
    border: 1px black solid;
    background: #ffffff;
    width: 385px;
    padding: 25px 25px 5px 25px;
    margin-top: 100px;
    .el-input {
      height: 38px;
      input {
        height: 38px;
      }
    }
    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }
  .login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }
  .login-code {
    width: 33%;
    display: inline-block;
    height: 38px;
    //float: right;
    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }
  .register {
    margin: 0 auto;
    color: rgb(24, 144, 255);
  }
  a {
    color: #1890ff;
    text-decoration: none;
    background-color: transparent;
    outline: none;
    cursor: pointer;
    transition: color 0.3s;
  }

</style>

