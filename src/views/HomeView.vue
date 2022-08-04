<template>
  <img src="../assets/background.png" class="background-img">
  <div class="Homepage">
    <el-container>
      <el-aside class="Aside">
        <div class="Portrait">
          <img src="../assets/头像.jpg" alt="头像" class="portrait">
        </div>
        <div class="Personal" @click="showPersonalInfo">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M628.736 528.896A416 416 0 0 1 928 928H96a415.872 415.872 0 0 1 299.264-399.104L512 704l116.736-175.104zM720 304a208 208 0 1 1-416 0 208 208 0 0 1 416 0z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">信息</div>
        </div>
        <div class="Enterprise" @click="showEnterprise">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M192 413.952V896h640V413.952L512 147.328 192 413.952zM139.52 374.4l352-293.312a32 32 0 0 1 40.96 0l352 293.312A32 32 0 0 1 896 398.976V928a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V398.976a32 32 0 0 1 11.52-24.576z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">团队</div>
        </div>
        <div class="Project" @click="showProject">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M128 192v640h768V320H485.76L357.504 192H128zm-32-64h287.872l128.384 128H928a32 32 0 0 1 32 32v576a32 32 0 0 1-32 32H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">项目</div>
        </div>
        <el-button type="primary" round class="Cancellation" @click="Quit">注销</el-button>
      </el-aside>
      <el-container>
        <el-main class="Main">
<!--          个人信息-->
          <div v-if="option===1" class="PersonalInfo">
            <el-header class="Header">个人信息</el-header>
            <div class="PersonalPortrait">
              <img src="../assets/头像.jpg" alt="头像" class="portrait">
              <el-upload
                  ref="upload"
                  class="upload-demo"
                  action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                  :limit="1"
                  :auto-upload="true"
              >
                <template #trigger>
                  <el-button type="primary" class="changePortraitBtn">更换头像</el-button>
                </template>
              </el-upload>
            </div>
            <el-form :model="info" label-width="120px" size="large" class="Format">
              <el-form-item label="用户名" class="Form-line">
                <el-input v-model="info.username" />
              </el-form-item>
              <el-form-item label="账号">
                <el-input v-model="info.account" />
              </el-form-item>
              <el-form-item label="姓名">
                <el-input v-model="info.name" />
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input v-model="info.email" />
              </el-form-item>
              <el-form-item label="生日">
                <el-col :span="11">
                  <el-date-picker
                      v-model="$data.info.birthday"
                      type="date"
                      placeholder="Pick a date"
                      style="width: 100%"
                  />
                </el-col>
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
              <div class="divisionBox" @click="toTeamView(i, info.account)">
                <span class="innerChar">团队名称：{{teams[i-1].EName}}</span>
                <span class="innerChar">团队编号：{{teams[i-1].id}}</span>
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
<!--          参与项目-->
          <div v-if="option===3" class="PersonalProjects">
            <div v-if="proMod===1">
              <el-header class="Header">参与项目</el-header>
              <div v-for="i in numProjects" :key='i'>
                <div class="divisionBox" @click="toProjectView1(i)">
                  <span class="innerChar">项目名称：{{projects[i-1].pname}}</span>
                  <span class="innerChar">团队编号：{{projects[i-1].tid}}</span>
                </div>
              </div>
              <div class="newBox" @click="addNewProjectBtn">
                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">
                  <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>
                  <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>
                  <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>
                </svg>
                <div class="plusChar">新建项目</div>
                <el-dialog v-model="addProDialog">
                  <el-form>
                    <el-form-item label="团队编号" :label-width="100">
                      <el-input v-model="newPro_team" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="项目名" :label-width="100">
                      <el-input v-model="newName" autocomplete="off" />
                    </el-form-item>
                  </el-form>
                  <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="addProDialog = false">取消</el-button>
                      <el-button type="primary" @click="addNewPro">确认</el-button>
                    </span>
                  </template>
                </el-dialog>
              </div>
              <div class="changeBtn" @click="changeProMod">
                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                style="width: 20px; height: 20px; margin-top: 5px">
                  <path fill="currentColor" d="M878.08 448H241.92l-96 384h636.16l96-384zM832 384v-64H485.76L357.504 192H128v448l57.92-231.744A32 32 0 0 1 216.96 384H832zm-24.96 512H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32h287.872l128.384 128H864a32 32 0 0 1 32 32v96h23.04a32 32 0 0 1 31.04 39.744l-112 448A32 32 0 0 1 807.04 896z"></path>
                </svg>
                <div style="text-align: center">回收站</div>
              </div>
            </div>
            <div v-else>
              <el-header class="Header">项目回收站</el-header>
              <div v-for="i in numt_Projects" :key='i'>
                <div class="divisionBox" @click="toProjectView2(i)">
                  <span class="innerChar">项目名称：{{t_projects[i-1].pname}}</span>
                  <span class="innerChar">团队编号：{{t_projects[i-1].tid}}</span>
                </div>
              </div>
              <div class="changeBtn" @click="changeProMod">
                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                     style="width: 20px; height: 20px; margin-top: 5px">
                  <path fill="currentColor" d="M878.08 448H241.92l-96 384h636.16l96-384zM832 384v-64H485.76L357.504 192H128v448l57.92-231.744A32 32 0 0 1 216.96 384H832zm-24.96 512H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32h287.872l128.384 128H864a32 32 0 0 1 32 32v96h23.04a32 32 0 0 1 31.04 39.744l-112 448A32 32 0 0 1 807.04 896z"></path>
                </svg>
                <div style="text-align: center">项目</div>
              </div>
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
import { captureRejectionSymbol } from 'events';

