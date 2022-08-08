<template>
  <el-tooltip class="item" effect="dark" content="点此上传头像" placement="right">
    <el-upload class="avatar-uploader"
               action="#"
               accept="image/png,image/gif,image/jpg,image/jpeg"
               :show-file-list="false"
               :before-upload="beforeAvatarUpload"
               :http-request="uploadimg"
               :limit="1"
               :on-success="handlesuccess"
               style="position: absolute;height: 150px;width: 150px;background-color: whitesmoke;border-radius: 50%"
    >
      <i class="el-icon-plus avatar-uploader-icon" style="position: absolute;left: -12px;top:-10px"></i>
    </el-upload>
  </el-tooltip>
</template>

<script>
export default {
  data(){
    return {

    }
  },
  methods: {
    uploadimg(file) {
      this.imageUrl1 = '';
      let formData = new FormData();
      formData.append('img', file.file);
      this.$axios.post(
          '/api/user/update_user_img',
          formData,
          {
            headers: { 'Content-Type': 'multipart/form-data' }
          }
      ).then(
          res => {
            if (res.data.errno === 0) {
              this.imageUrl = res.data.url;
              this.userhead = this.imageUrl;
              sessionStorage.setItem('imgurl', JSON.stringify(this.imageUrl));
              this.$message.success("头像上传成功");
              // window.location.reload();
            }
            else {
              this.$message.error(res.data.msg);
            }
          }
      )
          .catch(err => {
            console.log(err);
          })
    },
  }
}
</script>

<style scoped>

</style>
