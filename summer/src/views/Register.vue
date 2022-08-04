<template>
  <div class="page-register">
    <article class="header">
      <header>
        <div class="title">注册墨书</div>
        <span class="login">
          <em class="bold">已有账号？</em>
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
          <el-input v-model="ruleForm.username" maxlength="4" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email" />
<!--          <el-button size="mini" round @click="sendMsg">发送验证码</el-button>-->
          <span class="status">{{ statusMsg }}</span>
        </el-form-item>
        <el-form-item label="真实姓名" prop="name">
          <el-input v-model="ruleForm.name" maxlength="4" />
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

export default {
  data() {
    return {
      statusMsg: '',
      error: '',
      ruleForm: {
        textarea: '请仔细阅读以下协议',
        agreed: false,
        username: '',
        name: '',
        pwd: '',
        cpwd: '',
        email: ''
      },
      rules: {
        agreed: [{
          validator: (rule, value, callback) => {
            console.log('value:' + value)
            if (value !== true) {
              callback(new Error('请确认同意注册协议'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }],
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
        }, {
          validator: (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'))
            } else if (value !== this.ruleForm.pwd) {
              callback(new Error('两次输入密码不一致'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }]
      }
    }
  },
  layout: 'blank',
  methods: {
    sendMsg: function() {
      const self = this
      let namePass
      let emailPass
      if (self.timerid) {
        return false
      }
      this.$refs['ruleForm'].validateField('name', (valid) => {
        namePass = valid
      })
      self.statusMsg = ''
      if (namePass) {
        return false
      }
      this.$refs['ruleForm'].validateField('email', (valid) => {
        emailPass = valid
      })
      // 模拟验证码发送
      if (!namePass && !emailPass) {
        let count = 60
        self.statusMsg = `验证码已发送,剩余${count--}秒`
        self.timerid = setInterval(function() {
          self.statusMsg = `验证码已发送,剩余${count--}秒`
          if (count === 0) {
            clearInterval(self.timerid)
          }
        }, 1000)
      }
    },

    next: function() {
      if (this.active === 0) {
        this.$refs['ruleForm'].validateField('agreed', (valid) => {
          if (valid === '') {
            this.active++
          }
        })
      }
    },
    prev: function() {
      if (--this.active < 0) this.active = 0
    },

    // 模拟登录
    register: function() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          setTimeout(
              this.$router.push({
              }), 2000
          )
        }
      })
    }
  }
}
</script>

<style scoped rel="stylesheet/scss" lang="scss">
.page-register {
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

