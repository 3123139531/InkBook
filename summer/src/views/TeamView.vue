<template>
  <span class="background-img"></span>
  <div class="TeamInfo">
    <el-container>
      <el-header class="TeamHeader">
        <img src="../assets/bgimg2.png" class="TeamImg">
        <div class="TeamIntro">
          <div class="TeamName">
            <span>队名：</span>
            <span style="font-weight: 700">{{team.name}}</span>
          </div>
          <div class="TeamLeader">
            <span>队长：</span>
            <span v-for="i in team.numMembers" :key="i">
              <span v-if="team.members[i-1].identity==='创建者'" style="font-weight: 700">{{team.members[i-1].uname}}</span>
            </span>
          </div>
        </div>
        <div class="TeamManageNav">
          <div class="title">团队管理</div>
          <span class="teamMem">成员管理</span>
          <span class="teamPro" @click="toProManage">项目管理</span>
          <span class="teamDoc" @click="toDocCenter">文档中心</span>
        </div>
        <el-descriptions
            title="团队信息"
            direction="vertical"
            :column="2"
            border
            class="TeamDetail"
        >
          <el-descriptions-item label="团队ID">
            <span style="font-weight: 700">{{teamId}}</span>
          </el-descriptions-item>
          <el-descriptions-item label="团队人数" :span="2">
            <span style="font-weight: 700">{{team.numMembers}}</span>
          </el-descriptions-item>
        </el-descriptions>
<!--        <div class="TeamBtn">-->
<!--          <div>-->
<!--            <el-button type="primary" round v-if="userIdentity==='创建者'">解散队伍</el-button>-->
<!--            <el-button type="info" round v-else :plain="true" @click="JurisdictionError">解散队伍</el-button>-->
<!--          </div>-->
<!--          <el-button type="primary" round class="QuitBtn" @click="QuitTeam">退出队伍</el-button>-->
<!--        </div>-->
      </el-header>
      <el-main class="TeamMain">
        <div style="font:normal bold 20px/30px Georgia, serif; margin-bottom: 10px;">团队成员</div>
        <el-table
            :data="team.members"
            style="width: 100%"
            stripe
            class="MemDetail"
        >
          <el-table-column prop="uname" label="用户名" width="270"/>
          <el-table-column prop="uid" label="用户编号" width="270" />
          <el-table-column prop="unickname" label="姓名" width="270"/>
          <el-table-column prop="email" label="邮箱" width="270"/>
          <el-table-column prop="identity" label="身份" width="270"/>
        </el-table>
        <div v-if="userIdentity!=='队员'">
          <div style="font:normal bold 20px/30px Georgia, serif">成员管理</div>
          <div class="MemManageNav">
            <span class="ModifyMem" @click="AppointManagerBtn">
              任命管理员
            </span>
            <span class="ModifyMem" v-if="userIdentity==='创建者'" @click="DismissManagerBtn">
              撤销管理员
            </span>
            <span class="KickMem" @click="KickMemBtn">
              踢出成员
            </span>
            <span class="InviteMem" @click="InviteMemBtn">
              邀请成员
            </span>
          </div>
          <div class="MemManage">
            <div v-if="ManageMod===1">
              <el-dialog v-model="dialogVisible" title="点击用户名设置管理员">
                <span v-for="i in team.numMembers" :key="i">
                  <span v-if="team.members[i-1].identity==='队员'" class="Member" @click="AppointManager(i-1)">
                    {{team.members[i-1].uname}}
                  </span>
                </span>
              </el-dialog>
            </div>
            <div v-if="ManageMod===2">
              <el-dialog v-model="dialogVisible" title="点击用户名设置管理员">
                <span v-for="i in team.numMembers" :key="i">
                  <span v-if="team.members[i-1].identity==='管理员'" class="Member" @click="DismissManager(i-1)">
                    {{team.members[i-1].uname}}
                  </span>
                </span>
              </el-dialog>
            </div>
            <div v-if="ManageMod===3">
              <el-dialog v-model="dialogVisible" title="输入用户名邀请用户">
                <el-form>
                  <el-form-item label="用户名" :label-width="100">
                    <el-input v-model="userInvited" autocomplete="off" />
                  </el-form-item>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                      <el-button @click="dialogVisible = false">取消</el-button>
                      <el-button type="primary" @click="InviteMem()">确认</el-button>
                    </span>
                </template>
              </el-dialog>
            </div>
            <div v-if="ManageMod===4">
              <el-dialog v-model="dialogVisible" title="点击用户名踢出成员">
                <span v-for="i in team.numMembers" :key="i">
                  <span v-if="team.members[i-1].identity==='队员'" class="Member" @click="KickMem(i-1)">
                    {{team.members[i-1].uname}}
                  </span>
                </span>
              </el-dialog>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
  <el-button class="toHomepageBtn" @click="toHomeView" title="返回首页">
    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8="" style="width: 15px; height: 20px">
      <path fill="currentColor" d="M512 128 128 447.936V896h255.936V640H640v256h255.936V447.936z"></path>
    </svg>
  </el-button>
