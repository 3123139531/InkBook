<template>
  <span class="background-img"></span>
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/bgimg.png" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <div>
              <span>项目名：</span>
              <span style="font-weight: 700">{{project.pname}}</span>
              <span style="margin-left:30px">团队名：</span>
              <span style="font-weight: 700">{{teamName}}</span>
            </div>
          </div>
        </div>
        <div class="ProjectBtn">
          <div v-if="project.status==='doing'">
            <el-button type="primary" class="copyBtn" @click="copyProBtn">复制项目</el-button>
            <el-button type="primary" @click="RenameProBtn">重命名项目</el-button>
            <el-button type="primary" class="delBtn" @click="delProject">删除项目</el-button>
            <el-dialog v-model="ProDialog" title="输入新项目名">
              <el-form>
                <el-form-item>
                  <el-input v-model="newName" autocomplete="off" />
                </el-form-item>
              </el-form>
              <template #footer>
              <span class="dialog-footer">
                <el-button @click="ProDialog = false">取消</el-button>
                <el-button type="primary" @click="RenamePro">确认</el-button>
              </span>
              </template>
            </el-dialog>
          </div>
          <div v-else-if="project.status==='finish'">
            <el-button type="primary" class="delBtn" @click="delProject">删除项目</el-button>
          </div>
          <div v-else>
            <el-button type="primary" class="delBtn" @click="recoverProject">恢复项目</el-button>
            <el-button type="primary" class="delBtn" @click="delProject">彻底删除</el-button>
          </div>
        </div>
      </el-header>
      <el-main class="ProjectMain">
        <el-row>
        <el-col :span="10">
        <div style="font:normal 700 20px/30px Georgia, serif; text-align: center">项目文档</div>
        <table class="docTable">
          <tr>
            <th>文档名</th>
            <th>操作</th>
          </tr>
          <tr v-for="i in numDocuments" :key="i">
            <td>{{documents[i-1].dname}}</td>
            <td>
              <span @click="toFileView(i)">编辑文档</span>
              <span @click="delFile(i)" v-if="documents[i-1].dname!=='UML'">删除文档</span>
            </td>
          </tr>
        </table>
        </el-col>
        <el-cow :span="2">
        <el-tooltip
            class="box-item"
            effect="dark"
            content="点击新建文档"
            placement="right"
        >
          <div class="ProDoc" @click="addNewFileBtn" v-if="project.status==='doing'">
            <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">
              <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>
              <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>
              <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
            </svg>
            <span style="display: block; text-align: center; line-height: 40px; font-weight: bold">新建文档</span>
            <!--                <div class="plusChar">新建文档</div>-->
          </div>
        </el-tooltip>
        
        <el-dialog v-model="addFileDialog" title="输入新文档名">
          <el-form>
            <el-form-item>
              <el-input v-model="newFile" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
              <span class="dialog-footer">
                <el-button @click="addFileDialog = false">取消</el-button>
                <el-button type="primary" @click="addNewFile">确认</el-button>
              </span>
          </template>
        </el-dialog>
        </el-cow>
        <el-col :span="10">
        <div style="font:normal 700 20px/30px Georgia, serif; text-align: center">项目原型</div>
        <table class="docTable">
          <tr>
            <th>原型名</th>
            <th>操作</th>
          </tr>
          <tr v-for="i in numDesigns" :key="i">
            <td>{{designs[i-1].pgName}}</td>
            <td>
              <span @click="toDesignView(i)">编辑原型</span>
              <span @click="delDesign(i)">删除原型</span>
            </td>
          </tr>
        </table>
        </el-col>
        <el-cow :span="2">
        <el-tooltip
            class="box-item"
            effect="dark"
            content="点击新建原型"
            placement="right"
        >
          <div class="ProDoc" @click="addNewDesignBtn" v-if="project.status==='doing'">
            <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">
              <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>
              <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>
              <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
            </svg>
            <span style="display: block; text-align: center; line-height: 40px; font-weight: bold">新建原型</span>
          </div>
        </el-tooltip>
        
        <el-dialog v-model="addDesignDialog" title="输入新原型名">
          <el-form>
            <el-form-item>
              <el-input v-model="newDesign" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
              <span class="dialog-footer">
                <el-button @click="addDesignDialog = false">取消</el-button>
                <el-button type="primary" @click="addNewDesign">确认</el-button>
              </span>
          </template>
        </el-dialog>
        </el-cow>
        </el-row>
        
      </el-main>
    </el-container>
  </div>
  <el-button class="toHomepageBtn" @click="toHomeView" title="返回首页">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
         style="width: 15px; height: 20px">
      <path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path>
    </svg>
  </el-button>
  <el-button class="toTeamViewBtn" @click="toTeamView" title="返回团队页面">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
         style="width: 15px; height: 20px">
      <path fill="currentColor" d="M192 413.952V896h640V413.952L512 147.328 192 413.952zM139.52 374.4l352-293.312a32 32 0 0 1 40.96 0l352 293.312A32 32 0 0 1 896 398.976V928a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V398.976a32 32 0 0 1 11.52-24.576z"></path>
    </svg>
  </el-button>
