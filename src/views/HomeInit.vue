<template>
  <div id="div-main-wrap">
    <!-- header -->
    <div class="h-title">
      <div class="div-title">
        <div class="music-control" @click="musicCtrol">
          <img v-if="!musicState" src="~@/assets/img/music.png" alt="" />
          <img v-else src="~@/assets/img/music.png" alt="" />
        </div>
        <audio ref="audio" controls hidden>
          <source src="~@/assets/music/777888.mp3" type="audio/mpeg" />
        </audio>
        <!-- <iframe
          ref="audio"
          allow="autoplay"
          style="display: none"
          src="~@/assets/music/777888.mp3"
        ></iframe> -->
      </div>
    </div>
    <!-- 轮播图 -->
    <slide-show></slide-show>
    <!-- 主体 -->
    <div class="main-area">
      <!-- 简介 -->
      <profile></profile>
      <!-- 长引用 -->
      <div class="quote-wrap">
        <blockquote>
          <p>The ink book for you.</p>
        </blockquote>
      </div>
      <!-- 照片墙 -->
      <picture-wall></picture-wall>
      <!-- 计时器 -->
      <section class="timer" id="time">
        <div class="timer-wrap">
          <div class="time-header">
            <h2 class="newh2">当前时间</h2>
            <hr class="line" />
            <span class="intro">Current&nbsp;Time</span>
          </div>
          <div class="time-area">
            <div>{{ hour }}</div>
            <div>{{ minute }}</div>
            <div>{{ second }}</div>
          </div>
        </div>
      </section>
      <footer><div class="fotter"></div></footer>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import SlideShow from "@/components/SlideShow.vue";
import Profile from "@/components/ProFile.vue";


import PictureWall from "@/components/PictureWall.vue";

export default {
  setup() {
    const audio = ref(null);
    const musicState = ref(false);
    // const clickA = () => {
    //   audio.value.play();
    // };
    const musicCtrol = () => {
      if (!musicState.value) {
        audio.value.play();
        musicState.value = true;
      } else {
        audio.value.pause();
        musicState.value = false;
      }
    };
    // onMounted(() => {
    //   // 监听全局点击事件
    //   window.addEventListener("click", clickA);
    //   // audio.value.play();
    //   console.log(audio.value);
    // });
    // 时间
    const hour = ref(0);
    const minute = ref(0);
    const second = ref(0);
    onMounted(() => {
      setInterval(() => {
        let date = new Date();
        hour.value = date.getHours();
        minute.value = date.getMinutes();
        second.value = date.getSeconds();
      }, 1000);
    });
    return {
      audio,
      musicState,
      hour,
      minute,
      second,
      musicCtrol,
    };
  },
  components: {
    SlideShow,
    Profile,
    PictureWall,
  },
};
</script>

<style >
/* 去掉了scoped 其样式可以被其子组件使用 */
.newh2 {
  font-weight: 600;
  color: #333;
  letter-spacing: 1px;
  font-family: "Ek Mukta";
  font-size: 36px;
  margin: 0;
}
a {
  text-decoration: none;
}
ul {
  /* 去除默认样式 */
  padding: 0;
  /*去除项目符号 (小点)*/
  list-style: none;
}
#div-main-wrap {
  width: 100%;
}
.h-title {
  position: sticky;
  top: 0px;
  z-index: 9999;
  background-color: white;
  /* border-bottom: 1px #777 solid; */
  box-shadow: 0 1px 7px rgb(0 0 0 / 10%);
}
.music-control {
  position: absolute;
  top: 30px;
  right: 40px;
}
.music-control img {
  height: 30px;
  width: 30px;
  opacity: 0.8;
}
.div-title h1 {
  color: #777;
  text-align: center;
  margin: 0px 0px 15px 0px;
  padding-top: 15px;
}
.navv {
  margin: 15px auto 8px;
  display: flex;
  justify-content: center;
  width: 900px;
  padding: 10px 0px;
  border-top: 1px #ddd solid;
}
.navv a {
  font-size: 18px;
  padding: 0px 20px;
  font-weight: bold;
  color: #777;
}
.main-area {
  width: 100vw;
  background-color: rgb(247, 247, 247);
}
/* 长引用 */
.quote-wrap {
  padding: 10px 150px;
  background-color: #585a57;
  height: 300px;
  background-image: url("~@/assets/img/h2.png");
  background-repeat: no-repeat;
  background-attachment: fixed; /*让图片固定住而不是随网页和div移动*/
  background-size: 100%; /*这个属性是让图片大小填充div的关键*/
}
.quote-wrap p {
  font-size: 38px;
  /* line-height: 100%; */
  margin-top: 105px;
  color: white;
}
.quote-wrap blockquote {
  position: relative;
  padding: 10px 60px 10px;
}
.quote-wrap blockquote::before,
.quote-wrap blockquote::after {
  position: absolute;
  font-size: 90px;
  color: white;
}
.quote-wrap blockquote::before {
  /* line-height: 1; */
  content: "\201C";
  top: 0;
  left: 0;
}
.quote-wrap blockquote::after {
  top: 0;
  right: 0;
  content: "\201D";
}
/* time */
.time-area {
  padding: 10px 200px;
  width: 610px;
  display: flex;
  justify-content: space-between;
  margin: 0px auto;
}
.time-area div {
  height: 100px;
  width: 200px;
  background-color: rgb(51, 51, 51);
  color: white;
  font-size: 33px;
  line-height: 100px;
}
/* fotter */
.fotter {
  height: 60px;
  background-color: rgb(48, 48, 48);
  text-align: center;
  font-size: 16px;
  color: #666;
  line-height: 60px;
  font-family: "Ek Mukta", sans-serif;
}
</style>
