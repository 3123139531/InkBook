<template>
  <span class="background-img"></span>
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/bgimg.png" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <span>文档名：</span>
            <span style="font-weight: 700">{{document.dname}}</span>
            <span style="margin-left:30px">项目名：</span>
            <span style="font-weight: 700">{{$route.query.p_name}}</span>
          </div>
        </div>
        <div class="ProjectBtn">
          <el-button type="primary" @click="saveFile">保存文档</el-button>
          <el-button type="primary" class="renameBtn" v-if="document.dname==='UML'">
            <a href="https://online.visual-paradigm.com/cn/" target="_blank">前往绘制</a>
          </el-button>
          <el-button type="primary" class="renameBtn"
                     @click="renameFileBtn" v-if="document.dname!=='UML'">重命名文档</el-button>
          <el-button type="primary" class="delBtn"
                     @click="delFile" v-if="document.dname!=='UML'">删除文档</el-button>
          <el-dialog v-model="dialogFormVisible" title="输入新文档名">
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
      <el-main class="FileMain">
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
  <el-button class="toProViewBtn" @click="toProjectView" title="返回项目页面">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
         style="width: 15px; height: 20px">
      <path fill="currentColor" d="M128 192v640h768V320H485.76L357.504 192H128zm-32-64h287.872l128.384 128H928a32 32 0 0 1 32 32v576a32 32 0 0 1-32 32H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32z"></path>
    </svg>
  </el-button>
</template>

<script>
/* eslint-disable */
import { ElMessage } from 'element-plus'
import { Search } from '@element-plus/icons-vue'
import Vditor from "vditor"
import "vditor/dist/index.css"

export default {
  data () {
    return {
      contentEditor:"",
      pid: 0,
      userAccount: 0,
      teamName: '',

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
      this.userAccount = this.$route.query.ac
      this.documentId = this.$route.query.d_id
      this.pid = this.$route.query.p_id
      this.p_name = this.$route.query.p_name
      this.teamName = this.$route.query.teamName
      this.$axios.get('/documents/'+this.documentId
      ).then(response =>{
        this.document = response.data.data
        console.log(this.document)
      })
    },
    delFile () {
      this.$axios.delete('/documents/'+this.documentId,
      ).then(response =>{
        if(response.data.flag === true){
          ElMessage({
            message: '删除成功',
            type: 'success'
          })
          this.$router.push({
            name : 'project',
            query : {
              p_id: this.pid,
              ac: this.userAccount,
              teamName: this.teamName
            }
          })
        }
        else {
          ElMessage({
            message: '删除失败',
            type: 'error'
          })
        }
      })

    },
    saveFile () {
      this.$axios.get('/documents',{
        did: 1,
        dcontent: '文档1'
      }).then(response=> {
        console.log(response)
      })
    },
    renameFileBtn () {
      this.dialogFormVisible = true;
    },
    RenamePro () {
      this.$axios.put('/documents',{
        dcontent: this.document.dcontent,
        did: this.documentId,
        dname: this.newName,
        dpid: this.pid
      }).then(response =>{
        if(response.data.flag === true){
          this.document.dname = this.newName;
        }
        ElMessage({
          message: response.data.msg,
          type: (response.data.flag)?'success':'error'
        })
        this.dialogFormVisible = false;
        this.newName = '';
      })


    },
    toHomeView () {
      this.$router.push({
        name: 'home',
        query : {
          ac : this.userAccount
        }
      })
    },
    toProjectView () {
      this.$router.push({
        name: 'project',
        query : {
          ac : this.userAccount,
          p_id : this.pid,
          teamName: this.teamName
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
    color: white;
  }

  .background-img {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(144, 144, 144, 0.2);
  }

  .ProjectInfo {
    position: relative;
    left: 0;
    top: 10px;
    width: 90%;
    margin: 0 auto;
    height: 100%;
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

  .ProjectBtn .renameBtn,
  .ProjectBtn .delBtn {
    margin-left: 10px !important;
  }

  .FileMain {
    height: 760px;
    overflow: auto;
    padding: 30px;
    border: 1px black solid;
    border-radius: 20px;
    margin-top: 10px;
    background: white;
    /*background: rgba(255, 255, 255, 0.2);*/
  }

  .order {
    display: inline-block;
    width: 80%;
    height: auto;
  }

  .toHomepageBtn {
    position: fixed;
    left: 15px;
    top: 20px;
  }

  .toProViewBtn {
    position: fixed;
    left: 5px;
    top: 70px;
    margin-left: 0;
  }

  .el-button+.el-button{
    margin-left: 10px;
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
