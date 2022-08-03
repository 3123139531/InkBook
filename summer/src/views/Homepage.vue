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
        birthday : "2003.06.08"
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
      var data = {uid : this.account};
      var that = this;

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
      this.projects.push({id: 0, PName: this.newName});
      this.numProjects ++;

      var newPro = {
        name : this.newName,
      }

      this.addProDialog = false;
      this.newName = '';
    }
  }
}
</script>

<style scoped>
  @import "../style/Homepage.css";
</style>
