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
            <span style="font-weight: 700">{{team.leader}}</span>
          </div>
        </div>
        <div class="TeamManageNav">
          <div class="title">团队管理</div>
          <span class="teamMem" @click="toTeamView">成员管理</span>
          <span class="teamPro" @click="toProManage">项目管理</span>
          <span class="teamDoc" >文档中心</span>
        </div>
        <el-descriptions
            title="团队信息"
            direction="vertical"
            :column="2"
            border
            class="TeamDetail"
        >
          <el-descriptions-item label="团队ID">
            <span style="font-weight: 700">{{team.teamId}}</span>
          </el-descriptions-item>
          <el-descriptions-item label="团队人数" :span="2">
            <span style="font-weight: 700">{{team.numMembers}}</span>
          </el-descriptions-item>
        </el-descriptions>
      </el-header>
      <el-main class="TeamMain">
        <div style="font:normal bold 20px/30px Georgia, serif; margin-bottom: 10px;">文档中心</div>
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

export default {
  data () {
    return {
      userAccount: '',

      team: {
        teamId : '',
        name: '',
        leader: '',
        numMembers: 0
      },
    }
  },
  mounted () {
    this.init()
  },
  methods : {
    init () {
      this.userAccount = this.$route.params.userAccount;
      this.team.leader = this.$route.params.leader;
      this.team.teamId = this.$route.params.teamId;
      this.team.name = this.$route.params.teamName;
      this.team.numMembers = this.$route.params.numMembers;
    },
    toHomeView () {
      this.$router.push({
        name: 'home',
        params : {
          ac : this.userAccount
        }
      })
    },
    toTeamView() {
      this.$router.push({
        name: 'team',
        params : {
          userAccount : this.userAccount,
          teamId : this.team.teamId,
          teamName : this.team.name
        }
      })
    },
    toProManage() {
      this.$router.push({
        name: 'proManage',
        params : {
          userAccount: this.userAccount,
          teamId : this.team.teamId,
          teamName: this.team.name,
          leader: this.team.leader,
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
}

.TeamManageNav .teamMem {
  border-right: 1px black solid;
}

.TeamManageNav .teamDoc {
  border-left: 1px black solid;
}

.TeamManageNav .teamDoc {
  background: rgba(144, 144, 144, 0.3);
}

.TeamManageNav .teamPro:hover,
.TeamManageNav .teamMem:hover{
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

.TeamMain {
  height: 100%;
  overflow: auto;
  border: 1px black solid;
  border-radius: 20px;
  margin-top: 10px;
  background: white;
  /*background: rgba(200, 200, 200, 0.5);*/
}

.TeamMain tr {
  height: 40px;
  line-height: 40px;
}

.toHomepageBtn {
  position: absolute;
  left: 10px;
  top: 20px;
  background: white;
}
</style>
