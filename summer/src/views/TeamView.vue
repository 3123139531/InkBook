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
              <span v-if="team.members[i-1].identity==='创建者'" style="font-weight: 700">{{team.members[i-1].username}}</span>
            </span>
          </div>
        </div>
        <el-descriptions
            title="团队信息"
            direction="vertical"
            :column="4"
            border
            class="TeamDetail"
        >
          <el-descriptions-item label="团队ID">
            <span style="font-weight: 700">{{teamId}}</span>
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">
            <span style="font-weight: 700">{{team.setTime}}</span>
          </el-descriptions-item>
          <el-descriptions-item label="团队人数" :span="2">
            <span style="font-weight: 700">{{team.numMembers}}</span>
          </el-descriptions-item>
<!--          <el-descriptions-item label="团队简介">-->
<!--            <span style="font-weight: 700">{{team.remark}}</span>-->
<!--          </el-descriptions-item>-->
        </el-descriptions>
        <div class="TeamBtn">
          <div>
            <el-button type="primary" round v-if="userIdentity==='创建者'">解散队伍</el-button>
            <el-button type="info" round v-else :plain="true" @click="JurisdictionError">解散队伍</el-button>
          </div>
          <el-button type="primary" round class="QuitBtn" @click="QuitTeam">退出队伍</el-button>
        </div>
<!--        <ul class="TeamDetail">-->
<!--          <li>-->
<!--            <span>团队ID：</span>-->
<!--            <span style="font-weight: 700">{{teamId}}</span>-->
<!--          </li>-->
<!--          <li>-->
<!--            <span>创建时间：</span>-->
<!--            <span style="font-weight: 700">{{team.setTime}}</span>-->
<!--          </li>-->
<!--          <li>-->
<!--            <span>团队人数：</span>-->
<!--            <span style="font-weight: 700">{{team.numMembers}}</span>-->
<!--          </li>-->
<!--          <li>-->
<!--            <span>团队简介：</span>-->
<!--            <div style="font-weight: 700">{{team.remark}}</div>-->
<!--          </li>-->
<!--        </ul>-->
      </el-header>
      <el-main class="TeamMain">
        <div style="font:normal bold 20px/30px Georgia, serif; margin-bottom: 10px;">团队成员</div>
        <el-table
            :data="team.members"
            style="width: 100%"
            stripe
            class="MemDetail"
        >
          <el-table-column prop="username" label="用户名" width="265"/>
          <el-table-column prop="account" label="账号" width="265" />
          <el-table-column prop="name" label="姓名" width="265"/>
          <el-table-column prop="email" label="邮箱" width="265"/>
          <el-table-column prop="identity" label="身份" width="265"/>
        </el-table>
        <div v-if="userIdentity!=='队员'">
          <div style="font:normal bold 20px/30px Georgia, serif;">成员管理</div>
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
                    {{team.members[i-1].username}}
                  </span>
                </span>
              </el-dialog>
            </div>
            <div v-if="ManageMod===2">
              <span v-for="i in team.numMembers" :key="i">
                <span v-if="team.members[i-1].identity==='管理员'" class="Member" @click="DismissManager(i-1)">
                  {{team.members[i-1].username}}
                </span>
              </span>
            </div>
            <div v-if="ManageMod===3">
              <el-dialog v-model="dialogVisible" title="可邀请用户">
                <span v-for="user in Users" class="Member" @click="InviteMem(user.account)" :key="user">
                  {{user.username}}({{user.account}})
                </span>
              </el-dialog>
            </div>
            <div v-if="ManageMod===4">
              <el-dialog v-model="dialogVisible" title="点击用户名踢出成员">
                <span v-for="i in team.numMembers" :key="i">
                  <span v-if="team.members[i-1].identity==='队员'" class="Member" @click="KickMem(i-1)">
                    {{team.members[i-1].username}}
                  </span>
                </span>
              </el-dialog>
            </div>
          </div>
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
import { ElMessage } from 'element-plus'
import { Search } from '@element-plus/icons-vue'

export default {
  data () {
    return {
      acc: 0,
      userAccount: '2',
      userIdentity: '管理员',

      teamId: '1',
      team : {
        name: "六轮车",
        setTime: "2022-08-01",
        members: [
          {
            username : 'zzh',
            account : '1',
            name : '1',
            email : '1',
            birthday: '1',
            identity: '创建者'
          },
          {
            username : 'abb',
            account : '2',
            name : '2',
            email : '2',
            birthday: '2',
            identity: '管理员'
          },
          {
            username : 'cbb',
            account : '3',
            name : '3',
            email : '3',
            birthday: '3',
            identity: '队员'
          },
        ],
        numMembers : 3,
      },

      Users : [
        {
          username : 'cbb',
          account : '3',
          name : '3',
          email : '3',
          birthday: '3',
          identity: ''
        }
      ],

      ManageMod : 0,
      dialogVisible : false
    }
  },
  mounted () {
    this.init()
    console.log(this.acc)
  },
  methods : {
    init () {
      this.acc = this.$route.params.ac
      this.userAccount = this.$route.params.userAccount;
      this.teamId = this.$route.params.teamId;
      var getUsersUrl = "/team/" + this.teamId + "/members";
      this.$axios.get(getUsersUrl, {
        params: {
          team_id : this.teamId
        }
      }).then(response=> {
        console.log(response);
        this.team.members = response.data.data;
        this.team.numMembers = this.team.members.length;
        for (let i = 0; i < this.team.numMembers; i++) {
          this.team.members[i].identity = '队员'
        }
        // for (let i = 0; i < this.team.numMembers; i++) {
        //   if(this.team.members[i].identity === 1)
        //     this.team.members[i].identity = '队员'
        //   else if(this.team.members[i].identity === 2)
        //     this.team.members[i].identity = '管理员'
        //   if(this.team.members[i].identity === 3)
        //     this.team.members[i].identity = '创建者'
        // }
      });
    },
    JurisdictionError () {
      ElMessage.error('您没有此权限');
    },
    QuitTeam () {
      this.$router.push({
        name : 'home',
        params : {
          account : this.acc
        }
      })
    },
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



    },
    DismissManager () {
      this.team.members[i].identity = '队员';




    },
    InviteMem (account) {
      // ElMessage({
      //   message: '已发送邀请',
      //   type: 'success',
      // });
      // this.$axios.get("/team/{team_id}", {
      //   params: {
      //     team_id : this.teamId,
      //     username : username
      //   }
      // }).then(function (response) {
      //   console.log(response);
      // });
    },
    KickMem (i) {
      // this.$axios.delete("/team/{team_id}", {
      //   params: {
      //     team_id : this.teamId,
      //     username: this.team.members[i].username
      //   }
      // }).then(function (response) {
      //   console.log(response);
      // });
    },
    toHomeView () {
      this.$router.push({
        name: 'home',
        params : {
          ac : this.acc
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

  .TeamDetail {
    width: 500px;
    float: left;
    text-align: left;
    margin-left: 200px;
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
    height: 480px;
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
    width: 100%;
    padding-left: 15px;
    margin-bottom: 20px;
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
    margin-left: 20px;
    margin-right: 20px;
  }

  .ModifyMem:hover,
  .KickMem:hover,
  .InviteMem:hover {
    color: whitesmoke;
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
    margin: 20px auto;
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