</template>

<script>
/* eslint-disable */
import { ElMessage } from 'element-plus'
import { Search } from '@element-plus/icons-vue'

export default {
  data () {
    return {
      acc: 0,
      userAccount: '',
      userIdentity: '',

      teamId: '',
      team : {
        name: "",
        trief: '',
        members: [],
        numMembers : 0,
      },
      userInvited: '',

      ManageMod : 0,
      dialogVisible : false
    }
  },
  mounted () {
    this.init()
  },
  methods : {
    init () {
      this.userAccount = this.$route.params.userAccount;
      this.teamId = this.$route.params.teamId;
      this.team.name = this.$route.params.teamName;
      this.getMembers()
    },
    getMembers () {
      var getUsersUrl = "/team/" + this.teamId + "/members";
      this.$axios.get(getUsersUrl, {
        params: {
          team_id : this.teamId
        }
      }).then(response=> {
        // console.log(response);
        this.team.members = response.data.data;
        this.team.numMembers = this.team.members.length;
        for (let i = 0; i < this.team.numMembers; i++) {
          if(this.team.members[i].identity === '1')
            this.team.members[i].identity = '队员'
          else if(this.team.members[i].identity === '2')
            this.team.members[i].identity = '管理员'
          if(this.team.members[i].identity === '3')
            this.team.members[i].identity = '创建者'

          if(this.team.members[i].uname === this.userAccount)
            this.userIdentity = this.team.members[i].identity
        }
      });
    },
    // JurisdictionError () {
    //   ElMessage.error('您没有此权限');
    // },
    // QuitTeam () {
    //   this.$router.push({
    //     name : 'home',
    //     params : {
    //       account : this.acc
    //     }
    //   })
    // },
    AppointManagerBtn () {
      this.ManageMod = 1;
      this.dialogVisible = true;
    },
    DismissManagerBtn () {
      this.ManageMod = 2;
      this.dialogVisible = true;
    },
    InviteMemBtn () {
      this.ManageMod = 3;
      this.dialogVisible = true;
    },
    KickMemBtn () {
      this.ManageMod = 4;
      this.dialogVisible = true;
    },
    AppointManager (i) {
      this.team.members[i].identity = '管理员';
      this.$axios.put("/team/" + this.teamId + '/members', {
        targetPosition: 2,
        twoUserParam: {
          uName1: this.userAccount,
          uName2: this.team.members[i].uname,
        },
        team_id: this.teamId
      }).then(function (response) {
        console.log(response);
      })
      ElMessage({
        message: '任命成功',
        type: 'success',
      })
    },
    DismissManager (i) {
      this.team.members[i].identity = '队员';
      this.$axios.put("/team/" + this.teamId + '/members', {
        targetPosition: 1,
        twoUserParam: {
          uName1: this.userAccount,
          uName2: this.team.members[i].uname,
        },
        team_id: this.teamId
      }).then(function (response) {
        console.log(response);
      })
      ElMessage({
        message: '撤销成功',
        type: 'success',
      })
    },
    InviteMem () {
      this.$axios.post("/team/" + this.teamId + '/members', {
        team_id : this.teamId,
        uName1: this.userAccount,
        uName2: this.userInvited
      }).then(response=> {
        console.log(response);
        this.userInvited = ''
        if(response.data.msg === '成功邀请'){
          ElMessage({
            message: response.data.msg,
            type: 'success',
          })
          this.getMembers()
        }
        else {
          ElMessage({
            message: response.data.msg,
            type: 'warning',
          })
        }
      });
    },
    KickMem (i) {
      this.$axios.delete("/team/" + this.teamId + '/members', {
        data: {
          uName1: this.userAccount,
          uName2: this.team.members[i].uname
        }
      }).then(response=> {
        console.log(response);
        ElMessage({
          message: '踢出成功',
          type: 'success',
        })
        this.getMembers()
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
    toProManage() {
      var leader = ''
      for(let i=0; i<this.team.numMembers; i++){
        if(this.team.members[i].identity === '创建者')
          leader = this.team.members[i].uname
      }
      this.$router.push({
        name: 'proManage',
        params : {
          userAccount: this.userAccount,
          teamId : this.teamId,
          teamName: this.team.name,
          leader: leader,
          numMembers: this.team.numMembers
        }
      })
    },
    toDocCenter() {
      var leader = ''
      for(let i=0; i<this.team.numMembers; i++){
        if(this.team.members[i].identity === '创建者')
          leader = this.team.members[i].uname
      }
      this.$router.push({
        name: 'docCenter',
        params : {
          userAccount: this.userAccount,
          teamId : this.teamId,
          teamName: this.team.name,
          leader: leader,
          numMembers: this.team.numMembers
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
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(144, 144, 144, 0.2);
  }

  .TeamInfo {
    position: relative;
    left: 0;
    top: 10px;
    width: 90%;
    margin: 0 auto;
    height: 700px;
  }

  .TeamHeader {
    height: 205px;
    border: 1px black solid;
    border-radius: 20px;
    background: white;
    /*background: rgba(200, 200, 200, 0.5);*/
  }

  .TeamImg {
    float: left;
    display: inline-block;
    height: 160px;
    width: 160px;
    border-radius: 80px;
    margin-top: 20px;
    margin-left: 10px;
  }

  .TeamIntro {
    display: inline-block;
    height: 100%;
    float: left;
    margin-left: 50px;
    font-size: 18px;
    text-align: left;
  }

  .TeamName {
    margin-top: 55px;
  }

  .TeamLeader {
    margin-top: 30px;
  }

  .TeamManageNav {
    display: inline-block;
    float: left;
    margin-left: 100px;
    height: 60%;
    margin-top: 3%;
    width: 300px;
    border: 1px black solid;
  }

  .TeamManageNav .title {
    font-family: '黑体', sans-serif;
    font-weight: bold;
    font-size: 20px;
    margin-top: 10px;
    padding-bottom: 10px;
    border-bottom: 1px black solid;
  }

  .TeamManageNav .teamPro,
  .TeamManageNav .teamDoc,
  .TeamManageNav .teamMem {
    display: inline-block;
    margin-top: 25px;
    width: 30%;
    height: 30px;
    line-height: 30px;
    font-family: '微软雅黑', sans-serif;
    font-size: 16px;
  }

  .TeamManageNav .teamMem {
    border-right: 1px black solid;
  }

  .TeamManageNav .teamDoc {
    border-left: 1px black solid;
  }

  .TeamManageNav .teamMem {
    background: rgba(144, 144, 144, 0.3);
  }

  .TeamManageNav .teamPro:hover,
  .TeamManageNav .teamDoc:hover{
    background: rgba(144, 144, 144, 0.3);
    cursor: pointer;
    color: #1890ff;
  }

  .TeamDetail {
    width: 300px;
    float: right;
    text-align: left;
    margin-right: 100px;
    margin-top: 40px;
  }

  .TeamBtn {
    display: inline-block;
    float: right;
    margin-top: 50px;
    margin-right: 100px;
  }

  .QuitBtn {
    margin-top: 20px;
  }

  .TeamMain {
    height: 100%;
    overflow: auto;
    border: 1px black solid;
    border-radius: 20px;
    margin-top: 10px;
    background: white;
    /*background: rgba(200, 200, 200, 0.5);*/
  }

  .MemDetail {
    display: inline-block;
    float: left;
    width: 1400px;
    padding-left: 15px;
    margin: 0 auto;
  }

  .TeamMain tr {
    height: 40px;
    line-height: 40px;
  }

  .example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
  }

  .MemManageNav {
    display: block;
    height: 40px;
    line-height: 40px;
    margin-top: 10px;
  }

  .ModifyMem {
    width: 100px;
    display: inline-block;
    border-radius: 10px;
    margin-right: 20px;
    background: rgba(144, 144, 144, 0.6);
  }

  .KickMem,
  .InviteMem {
    width: 100px;
    display: inline-block;
    border-radius: 10px;
    background: rgba(144, 144, 144, 0.6);
  }

  .KickMem {
    margin-right: 20px;
  }

  .ModifyMem:hover,
  .KickMem:hover,
  .InviteMem:hover {
    color: whitesmoke;
    cursor: pointer;
  }

  .MemManage {
    width: 40%;
    border-radius: 10px;
    margin: 20px auto;
    display: block;
    background: rgba(144, 144, 144, 0.6);
  }

  .Member {
    display: inline-block;
    margin-right: 10px;
    font-size: 18px;
    font-weight: 700;
  }

  .Member:hover {
    color: blue;
  }

  .toHomepageBtn {
    position: absolute;
    left: 10px;
    top: 20px;
    background: white;
  }
</style>
