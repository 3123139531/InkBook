<template>
  <div class="Homepage">
    <el-container>
      <el-aside class="Aside">
        <div class="Portrait">
          <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
               alt="头像" id="portrait1">
        </div>
        <div class="Personal" @click="showPersonalInfo">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M628.736 528.896A416 416 0 0 1 928 928H96a415.872 415.872 0 0 1 299.264-399.104L512 704l116.736-175.104zM720 304a208 208 0 1 1-416 0 208 208 0 0 1 416 0z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">信息</div>
          <div class="curView" v-if="option===1"></div>
        </div>
        <div class="Enterprise" @click="showEnterprise">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M192 413.952V896h640V413.952L512 147.328 192 413.952zM139.52 374.4l352-293.312a32 32 0 0 1 40.96 0l352 293.312A32 32 0 0 1 896 398.976V928a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V398.976a32 32 0 0 1 11.52-24.576z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">团队</div>
          <div class="curView" v-if="option===2"></div>
        </div>
        <div class="Enterprise" @click="toInit">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M512 64a448 448 0 1 1 0 896.064A448 448 0 0 1 512 64zm67.2 275.072c33.28 0 60.288-23.104 60.288-57.344s-27.072-57.344-60.288-57.344c-33.28 0-60.16 23.104-60.16 57.344s26.88 57.344 60.16 57.344zM590.912 699.2c0-6.848 2.368-24.64 1.024-34.752l-52.608 60.544c-10.88 11.456-24.512 19.392-30.912 17.28a12.992 12.992 0 0 1-8.256-14.72l87.68-276.992c7.168-35.136-12.544-67.2-54.336-71.296-44.096 0-108.992 44.736-148.48 101.504 0 6.784-1.28 23.68.064 33.792l52.544-60.608c10.88-11.328 23.552-19.328 29.952-17.152a12.8 12.8 0 0 1 7.808 16.128L388.48 728.576c-10.048 32.256 8.96 63.872 55.04 71.04 67.84 0 107.904-43.648 147.456-100.416z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">介绍</div>
        </div>
        <el-button type="primary" round class="Cancellation" @click="Quit">注销</el-button>
      </el-aside>
      <el-container>
        <el-main class="Main">
<!--          个人信息-->
          <div v-if="option===1" class="PersonalInfo">
            <el-header class="Header">个人信息</el-header>
            <div class="PersonalPortrait">
              <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                   alt="头像" id="portrait2">
              <el-upload
                  v-model:file-list="fileList"
                  ref="upload"
                  class="avatar-uploader"
                  action="#"
                  accept="image/png,image/jpg,image/jpeg"
                  :http-request="submitUpload"
                  :show-file-list="false"
              >
                <template #trigger>
                  <el-button type="primary" class="changePortraitBtn">更换头像</el-button>
                </template>
              </el-upload>
            </div>
            <el-form :model="info" label-width="120px" size="large" class="Format">
              <el-form-item label="用户名" class="Form-line">
                <el-input v-model="newUname" />
              </el-form-item>
              <el-form-item label="姓名" class="Form-line">
                <el-input v-model="info.unickname" />
              </el-form-item>
              <el-form-item label="邮箱" class="Form-line">
                <el-input v-model="info.email" />
              </el-form-item>
              <el-form-item label="密码" class="Form-line">
                <el-input v-model="info.password"  type="password"/>
              </el-form-item>
              <el-form-item>
                <el-button :plain="true" type="primary" @click="Submit" class="changeInfoBtn">确认修改</el-button>
                <el-button @click="Revoke" class="changeInfoBtn">撤销修改</el-button>
              </el-form-item>
            </el-form>
          </div>
<!--          所在团队-->
          <div v-if="option===2" class="PersonalEnterprises">
            <el-header class="Header">所在团队</el-header>
            <div v-for="i in numTeams" :key="i">
              <div class="divisionBox" @click="toTeamView(teams[i-1].tid, teams[i-1].tname)">
                <span class="innerChar">团队名称：{{teams[i-1].tname}}</span>
                <span class="innerChar">团队编号：{{teams[i-1].tid}}</span>
              </div>
            </div>
            <div class="newBox" @click="addNewTeamBtn">
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">
                <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>
                <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>
                <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
              </svg>
              <span class="plusChar">新建团队</span>
              <el-dialog v-model="addTeamDialog">
                <el-form>
                  <el-form-item label="请输入新团队名" :label-width="'140'">
                    <el-input v-model="newName" autocomplete="off" />
                  </el-form-item>
                </el-form>
                <template #footer>
              <span class="dialog-footer">
                <el-button @click="addTeamDialog = false">取消</el-button>
                <el-button type="primary" @click="addNewTeam">确认</el-button>
              </span>
                </template>
              </el-dialog>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
/* eslint-disable */
import { ElMessage } from 'element-plus'

