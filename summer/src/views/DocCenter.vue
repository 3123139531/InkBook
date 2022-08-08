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
        <div class="docTree">
          <div style="text-align: center; border-bottom: 1px black solid">进行中项目</div>
          <el-tree :data="dataDoing"
                   :props="defaultProps"
                   :highlight-current="true"
          />
        </div>
        <div class="docTree">
          <div style="text-align: center; border-bottom: 1px black solid">已完成项目</div>
          <el-tree :data="dataFinish"
                   :props="defaultProps"
                   :highlight-current="true"
          />
        </div>
        <div class="docTree">
          <div style="text-align: center; border-bottom: 1px black solid">已回收项目</div>
          <el-tree :data="dataTrash"
                   :props="defaultProps"
                   :highlight-current="true"
          />
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

      dataDoing: [],
      dataFinish: [],
      dataTrash: [],
      defaultProps : {
        label: 'name',
        children: 'docs'
      }
    }
  },
  mounted () {
    this.init()
  },
  methods : {
    init () {
      this.userAccount = this.$route.query.userAccount;
      this.team.leader = this.$route.query.leader;
      this.team.teamId = this.$route.query.teamId;
      this.team.name = this.$route.query.teamName;
      this.team.numMembers = this.$route.query.numMembers;
      this.getTree()
      console.log(this.data)
    },
    getTree() {
      this.getDoingTree()
      this.getFinishTree()
      this.getTrashTree()
    },
    getDoingTree() {
      this.$axios.get('/projects/doing/' + this.team.teamId, {
        params: {
          tid: this.team.teamId
        }
      }).then(response=>{
        let projects = response.data.data
        let numPro = projects.length
        for(let i=0; i<numPro; i++){
          this.$axios.get('/documents/project/'+projects[i].pid
          ).then(res=>{
            // console.log(res)
            let docoments = res.data.data.documents
            let numDoc = docoments.length

            let docTreeNode = []
            for(let j=0; j<numDoc; j++){
              docTreeNode.push({
                name: docoments[j].dname
              })
            }
            this.dataDoing.push({
              name: projects[i].pname,
              docs: docTreeNode
            })
          })
        }
      })
    },
    getFinishTree() {
      this.$axios.get('/projects/finish/' + this.team.teamId, {
        params: {
          tid: this.team.teamId
        }
      }).then(response=>{
        let projects = response.data.data
        let numPro = projects.length
        for(let i=0; i<numPro; i++){
          this.$axios.get('/documents/project/'+projects[i].pid
          ).then(res=>{
            // console.log(res)
            let docoments = res.data.data.documents
            let numDoc = docoments.length

            let docTreeNode = []
            for(let j=0; j<numDoc; j++){
              docTreeNode.push({
                name: docoments[j].dname
              })
            }
            this.dataFinish.push({
              name: projects[i].pname,
              docs: docTreeNode
            })
          })
        }
      })
    },
    getTrashTree() {
      this.$axios.get('/projects/trash/' + this.team.teamId, {
        params: {
          tid: this.team.teamId
        }
      }).then(response=>{
        let projects = response.data.data
        let numPro = projects.length
        for(let i=0; i<numPro; i++){
          this.$axios.get('/documents/project/'+projects[i].pid
          ).then(res=>{
            // console.log(res)
            let docoments = res.data.data.documents
            let numDoc = docoments.length

            let docTreeNode = []
            for(let j=0; j<numDoc; j++){
              docTreeNode.push({
                name: docoments[j].dname
              })
            }
            this.dataTrash.push({
              name: projects[i].pname,
              docs: docTreeNode
            })
          })
        }
      })
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
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(144, 144, 144, 0.2) scroll;
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
  height: 680px;
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

.docTree {
  display: inline-block;
  float: left;
  width: 30%;
  font-size: 16px !important;
  font-family: '微软雅黑',sans-serif;
  border: 1px black solid;
  border-radius: 5px;
  padding: 10px;
  margin-left: 1%;
  background: rgba(144, 144, 144, 0.1);
}

.prefix {
  color: var(--el-color-primary);
  margin-right: 10px;
  height: 40px;
  line-height: 40px;
}
.prefix.is-leaf {
  color: var(--el-color-success);
}

.toHomepageBtn {
  position: fixed;
  left: 10px;
  top: 20px;
  background: white;
}
</style>