</template>

<script>
/* eslint-disable */

import {ElMessage} from "element-plus";

export default {
  data () {
    return {
      userAccount: '',
      teamName: '',

      projectId: 0,
      project : {},

      documents : [],
      numDocuments : 0,

      designs : [],
      numDesigns : 0,

      newName : '',
      newFile : '',
      newDesign : '',

      ProDialog : false,
      addDesignDialog : false,
      addFileDialog : false
    }
  },
  mounted() {
    this.init();
  },
  methods : {
    init () {
      this.projectId = this.$route.query.p_id
      this.userAccount = this.$route.query.ac
      this.teamName = this.$route.query.teamName
      // console.log(this.teamName)
      this.$axios.get('/projects/'+this.projectId
      ).then(response =>{
        this.project = response.data.data
        console.log(this.project)
      })
      this.getDocs()
      this.getDesigns()
    },
    getDocs() {
      this.$axios.get('/documents/project/'+this.projectId
      ).then(response =>{
        // console.log(response)
        this.documents = response.data.data.documents
        this.numDocuments = response.data.data.documents.length
      })
    },
    getDesigns() {
      this.$axios.get('pages/project/'+this.projectId
      ).then(response =>{
        this.designs = response.data.data
        this.numDesigns = response.data.data.length
      })
    },
    copyProBtn() {
      this.$axios.get('/projects/copy/' + this.projectId, {
        params: {
          pid: this.projectId
        }
      }).then(response=> {
        // console.log(response)
        ElMessage({
          message: '复制成功',
          type: 'success'
        })
      })
    },
    recoverProject() {
      this.$axios.put('/projects/doing/'+this.projectId,{
        pid: this.projectId
      }).then(response =>{
        console.log(response)
        ElMessage({
          message: '恢复成功',
          type: 'success'
        })
        this.project.status = 'doing'
      })
    },
    delProject () {
      if(this.project.status==='trash'){
        this.$axios.delete('/projects/!/'+this.projectId
        ).then(response =>{
          console.log(response)
        })
      }
      else{
        this.$axios.delete('/projects/'+this.projectId
        ).then(response =>{
          console.log(response)
        })
      }
      ElMessage({
        message: '删除成功',
        type: 'success'
      })
      this.toTeamView()
    },
    RenameProBtn () {
      this.ProDialog = true;
    },
    RenamePro () {
      this.$axios.put('/projects',{
        pid: this.projectId,
        pname: this.newName
      }).then(response =>{
        console.log(response)
      })
      this.project.pname = this.newName;
      this.ProDialog = false;
      this.newName = '';
    },
    addNewFileBtn () {
      this.addFileDialog = true;
    },
    addNewFile() {
      this.$axios.post('/documents',{
        dcontent: "",
        did: 0,
        dname: this.newFile,
        dpid: this.projectId
      }).then(response =>{
        console.log(response)
        // console.log(this.newFile)
        // console.log(this.documents)
        if(response.data.flag===true){
          ElMessage({
            message: '文档创建成功',
            type: 'success'
          })
        }
        this.getDocs()
        this.addFileDialog = false
        this.newFile = ''
      });
    },
    addNewDesignBtn () {
      this.addDesignDialog = true;
    },
    addNewDesign() {
      this.$axios.put('/pages',{
        pgContent: '',
        pgName: this.newDesign,
        pgPid: this.project.pid
      }).then(response =>{
        console.log(response)
        // console.log(this.newFile)
        // console.log(this.documents)
        if(response.data.flag===true){
          ElMessage({
            message: '原型创建成功',
            type: 'success'
          })
        }
        this.getDesigns()
        this.addDesignDialog = false
        this.newDesign = ''
      });
    },
    delFile (id) {
      this.$axios.delete('/documents/'+this.documents[id-1].did,
      ).then(response =>{
        if(response.data.flag === true){
          ElMessage({
            message: '删除成功',
            type: 'success'
          })
          this.getDocs()
        }
        else {
          ElMessage({
            message: '删除失败',
            type: 'error'
          })
        }
      })
    },
    delDesign (id) {
      this.$axios.delete('/pages/'+this.designs[id-1].pgId,
      ).then(response =>{
        if(response.data.flag === true){
          ElMessage({
            message: '删除成功',
            type: 'success'
          })
          this.getDocs()
        }
        else {
          ElMessage({
            message: '删除失败',
            type: 'error'
          })
        }
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
    toTeamView() {
      this.$router.push({
        name: 'team',
        query : {
          userAccount : this.userAccount,
          teamId: this.project.tid,
          teamName: this.teamName
        }
      })
    },
    toFileView (id) {
      this.$router.push({
        name: 'file',
        query : {
          ac : this.userAccount,
          d_id : this.documents[id-1].did,
          p_id : this.projectId,
          p_name : this.project.pname,
          teamName: this.teamName
        }
      })
    },
    toDesignView (id) {
      this.$router.push({
        name: 'design',
        query : {
          ac : this.userAccount,
          pg_id : this.designs[id-1].pgId,
          //pg_id : this.designs[id-1].pgId,
          p_id : this.projectId,
          p_name : this.project.pname,
          teamName: this.teamName
        }
      })
    },
  }
}
</script>

<style scoped>
  a {
    font-style: normal;
    text-decoration: none;
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

  .ProjectBtn .delBtn {
    margin-left: 10px !important;
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

  .docTable {
    display: inline-block;
    border: 1px black solid;
    width: 60%;
    margin-top: 10px;
    /*margin-left: 16%;*/
  }

  .docTable tr {
    display: block;
    height: 40px;
  }

  .docTable tr th {
    display: inline-block;
    width: calc(50% - 1px);
    padding: 0;
    line-height: 40px;
    font-size: 17px;
  }

  .docTable td {
    display: inline-block;
    width: calc(50% - 1px);
    padding: 0;
    line-height: 40px;
    font-size: 15px;
    border-top: 1px black solid;
  }

  .docTable tr th:first-child,
  .docTable td:first-child {
    border-right: 1px black solid;
  }

  .docTable td span {
    margin-left: 10%;
    cursor: pointer;
    color: #1890ff;
  }

  .docTable td span:hover {
    text-decoration: underline;
  }

  .docTable td span:first-child {
    margin-left: 0;
  }

  .docTable tr:nth-child(even) {
    background: rgba(144, 144, 144, 0.1);
  }

  .docTable tr:last-child {
    border-bottom: 0;
  }

  .toHomepageBtn {
    position: fixed;
    left: 15px;
    top: 20px;
  }

  .toTeamViewBtn {
    position: fixed;
    left: 5px;
    top: 70px;
    margin-left: 0;
  }

  .el-button+.el-button{
    margin-left: 10px;
  }

  .prototype,
  .ProDoc {
    top: 30%;
    right: 15%;
    width: 80px;
    height: 80px;
    border-radius: 5px;
    border: black solid;
    text-align: left;
    transition: 0.5s;
    cursor: pointer;
  }

  .prototype {
    top: 40%;
  }

  .prototype:hover,
  .ProDoc:hover {
    box-shadow: 5px 5px 20px;
  }

  .ProDoc .plusIcon {
    display: block;
    width: 40px;
    height: 40px;
    margin: 1px auto;
  }

  .ProDoc .plusChar {
    display: block;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
  }

  .ProjectMain .DocName {
    display: block;
    line-height: 50px;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
  }


</style>
