<template>
  <img src="../assets/background.png" class="background-img">
  <div class="Homepage">
    <el-container>
      <el-aside class="Aside">
        <div class="Portrait">
          <img src="../assets/头像.jpg" alt="头像" class="portrait">
        </div>
        <div class="Personal" @click="showPersonalInfo">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 20px; width: 20px">
            <path fill="currentColor" d="M628.736 528.896A416 416 0 0 1 928 928H96a415.872 415.872 0 0 1 299.264-399.104L512 704l116.736-175.104zM720 304a208 208 0 1 1-416 0 208 208 0 0 1 416 0z"></path>
          </svg>
          <div style="font-size: 10px">信息</div>
        </div>
        <div class="Enterprise" @click="showEnterprise">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 20px; width: 20px">
            <path fill="currentColor" d="M192 413.952V896h640V413.952L512 147.328 192 413.952zM139.52 374.4l352-293.312a32 32 0 0 1 40.96 0l352 293.312A32 32 0 0 1 896 398.976V928a32 32 0 0 1-32 32H160a32 32 0 0 1-32-32V398.976a32 32 0 0 1 11.52-24.576z"></path>
          </svg>
          <div style="font-size: 10px">团队</div>
        </div>
        <div class="Project" @click="showProject">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="height: 20px; width: 20px">
            <path fill="currentColor" d="M128 192v640h768V320H485.76L357.504 192H128zm-32-64h287.872l128.384 128H928a32 32 0 0 1 32 32v576a32 32 0 0 1-32 32H96a32 32 0 0 1-32-32V160a32 32 0 0 1 32-32z"></path>
          </svg>
          <div style="font-size: 10px">项目</div>
        </div>
        <el-button type="primary" round class="Cancellation">注销</el-button>
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
            <el-form :model="info" label-width="120px" class="Format">
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
            <div v-for="i in numTeams">
              <div class="divisionBox" @click="toTeamInfo(i, info.account)">
                <span class="innerChar">{{teams[i-1].EName}}</span>
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
            <el-header class="Header">参与项目</el-header>
            <div v-for="i in numProjects">
              <div class="divisionBox" @click="toProjectInfo(i, account)">
                <span class="innerChar">{{projects[i-1].PName}}</span>
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
                  <el-form-item label="请输入新项目名" :label-width="'140'">
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
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'

export default {
  data () {
    return {
      account: '',
      info : {
        username : "wzszs",
        account : "3123139531",
        name : "王振阳",
        email : "3123139531@qq.com",
        birthday : "2003-06-08"
      },
      teams : [
        {
          id: 1,
          EName: 'A'
        }
      ],
      projects : [
        {
          id: 1,
          PName: 'A'
        },
        {
          id: 2,
          PName: 'B'
        }
      ],

      numTeams : 1,
      numProjects : 2,
      option : 2,

      addTeamDialog : false,
      addProDialog : false,
      newName : ''
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
    toTeamInfo (id, account) {
      this.$router.push({
        name: 'TeamInfo',
        params: {
          userAccount: account,
          teamId: id
        }
      })
    },
    toProjectInfo (id) {
      this.$router.push({
        name: 'ProjectInfo',
        params: {
          id: id
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

      this.addTeamDialog = false;
      this.newName = '';
    },
    addNewPro() {
      this.projects.push({id: 0, PName: this.newName});
      this.numProjects ++;

      this.addProDialog = false;
      this.newName = '';
    }
  }
}
</script>

<style scoped>
  /*@import "../style/Homepage.css";*/

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
    box-shadow: 5px 0px 5px #888888;
    border: 1px solid black;
    background: rgba(255, 255, 255, 0.4);
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
    height: 50px;
    width: 60px;
    padding-top: 20px;
    margin: 15px auto auto auto;
    border-radius: 5px;
    transition: 0.5s;
  }

  .Aside .Personal:hover,
  .Aside .Enterprise:hover,
  .Aside .Project:hover {
    background: rgba(144, 144, 144, 0.5);
    color: ghostwhite;
  }

  .Aside .Cancellation {
    margin-top: 220px;
  }

  .Main {
    height: 680px;
    background: rgba(255, 255, 255, 0.5);
  }

  .Main .Header {
    height: 50px;
    line-height: 50px;
    font-size: 26px;
    font-weight: 700;
    background: rgba(255, 255, 255, 0.4);
    border-bottom: 2px black dotted;
  }

  .PersonalInfo,
  .PersonalEnterprises,
  .PersonalProjects {
    width: 99%;
    height: 99%;
    border: 2px black solid;
  }

  .PersonalInfo .portrait {
    display: block;
    width: 160px;
    height: 160px;
    border-radius: 80px;
    margin: 0 auto;
  }

  .PersonalInfo .Format {
    display: inline-block;
    width: 50%;
    margin-top: 100px;
    margin-right: 50px;
    border-radius: 5px;
    padding-top: 10px;
    padding-right: 20px;
    color: black;
  }

  .PersonalInfo .PersonalPortrait {
    display: inline-block;
    width: 30%;
    margin: 0 auto;
  }

  .changePortraitBtn {
    display: block;
    margin: 40px auto;
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
  }

  .innerChar {
    display: block;
    font-size: 20px;
    font-weight: 700;
    line-height: 140px;
    text-align: center;
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
    width: 64px;
    font-size: 16px;
    font-weight: 700;
    margin: 0 auto;
  }

  .divisionBox:hover,
  .newBox:hover {
    box-shadow: 5px 5px 20px;
  }
</style>
