<template>
  <div class="not-found">
    <meta http-equiv="refresh" content="5;URL=http:/www.gg.com">
    <meta http-equiv="refresh" content="5;URL=/login">
    <h1 style="margin-bottom:25px;">注册成功!</h1>
    <h3>将于<span>&nbsp;{{initTime}}&nbsp;</span>秒后返回登录界面</h3>
  </div>
</template>

<script>
/* eslint-disable */
  export default {
    name: "NotFound",
    data() {
      return {
        initTime: 5,
        code:"",
      }
    },
    mounted() {
      this.init();
    },
    methods: {
      init() {
          this.code=this.$route.query.url;
          console.log(this.code);
          var data={code:this.code};
          this.$axios.get('/lookCode/'+this.code
          ).then( response =>{
            console.log(response);
          })
      },
      leftTime() {
        this.initTime--
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