export default {
  data () {
    return {
      account : 0,
      info : {},
      teams : [],
      projects : [],
      t_projects : [],
      pp : [],
      numTeams : 0,
      numProjects : 0,
      numt_Projects : 0,
      option : 2,

      addTeamDialog : false,
      addProDialog : false,
      newPro_team : 0,
      newUname: '',
      newName : '',
      proMod : 1,

      fileList: [],
      file: {}
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init () {
      // console.log(this.$route)
      this.account = this.$route.query.ac
      this.$axios.get('/team/' + this.account
      ).then(response=> {
        this.teams = response.data.data;
        this.numTeams = this.teams.length;
      });
      this.$axios.get('/user/info/' + this.account
      ).then(response=> {
        this.info = response.data.data
        if(this.info.profilePic!=null && this.info.profilePic!=='')
          document.getElementById('portrait1').src = this.info.profilePic
      });
    },
    showPersonalInfo () {
      // console.log(this.account)
      this.$axios.get('/user/info/' + this.account
      ).then(response=> {
        this.info = response.data.data
        this.newUname = this.info.uname
        if(this.info.profilePic!=null && this.info.profilePic!==''){
          document.getElementById('portrait1').src = this.info.profilePic
          document.getElementById('portrait2').src = this.info.profilePic
        }
      })
      this.option = 1;
    },
    showEnterprise () {
      // console.log(this.account)
      this.$axios.get('/team/' + this.account
      ).then(response=> {
        this.teams = response.data.data;
        this.numTeams = this.teams.length;
      });
      this.option = 2;
    },
    showProject () {
      // console.log(this.account)
      if(this.proMod===1){
        this.projects = []
        this.numProjects = 0
        for (let i=0; i<this.numTeams; i++){
          this.$axios.get('/projects/doing/'+this.teams[i].tid
          ).then(response =>{
            this.pp = response.data.data
            this.numProjects += response.data.data.length
            for(let j=0; j<response.data.data.length; j++){
            this.projects.push({pid: this.pp[j].pid, tid: this.pp[j].tid, status: this.pp[j].status, pname: this.pp[j].pname})
            }
          })
        }
      }
      else {
        this.t_projects = []
        this.numt_Projects = 0
        for (let i=0; i<this.numTeams; i++){
          this.$axios.get('/projects/trash/'+this.teams[i].tid
          ).then(response =>{
            this.pp = response.data.data
            this.numt_Projects += response.data.data.length
            for(let j=0; j<response.data.data.length; j++){
            this.t_projects.push({pid: this.pp[j].pid, tid: this.pp[j].tid, status: this.pp[j].status, pname: this.pp[j].pname})
            }
          })
        }
      }
      this.option = 3;
    },
    submitUpload(file) {
      this.file = file.file
      console.log(this.file)
      let formData = new FormData();
      formData.append('file', this.file);
      this.$axios.post('/upload/cos', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(response=>{
        console.log(response)
        if(response.data.flag===true){
          this.info.profilePic = response.data.data
          if(this.info.profilePic!=null && this.info.profilePic!==''){
            document.getElementById('portrait1').src = this.info.profilePic
            document.getElementById('portrait2').src = this.info.profilePic
            this.Submit()
          }
        }
        else {
          ElMessage({
            message: '修改失败',
            type: 'error'
          })
        }
      }).catch(err=>{
        ElMessage({
          message: '修改失败',
          type: 'error'
        })
      })
    },
    Submit () {
      this.$axios.post('/user/info/' + this.info.uname, {
        email: this.info.email,
        password: this.info.password,
        profilePic: this.info.profilePic,
        uid: 0,
        uname: this.newUname,
        unickname: this.info.unickname
      }).then(response=> {
        // console.log(response)
        this.newName = ''
        if(response.data.flag === true){
          ElMessage({
            message: '修改成功',
            type: 'success',
          })
        }
        else {
          ElMessage({
            message: '修改失败，'+response.data.msg,
            type: 'warning',
          })
        }
      })
    },
    Revoke () {
      this.showPersonalInfo()
      ElMessage({
        message: '撤回修改',
        type: 'success',
      })
    },
    toTeamView (id, name) {
      this.$router.push({
        name: 'team',
        query: {
          ac: this.account,
          userAccount: this.info.uname,
          teamId: id,
          teamName : name
        }
      })
    },
    addNewTeamBtn () {
      this.addTeamDialog = true;
    },
    // addNewProjectBtn () {
    //   this.addProDialog = true;
    // },
    addNewTeam () {
      let url = '/team/' + this.info.uname
      console.log(this.newName)
      this.$axios.post(url,{
        tbrief: "string",
        tid: 0,
        tname: this.newName,
        user_name: this.info.uname
      }).then(response =>{
        console.log(response)
        this.showEnterprise();
        this.addTeamDialog = false;
        this.newName = '';
      });
    },
    addNewPro() {
      this.$axios.post('/projects',{
        pname: this.newName,
        tid: this.newPro_team,
      }).then(response =>{
        console.log(response)
        this.projects.push({pid: response.data.data, tid: this.newPro_team, status: 'doing', pname: this.newName});
        this.numProjects ++;
        this.addProDialog = false;
        this.newName = '';
        this.newPro_team = 0;
      });
    },
    Quit () {
      ElMessage({
        message: '退出登录',
        type: 'success',
      })
      this.$router.push({
        name: 'login',
      })
    },
    toInit() {
      this.$router.push({
        name: 'init'
      })
    }
  }
}
</script>

<style scoped>

.Homepage {
    position: relative;
    top: 20px;
    display: block;
    height: 100%;
    width: 90%;
    margin: 0 auto;
  }

  .Aside {
    margin-right: 3px;
    width: 100px;
    box-shadow: 5px 0 5px #888888;
    border: 1px solid black;
    background: rgba(144, 144, 144, 0.2);
    /*background: rgba(255, 255, 255, 0.4);*/
    color: black;
  }

  .Aside #portrait1 {
    display: block;
    width: 80px;
    height: 80px;
    border-radius: 40px;
    margin: 20px auto;
  }

  .Personal, .Enterprise{
    position: relative;
    left: 0;
    top: 0;
    height: 55px;
    width: 60px;
    padding-top: 10px;
    margin: 10px auto;
    border-radius: 5px;
    transition: 0.5s;
  }

  .Personal{
    margin-top: 100px;
  }

  .Aside .Personal:hover,
  .Aside .Enterprise:hover {
    background: rgba(144, 144, 144, 0.4);
  }

  .Aside .Cancellation {
    margin-top: 200px;
    margin-bottom: 20px;
  }

  .curView {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    border-radius: 5px;
    background: rgba(144, 144, 144, 0.4);
  }

  .Main {
    height: 100%;
    background: white;
    background: rgba(144, 144, 144, 0.2);
    /*background: rgba(255, 255, 255, 0.4);*/
  }

  .Main .Header {
    height: 50px;
    line-height: 50px;
    font-size: 26px;
    font-weight: 700;
    text-align: center;
    background: rgba(255, 255, 255, 0.4);
    border-bottom: 2px black dotted;
  }

  .PersonalInfo,
  .PersonalEnterprises,
  .PersonalProjects {
    position: relative;
    width: 99%;
    height: 99%;
    border: 2px black solid;
    background: white;
    overflow: auto;
  }

  .Form-line {
    padding-top: 10px;
    padding-right: 10px;
  }

  .PersonalInfo #portrait2 {
    position: absolute;
    left: 150px;
    top: 200px;
    width: 160px;
    height: 160px;
    border-radius: 80px;
  }

  .PersonalInfo .Format {
    display: inline-block;
    width: 50%;
    margin-top: 100px;
    margin-right: 50px;
    border: 1px grey solid;
    border-radius: 5px;
    box-shadow: 5px 5px 10px #888888;
    padding-top: 10px;
    padding-right: 20px;
    color: black;
  }

  .PersonalInfo .PersonalPortrait {
    display: inline-block;
    float: left;
    width: 30%;
  }

  .changePortraitBtn {
    position: absolute;
    left: 185px;
    top: 400px;
  }

  .changeInfoBtn {
    width: 80px;
    height: 40px;
    margin-top: 10px;
    margin-right: 10px;
  }

  .divisionBox {
    display: inline-block;
    float: left;
    margin: 20px;
    width: 16%;
    height: 130px;
    border-radius: 10px;
    border: black solid;
    text-align: left;
    transition: 0.5s;
    background: lightcyan;
  }

  .innerChar {
    display: block;
    font-size: 20px;
    font-weight: 700;
    text-align: center;
    margin-top: 27px;
  }

  .newBox {
    display: inline-block;
    float: left;
    margin: 20px;
    width: 16%;
    height: 140px;
    border-radius: 10px;
    border: black solid;
    text-align: left;
    transition: 0.5s;
    background: rgba(144, 144, 144, 0.4);
  }

  .newBox .plusIcon {
    display: block;
    width: 50px;
    height: 50px;
    margin: 20px auto;
  }

  .newBox .plusChar {
    display: block;
    font-size: 18px;
    font-weight: 700;
    text-align: center;
  }

  .divisionBox:hover,
  .newBox:hover {
    box-shadow: 5px 5px 20px;
  }

  .changeBtn {
    position: absolute;
    right: 15px;
    bottom: 15px;
    width: 62px;
    height: 62px;
    border: 2px black solid;
    border-radius: 31px;
    transition: 0.5s;
  }

  .changeBtn:hover{
    background: rgba(144, 144, 144, 0.5);
  }
</style>
