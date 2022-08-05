<template>
  <img src="../assets/bgimg2.png" class="background-img">
  <div class="ProjectInfo">
    <el-container>
      <el-header class="ProjectHeader">
        <img src="../assets/头像.jpg" class="ProjectImg">
        <div class="ProjectIntro">
          <div class="ProjectName">
            <span>项目名：</span>
            <span style="font-weight: 700">{{project.pname}}</span>
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
        <div v-for="i in numDocuments" class="ProDoc" :key="i" @click="toFileView(i)">
          <span class="DocName">{{documents[i-1].dname}}</span>
        </div>
        <div class="ProDoc" @click="addNewFileBtn">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">
                  <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>
                  <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>
                  <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
                </svg>
                <div class="plusChar">新建文档</div>
                <el-dialog v-model="addFileDialog">
                  <el-form>
                    <el-form-item label="文档名" :label-width="100">
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

export default {
  data () {
    return {
      userAccount: '2',
      userIdentity: '管理员',

      projectId: 0,
      project : {},

      documents : [],
      numDocuments : 0,

      newName : '',
      newFile : '',
      dialogFormVisible : false,
      addFileDialog : false
    }
  },
  mounted() {
    this.init();
  },
  methods : {
    init () {
      this.projectId = this.$route.params.p_id
      this.userAccount = this.$route.params.ac
      this.$axios.get('/projects/'+this.projectId
      ).then(response =>{
        this.project = response.data.data
        console.log(this.project)
      })
      this.$axios.get('/documents/project/'+this.projectId
      ).then(response =>{
        this.documents = response.data.data.documents
        this.numDocuments = response.data.data.documents.length
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
      this.$router.push({
        name : 'home',
        params : {
          account : this.userAccount
        }
      })
    },
    RenameProBtn () {
      this.dialogFormVisible = true;
    },
    RenamePro () {
      this.$axios.put('/projects',{
        pid: this.projectId,
        pname: this.newName
      }).then(response =>{
        console.log(response)
      })
      this.project.pname = this.newName;
      this.dialogFormVisible = false;
      this.newName = '';
    },
    addNewFileBtn () {
      this.addFileDialog = true;
    },
    addNewFile() {
      this.$axios.post('/documents',{
        name: this.newFile,
        pid: this.projectId,
      }).then(response =>{
        console.log(response)
        console.log(this.newFile)
        console.log(this.documents)
        this.documents.push({content: '', did: response.data.data, dname: this.newFile, pid: this.projectId});
        console.log(this.documents)
        this.numDocuments ++;
        this.addFileDialog = false;
        this.newFile = '';
      });
    },
    toHomeView () {
      this.$router.push({
        name: 'home',
        params : {
          ac : this.userAccount
        }
      })
    },
    toFileView (id) {
      this.$router.push({
        name: 'file',
        params : {
          ac : this.userAccount,
          d_id : this.documents[id-1].did,
          p_id : this.projectId,
          p_name : this.project.pname
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

  .ProDoc .plusIcon {
    display: block;
    width: 50px;
    height: 50px;
    margin: 30px auto;
  }

  .ProDoc .plusChar {
    display: block;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
  }

  .ProjectMain .DocName {
    display: block;
    margin-top: 20px;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
  }

  
</style>
