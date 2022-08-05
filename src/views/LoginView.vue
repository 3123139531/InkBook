<template>
  <img src="../assets/bgimg.png" class="background-img">
  <div class="login" >
    <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        label-position="left"
        label-width="0px"
        class="login-form"
    >
      <h3 class="title">欢迎使用墨书</h3>
      <el-form-item prop="username">
        <el-input
            v-model="loginForm.username"
            type="text"
            auto-complete="off"
            placeholder="用户名"
        >
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter="handleLogin"
        >
        </el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button
            size="default"
            type="primary"
            style="width: 100%"
            @click.prevent="handleLogin"
        >
          <span>登 录</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <a href="/register" class="register">注册</a>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
/* eslint-disable */
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
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
    }
  },
  methods: {
    handleLogin() {
      if(this.loginForm.username==='' || this.loginForm.password===''){
        ElMessage({
          message: '请填写所有信息',
          type: 'danger',
        });
        return;
      }
      this.$axios.post("user/login", {
        email: '0',
        password: this.loginForm.password,
        profilePic: '0',
        uid: 0,
        uname: this.loginForm.username,
        unickname: '0',
      }).then(function (response) {
        // console.log(response);
        ElMessage({
          message: '登录成功',
          type: 'success',
        });
      })
      this.$router.push({
        name: 'home',
        params: {
          ac : this.loginForm.username
        }
      })
    },
  }
}
</script>

<style scoped rel="stylesheet/scss" lang="scss">
.background-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.login {
  position: relative;
  top: 10px;
  display: inline-block;
  align-items: center;
  background: white;
  border-radius: 6px;
  margin-top: 100px;
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

