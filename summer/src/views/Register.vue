<template>
  <img src="../assets/bgimg3.png" class="background-img">
  <div class="page-register">
    <article class="header">
      <header>
        <div class="title" style="color: rgb(40, 40, 40);">注册墨书</div>
        <span class="login">
          <em class="bold" style="color: rgb(40, 40, 40)">已有账号？</em>
          <a href="/#/">
            <el-button type="primary" size="small">登录</el-button>
          </a>
        </span>
      </header>
    </article>

    <section>
      <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="100px"
          class="demo-ruleForm"
          autocomplete="off"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email" />
          <!--          <el-button size="mini" round @click="sendMsg">发送验证码</el-button>-->
          <span class="status">{{ statusMsg }}</span>
        </el-form-item>
        <el-form-item label="真实姓名" prop="name">
          <el-input v-model="ruleForm.name" maxlength="5" />
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
          <el-input v-model="ruleForm.pwd" type="password" />
        </el-form-item>
        <el-form-item label="确认密码" prop="cpwd">
          <el-input v-model="ruleForm.cpwd" type="password" />
        </el-form-item>
      </el-form>
    </section>
    <div class="footer">
      <el-button
          type="primary"
          @click="register"
      >点击注册
      </el-button>
      <div class="error">{{ error }}</div>
    </div>
  </div>
</template>

<script>

import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      statusMsg: '',
      error: '',
      ruleForm: {
        username: '',
        name: '',
        pwd: '',
        cpwd: '',
        email: ''
      },
      rules: {
        username: [{
          required: true,
          type: 'string',
          message: '请输入用户名',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          type: 'string',
          message: '请输入真实姓名',
          trigger: 'blur'
        }],
        email: [{
          required: true,
          type: 'email',
          message: '请输入邮箱',
          trigger: 'blur'
        }],
        pwd: [{
          required: true,
          message: '创建密码',
          trigger: 'blur'
        }],
        cpwd: [{
          required: true,
          message: '确认密码',
          trigger: 'blur'
        }]
      }
    }
  },
  layout: 'blank',
  methods: {
    // 模拟登录
    register: function() {
      if(this.ruleForm.email==='' || this.ruleForm.pwd==='' || this.ruleForm.name===''
          || this.ruleForm.username==='' || this.ruleForm.cpwd===''){
        ElMessage({
          message: '请填写所有信息',
          type: 'danger',
        });
        return;
      }
      this.$axios.post("user/register", {
        email: this.ruleForm.email,
        password: this.ruleForm.pwd,
        profilePic: null,
        uid: 0,
        uname: this.ruleForm.name,
        unickname: this.ruleForm.username
      }).then(function (response) {
        console.log(response);
        ElMessage({
          message: '注册成功,即将跳转到登录页面',
          type: 'success',
        })
      })
      setInterval(()=>{
        this.$router.push({
          name: 'Login',
        })
      }, 2000)
    }
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
.page-register {
  position: relative;
  top: 10px;
  display: inline-block;
  height: 500px;
  border-radius: 10px;
  margin-top: 50px;
  color: white;
  .header {
    min-width: 980px;
    color: #666;
    header {
      margin: 0 auto;
      padding: 10px 0;
      width: 980px;
      .title {
        font-size: 24px;
        font-weight: 700;
        text-align: center;
      }
      .login {
        float: right;
      }
      .bold {
        font-style: normal;
      }
    }
  }
  .demo-ruleForm {
    background: white;
    margin: 20px auto;
    border: 1px black solid;
    padding: 20px 20px 0 0;
    border-radius: 10px;
  }
  a {
    color: #1890ff;
    text-decoration: none;
    background-color: transparent;
    outline: none;
    cursor: pointer;
    transition: color 0.3s;
  }
  > section {
    margin: 0 auto 30px;
    padding-top: 30px;
    width: 580px;
    min-height: 300px;
    box-sizing: border-box;
    .status {
      font-size: 12px;
      margin-left: 20px;
      color: #e6a23c;
    }
    .error {
      color: red;
    }
  }
  .footer{
    text-align: center;
  }
}
</style>