export default {
  data () {
    return {
      info : {
        username : "wzszs",
        account : 3123139531,
        name : "王振阳",
        email : "3123139531@qq.com",
        birthday : "2003.06.08"
      },
      teams : [
        {
          id: 1,
          EName: 'A'
        }
      ],
      projects : [],
      t_projects : [],
      pp : [],
      numTeams : 1,
      numProjects : 0,
      numt_Projects : 0,
      option : 2,

      addTeamDialog : false,
      addProDialog : false,
      newPro_team : 0,
      newName : '',
      proMod : 1,
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init () {
      
      
    },
    showPersonalInfo () {
      this.option = 1;
    },
    showEnterprise () {
      this.option = 2;
    },
    showProject () {
      if(this.proMod===1){
        this.projects = []
        this.numProjects = 0
        for (let i=0; i<this.numTeams; i++){
          this.$axios.get('/projects/team/'+this.teams[i].id
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
          this.$axios.get('/projects/trash/'+this.teams[i].id
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
    Submit () {
      ElMessage({
        message: '修改成功',
        type: 'success',
      })
    },
    Revoke () {
    },
    toTeamView (id, account) {
      this.$router.push({
        name: 'team',
        params: {
          userAccount: account,
          teamId: id
        }
      })
    },
    toProjectView1 (id) {
      this.$router.push({
        name: 'project',
        params: {
          p_id: this.projects[id-1].pid,
          ac: this.info.account
        }
      })
    },
    toProjectView2 (id) {
      this.$router.push({
        name: 'project',
        params: {
          p_id: this.t_projects[id-1].pid,
          ac: this.info.account
        }
      })
    },
    addNewTeamBtn () {
      this.addTeamDialog = true;
    },
    addNewProjectBtn () {
      this.addProDialog = true;
    },
    addNewTeam () {
      this.teams.push({id: 0, EName: this.newName});
      this.numTeams ++;

      var data = {
        name : this.newName,
      };
      this.$axios.post("/team/{team_id}", JSON.stringify(data)).then(function (request) {
        console.log(request.data)
      });

      this.addTeamDialog = false;
      this.newName = '';
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
    changeProMod () {
      this.proMod ^= 1;
      if(this.proMod===1){
        this.projects = []
        this.numProjects = 0
        for (let i=0; i<this.numTeams; i++){
          this.$axios.get('/projects/team/'+this.teams[i].id
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
          this.$axios.get('/projects/trash/'+this.teams[i].id
          ).then(response =>{
            this.pp = response.data.data
            this.numt_Projects += response.data.data.length
            for(let j=0; j<response.data.data.length; j++){
           this.t_projects.push({pid: this.pp[j].pid, tid: this.pp[j].tid, status: this.pp[j].status, pname: this.pp[j].pname})
            }
          })
        }
      }
    },
    Quit () {
      ElMessage({
        message: '退出登录',
        type: 'success',
      })
      this.$router.push({
        name: 'Login',
        params: {

        }
      })
    }
  }
}
</script>

<style scoped>
    .background-img {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    margin-left: -50%;
  }

  .Homepage {
    position: relative;
    top: 10px;
    display: block;
    height: 700px;
    width: 90%;
    margin: 0 auto;
  }

  .Aside {
    margin-right: 3px;
    width: 100px;
    box-shadow: 5px 0 5px #888888;
    border: 1px solid black;
    background: white;
    /*background: rgba(255, 255, 255, 0.4);*/
    color: black;
  }

  .Aside .Portrait {
    margin-bottom: 20px;
  }

  .Aside .portrait {
    display: block;
    width: 80px;
    height: 80px;
    border-radius: 40px;
    margin: 20px auto 50px;
  }

  .Aside .Personal, .Enterprise, .Project {
    height: 55px;
    width: 60px;
    padding-top: 20px;
    margin: 15px auto auto auto;
    border-radius: 5px;
    transition: 0.5s;
  }

  .Aside .Personal:hover,
  .Aside .Enterprise:hover,
  .Aside .Project:hover {
    background: rgba(144, 144, 144, 0.4);
  }

  .Aside .Cancellation {
    margin-top: 220px;
  }

  .Main {
    height: 680px;
    background: white;
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
  }

  .PersonalInfo .portrait {
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
    margin-top: 10px;
    margin-right: 10px;
  }

  .divisionBox {
    display: inline-block;
    float: left;
    margin: 20px;
    width: 21%;
    height: 140px;
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
    width: 20%;
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
