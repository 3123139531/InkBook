<template>
  <img src="../assets/bgimg2.png" class="background-img">
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/bgimg2.png" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <span>项目名：</span>
            <span style="font-weight: 700">{{project.name}}</span>
          </div>
        </div>
        <div class="Link">
          <span class="Prototype">
            项目原型
          </span>
          <a href="https://online.visual-paradigm.com/cn/diagrams/features/uml-tool/"
             target="_blank" class="UML-link">绘制UML</a>
        </div>
        <div class="ProjectBtn">
          <el-button type="primary" round @click="RenameProBtn">重命名项目</el-button>
          <el-button type="primary" round class="delBtn" @click="delProject">删除项目</el-button>
          <el-dialog v-model="dialogFormVisible" title="输入新项目名">
            <el-form>
              <el-form-item>
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

export default {
  data () {
    return {
      userAccount: '2',
      userIdentity: '管理员',

      projectId: '1',
      project : {
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
      this.ProjectId = this.$route.params.id;
    },
    delProject () {
      ElMessage({
        message: '删除',
        type: 'success',
      });

      this.$axios.delete("/projects/{pid}", {
        pid : this.projectId
      }).then(function (response) {
        console.log(response);
      })

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
      this.project.name = this.newName;
      this.dialogFormVisible = false;
      this.newName = '';

      this.$axios.put("/projects/", {
        pid : this.projectId,
        pName : this.newName
      }).then(function (response) {
        console.log(response);
      })
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
  a {
    font-style: normal;
    text-decoration: none;
  }

  .background-img {
    position: absolute;
    width: 100%;
    height: 100%;
    margin-left: -50%;
    margin-top: -8px;
  }

  .ProjectInfo {
    position: relative;
    left: 0;
    top: 10px;
    width: 90%;
    margin: 0 auto;
    height: 700px;
  }

  .ProjectHeader {
    height: 105px;
    border: 1px black solid;
    border-radius: 20px;
    background: white;
    /*background: rgba(255, 255, 255, 0.2);*/
  }

  .ProjectImg {
    float: left;
    display: inline-block;
    height: 80px;
    width: 80px;
    border-radius: 40px;
    margin-top: 10px;
    margin-left: 10px;
  }

  .ProjectIntro {
    display: inline-block;
    height: 100%;
    float: left;
    margin-left: 50px;
    font-size: 18px;
    text-align: left;
  }

  .ProjectName {
    line-height: 103px;
  }

  .ProjectBtn {
    display: inline-block;
    float: right;
    margin-top: 35px;
    margin-right: 80px;
  }

  .Link {
    line-height: 103px;
    display: inline-block;
  }

  .Prototype,
  .UML-link {
    display: inline-block;
    width: 100px;
    border-left: 1px black solid;
    border-right: 1px black solid;
    margin-left: 1px;
    border-radius: 5px;
    font-size: 18px;
    color: black;
    transition: 0.5s;
  }

  .Prototype:hover,
  .UML-link:hover {
    background: rgba(144, 144, 144, 0.5);
  }

  .ProjectMain {
    height: 580px;
    overflow: auto;
    border: 1px black solid;
    border-radius: 20px;
    margin-top: 10px;
    background: white;
    /*background: rgba(255, 255, 255, 0.2);*/
  }

  .toHomepageBtn {
    position: absolute;
    left: 10px;
    top: 20px;
  }

  .ProDoc {
    display: inline-block;
    float: left;
    margin: 20px;
    width: 10%;
    height: 150px;
    border-radius: 5px;
    background: transparent -webkit-linear-gradient(right, lightgreen 0%, lightblue 100%);
    border: black solid;
    text-align: left;
    transition: 0.5s;
  }

  .ProDoc:hover {
    box-shadow: 5px 5px 20px;
  }

  .ProjectMain .DocName {
    display: block;
    margin-top: 20px;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
  }

</style>
