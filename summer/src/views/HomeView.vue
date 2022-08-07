<template>
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
          <div class="curView" v-if="option===1"></div>
        </div>
        <div class="Enterprise" @click="showEnterprise">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M192 413.952V896h640V413.952L512 147.328 192 413.952zM139.52 374.4l352-293.312a32 32 0 0 1 40.96 0l352 293.312A32 32 0 0 1 896 398.976V928a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V398.976a32 32 0 0 1 11.52-24.576z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">团队</div>
          <div class="curView" v-if="option===2"></div>
        </div>
        <div class="Project" @click="showProject">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 22px; width: 22px">
            <path fill="currentColor" d="M128 192v640h768V320H485.76L357.504 192H128zm-32-64h287.872l128.384 128H928a32 32 0 0 1 32 32v576a32 32 0 0 1-32 32H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32z"></path>
          </svg>
          <div style="font-size: 14px; font-weight: bold">项目</div>
          <div class="curView" v-if="option===3"></div>
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
<!--              <div class="newBox" @click="addNewProjectBtn">-->
<!--                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" class="plusIcon">-->
<!--                  <path fill="currentColor" d="M352 480h320a32 32 0 1 1 0 64H352a32 32 0 0 1 0-64z"></path>-->
<!--                  <path fill="currentColor" d="M480 672V352a32 32 0 1 1 64 0v320a32 32 0 0 1-64 0z"></path>-->
<!--                  <path fill="currentColor" d="M512 896a384 384 0 1 0 0-768 384 384 0 0 0 0 768zm0 64a448 448 0 1 1 0-896 448 448 0 0 1 0 896z"></path>-->
<!--                </svg>-->
<!--                <div class="plusChar">新建项目</div>-->
<!--                <el-dialog v-model="addProDialog">-->
<!--                  <el-form>-->
<!--                    <el-form-item label="团队编号" :label-width="100">-->
<!--                      <el-input v-model="newPro_team" autocomplete="off" />-->
<!--                    </el-form-item>-->
<!--                    <el-form-item label="项目名" :label-width="100">-->
<!--                      <el-input v-model="newName" autocomplete="off" />-->
<!--                    </el-form-item>-->
<!--                  </el-form>-->
<!--                  <template #footer>-->
<!--                    <span class="dialog-footer">-->
<!--                      <el-button @click="addProDialog = false">取消</el-button>-->
<!--                      <el-button type="primary" @click="addNewPro">确认</el-button>-->
<!--                    </span>-->
<!--                  </template>-->
<!--                </el-dialog>-->
<!--              </div>-->
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
import { thisTypeAnnotation } from '@babel/types';
import { ElMessage } from 'element-plus'
import { captureRejectionSymbol } from 'events';

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
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init () {
      // console.log(this.$route)
      this.account = this.$route.params.ac
      this.$axios.get('/team/' + this.account
      ).then(response=> {
        this.teams = response.data.data;
        this.numTeams = this.teams.length;
      });
      this.$axios.get('/user/info/' + this.account
      ).then(response=> {
        this.info = response.data.data
      });
    },
    showPersonalInfo () {
      // console.log(this.account)
      this.$axios.get('/user/info/' + this.account
      ).then(response=> {
        this.info = response.data.data
        this.newUname = this.info.uname
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
    Submit () {
      this.$axios.post('/user/info/' + this.info.uname, {
        email: this.info.email,
        password: this.info.password,
        profilePic: "",
        uid: 0,
        uname: this.newUname,
        unickname: this.info.unickname
      }).then(response=> {
        console.log(response)
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
        params: {
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
        this.addProDialog = false;
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
    changeProMod () {
      this.proMod ^= 1;
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
    },
    Quit () {
      ElMessage({
        message: '退出登录',
        type: 'success',
      })
      this.$router.push({
        name: 'login',
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
    background: rgba(144, 144, 144, 0.2);
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
  .Personal, .Enterprise, .Project {
    position: relative;
    left: 0;
    top: 0;
    height: 55px;
    width: 60px;
    padding-top: 10px;
    margin: 15px auto;
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
    height: 680px;
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
