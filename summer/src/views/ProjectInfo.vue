<template>
  <img src="../assets/bgimg2.png" class="background-img">
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/头像.jpg" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <span>项目名：</span>
            <span style="font-weight: 700">{{Project.name}}</span>
          </div>
        </div>
        <div class="ProjectBtn">
          <el-button type="primary" round @click="RenameProBtn">重命名项目</el-button>
          <el-button type="primary" round class="delBtn" @click="delProject">删除项目</el-button>
          <el-dialog v-model="dialogFormVisible" title="输入新项目名">
            <el-form>
              <el-form-item label="Promotion name" :label-width="140">
                <el-input v-model="newName" autocomplete="off" />
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="RenamePro">确认</el-button>
              </span>
            </template>
          </el-dialog>
        </div>
      </el-header>
      <el-main class="ProjectMain">
        <div style="font:normal 700 20px/30px Georgia, serif;">项目文档</div>
        <div v-for="doc in documents" class="ProDoc">
          <span class="DocName">{{doc.name}}</span>
        </div>
      </el-main>
    </el-container>
  </div>
  <el-button class="toHomepageBtn" @click="toHomepage">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="width: 15px; height: 20px">
      <path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path>
    </svg>
  </el-button>
</template>

<script>
import { ElMessage } from 'element-plus'
import { Search } from '@element-plus/icons-vue'

export default {
  data () {
    return {
      userAccount: '2',
      userIdentity: '管理员',

      ProjectId: '1',
      Project : {
        name: "六轮车",
        setTime: "2022-08-01",
        remark: "听我说谢谢你",
      },

      documents : [
        {
          name : '小学期'
        }
      ],
      numDocuments : 0,

      newName : '',
      dialogFormVisible : false
    }
  },
  mounted() {
    this.init();
  },
  methods : {
    init () {

    },
    delProject () {
      this.$router.push({
        name : 'Homepage',
        params : {
          account : this.userAccount
        }
      })
    },
    RenameProBtn () {
      this.dialogFormVisible = true;
    },
    RenamePro () {
      this.Project.name = this.newName;
      this.dialogFormVisible = false;
      this.newName = '';
    },
    toHomepage () {
      this.$router.push({
        name: 'Homepage',
        params : {
          account : this.userAccount
        }
      })
    }
  }
}
</script>

<style scoped>
@import "../style/ProjectInfo.css";
</style>
