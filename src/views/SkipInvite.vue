<template>
  <div className="not-found">
    <meta http-equiv="refresh" content="5;URL=http:/www.gg.com">
    <meta http-equiv="refresh" content="5;URL=/login">
    <h1 style="margin-bottom:25px;">注册成功!</h1>
    <h3>将于<span>&nbsp;{{ initTime }}&nbsp;</span>秒后返回登录界面</h3>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  data() {
    return {
      initTime: 5,
      code: "",
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
    },
    leftTime() {
      this.initTime--
      if (this.initTime === 1) {
        var url = window.location.href;
        var uname = url.split('/')[4]
        var tid = url.split('/')[5]
        console.log(uname)
        console.log(tid)
        this.$axios.get('/invite/temp/'+uname+'/'+tid
        ).then(response=>{
          console.log(response)
        })
      }
    }
  },
  created() {
    setInterval(this.leftTime, 1000)
  }
}
</script>

<style scoped>
.not-found {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

h1 {
  margin-top: 100px;
}

span {
  color: dodgerblue;
}
</style>
