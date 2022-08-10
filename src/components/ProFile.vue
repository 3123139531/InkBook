<template>
  <section class="profile-section" id="profile">
    <!-- 简介 -->
    <div class="profile-container">
      <div class="left-area">
        <div class="bg-area">
          <a
            href="https://cn.bing.com/images/search?q=%e5%a2%a8%e4%b9%a6&id=161FB6B51A34DDAC69B3F09F45FE0C8AE6648ABE&form=IQFRBA&tsc=ImageHoverTitle&first=1&disoverlay=1"
            target="_blank"
            ><img
              src="~@/assets/image/living-room.jpg"
              alt=""
              class="img-curry c-img-left"
          /></a>
          <h3>模块区分</h3>
          <p>
            本墨书的功能需求分为三大模块：管理团队、执行项目、文档中心。在管理团队界面中，你能直观地看到所属团队的信息和成员。在执行项目界面中，具有管理项目、设计原型、绘制图、多人在线编辑文档四大功能。在文档编辑功能方面，文档中心的功能需求与项目的文档区一致，包括即时渲
染、共享编辑、文档模板、导出文件。
          </p>
          <a
            href="https://cn.bing.com/images/search?q=%e5%a2%a8%e4%b9%a6&id=161FB6B51A34DDAC69B3F09F45FE0C8AE6648ABE&form=IQFRBA&tsc=ImageHoverTitle&first=1&disoverlay=1"
            target="_blank"
            ><i class="fa fa-weibo"></i
          ></a>
          <br />
          <div
            http-equiv="refresh"
            @click="$router.push('/login')"
            target="_blank"
            class="btn-a"
            >现在登录</div
          >
        </div>
      </div>

      <div class="right-area">
        <div class="bg-area">
          <a
            href="https://cn.bing.com/images/search?q=%e5%a2%a8%e4%b9%a6&id=161FB6B51A34DDAC69B3F09F45FE0C8AE6648ABE&form=IQFRBA&tsc=ImageHoverTitle&first=1&disoverlay=1"
            target="_blank"
            ><img
              src="~@/assets/image/furniture.jpg"
              alt=""
              class="img-curry c-img-right"
          /></a>
          <h3>功能说明</h3>
          <p>
           本项目具备最基本的管理团队、管理项目的功能，涉及项目产品原型设计、文档共享编辑等。同时，该项目还关注于站在用户的角度，注重用户的隐私性、操作方便性等。
          </p>
          <!-- 使用了图标库font-awesome -->
          <a
            href="https://cn.bing.com/images/search?q=%e5%a2%a8%e4%b9%a6&id=161FB6B51A34DDAC69B3F09F45FE0C8AE6648ABE&form=IQFRBA&tsc=ImageHoverTitle&first=1&disoverlay=1"
            target="_blank"
            ><i class="fa fa-weibo"></i
          ></a>
          <br />
          <div
            @click="$router.push('/register')"
            target="_blank"
            class="btn-a"
            >马上注册</div
          >
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
.profile-section {
  padding: 50px 15px 50px;
  margin: 20px auto;
}
.profile-container {
  width: 1220px;
  padding-left: 15px;
  padding-right: 150px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 120px;
}
.left-area,
.right-area {
  display: inline-block;
  text-align: center;
  width: 50%;
  height: 550px;
  position: relative;
  /* margin: 0px 20px; */
}
.img-curry {
  border-radius: 50%;
  width: 350px;
  height: 350px;
  margin-top: -120px;
  border: 10px solid rgb(215, 215, 215);
}
.img-curry:hover {
  border: 10px solid rgb(140, 140, 140);
}
.left-area p,
.right-area p {
  width: 90%;
  line-height: 25px;
  font-size: 17px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
  padding: 0px 20px;
  margin: 0px 10px !important;
}
.bg-area {
  background-color: rgb(253, 253, 253);
  margin: 0px 50px;
  height: 590px;
  box-shadow: 0px 56px 36px -60px #121d12;
}
.btn-a {
  text-decoration: none;
  background-color: rgb(0, 161, 214);
  padding: 10px 20px;
  display: inline-block;
  border-radius: 3px;
  color: white;
  margin: 10px auto 30px;
}
.btn-a:hover {
  background-color: rgb(0, 181, 229);
}
.fa {
  color: #777;
  font-size: 20px;
  margin: 5px 0px;
  padding: 5px;
}
.fa:hover {
  color: rgb(230, 22, 45);
  border-radius: 50%;
  /* border: 1px solid rgb(217,0,19); */
  padding: 5px;
}
</style>
