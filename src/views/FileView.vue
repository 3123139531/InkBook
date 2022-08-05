<template>
  <img src="../assets/bgimg2.png" class="background-img">
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/头像.jpg" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <span @click="toProjectView">项目名：</span>
            <span style="font-weight: 700" @click="toProjectView">{{$route.params.p_name}}</span>
            <span style="margin-left:30px">文档名：</span>
            <span style="font-weight: 700">{{document.dname}}</span>
          </div>
        </div>
        <div class="ProjectBtn">
          <el-button type="primary" round @click="saveFile">保存文档</el-button>
          <el-button type="primary" round @click="renameFileBtn">重命名文档</el-button>
          <el-button type="primary" round class="delBtn" @click="delFile">删除文档</el-button>
          <el-dialog v-model="dialogFormVisible" title="输入新项目名">
            <el-form>
              <el-form-item label="Promotion name" :label-width="140">
                <el-input v-model="newName" autocomplete="off" />
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="renameFile">确认</el-button>
              </span>
            </template>
          </el-dialog>
        </div>
      </el-header>
      <el-main class="ProjectMain">
        <div style="font:normal 700 20px/30px Georgia, serif;">{{$route.params.programName}}</div>
        <div class="order">
            <div id="vditor"></div>
        </div>
      </el-main>
    </el-container>
  </div>
  <el-button class="toHomepageBtn" @click="toHomeView">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="width: 15px; height: 20px">
      <path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path>
    </svg>
  </el-button>
</template>

<script>
/* eslint-disable */
import Vditor from "vditor"
import "vditor/dist/index.css"

export default {
  data () {
    return {
      contentEditor:"",
      pid: 0,
      userAccount: 0,
      userIdentity: '管理员',

      documentId : 0,
      document : {},

      newName : '',
      dialogFormVisible : false
    }
  },
  mounted() {
    this.init();
    console.log(this.$route)
    this.contentEditor = new Vditor("vditor",{
            height:690,
            mode:'ir',
            toolbarConfig:{
                pin:true
            },
            cache:{
                enable:false
            },
            after:()=>{
                this.contentEditor.setValue(document.dcontent)
            }
        })
  },
  methods : {
    init () {
      this.userAccount = this.$route.params.ac
      this.documentId = this.$route.params.d_id
      this.pid = this.$route.params.p_id
      this.$axios.get('/documents/'+this.documentId
      ).then(response =>{
        this.document = response.data.data
        console.log(this.document)
      })
    },
    delFile () {
      this.$router.push({
        name : 'home',
        params : {
          ac : this.userAccount
        }
      })
    },
    saveFile () {
      this.$axios.get('/documents',{
        did: 1,
        dcontent: '文档1'
      })
    },
    renameFileBtn () {
      this.dialogFormVisible = true;
    },
    RenamePro () {
      this.$axios.put('/documents',{
        did: this.documentId,
        name: this.newName
      }).then(response =>{
        console.log(response)
      })
      this.document.name = this.newName;
      this.dialogFormVisible = false;
      this.newName = '';
    },
    toHomeView () {
      this.$router.push({
        name: 'home',
        params : {
          ac : this.userAccount
        }
      })
    },
    toProjectView () {
      this.$router.push({
        name: 'project',
        params : {
          ac : this.userAccount,
          p_id : this.pid
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
    height: 780px;
    overflow: auto;
    padding: 40px;
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
