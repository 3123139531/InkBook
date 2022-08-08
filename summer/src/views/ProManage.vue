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
          <span class="teamPro" >项目管理</span>
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
            <span style="font-weight: 700">{{team.teamId}}</span>
          </el-descriptions-item>
          <el-descriptions-item label="团队人数" :span="2">
            <span style="font-weight: 700">{{team.numMembers}}</span>
          </el-descriptions-item>
        </el-descriptions>
      </el-header>
      <el-main class="TeamMain">
        <span class="mainTitle">团队项目</span>
        <span class="search">
          <el-input v-model="searchName" placeholder="输入项目名搜索项目"
          style="display:inline-block; float: left; width: 200px"/>
          <el-button class="searchBtn" v-if="!searchClicked" @click="searchPro">搜索</el-button>
          <el-button class="searchBtn" v-else @click="searchPro">取消</el-button>
        </span>
        <table class="proNav" v-if="numShow!==0">
          <tr>
            <th>项目编号</th>
            <th>
              <span title="点击以此排序" @click="sortByName">项目名称</span>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===3">
                <path fill="currentColor" d="m192 384 320 384 320-384z"></path>
              </svg>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===4">
                <path fill="currentColor" d="m192 384 320 384 320-384z"></path>
              </svg>
            </th>
            <th>
              <el-dropdown trigger="click">
                <span title="点击进行筛选">{{ this.statusShow }}</span>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item @click="showAll">全部</el-dropdown-item>
                    <el-dropdown-item @click="showDoing">进行中</el-dropdown-item>
                    <el-dropdown-item @click="showFinish" >已完成</el-dropdown-item>
                    <el-dropdown-item @click="showTrash">已回收</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
            </th>
            <th>
              <span title="点击以此排序" @click="sortByCreate">创建时间</span>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===1">
                <path fill="currentColor" d="m192 384 320 384 320-384z"></path>
              </svg>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===2">
                <path fill="currentColor" d="M512 320 192 704h639.936z"></path>
              </svg>
            </th>
            <th>
              <span title="点击以此排序" @click="sortByModify">修改时间</span>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===5">
                <path fill="currentColor" d="m192 384 320 384 320-384z"></path>
              </svg>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" data-v-78e17ca8=""
                   class="sortImg" v-if="sortType===6">
                <path fill="currentColor" d="M512 320 192 704h639.936z"></path>
              </svg>
            </th>
            <th></th>
          </tr>
          <tr v-for="i in numShow" key="i" class="proInfo">
            <td>{{ proShow[i-1].pid }}</td>
            <td>{{ proShow[i-1].pname }}</td>
            <td v-if="proShow[i-1].status==='doing'" style="color: #1890ff">进行中</td>
            <td v-else-if="proShow[i-1].status==='finish'" style="color: red">已完成</td>
            <td v-else style="color: grey">已回收</td>
            <td>{{ proShow[i-1].createTime }}</td>
            <td>{{ proShow[i-1].modifyTime }}</td>
            <td>
              <span class="proDetailLink" style="margin-right: 10px"
                    @click="toProjectView(i-1)">项目详情</span>
              <span class="proDetailLink" v-if="proShow[i-1].status==='doing'"
                    @click="finishPro(i-1)">结束项目</span>
            </td>
          </tr>

        </table>
        <el-empty :image-size="100" v-else/>
        <el-button type="primary" class="addProBtn" @click="addNewProBtn">新建项目</el-button>
        <el-dialog v-model="addProDialog">
          <el-form>
            <el-form-item label="项目名" :label-width="100">
              <el-input v-model="newProName" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="addProDialog = false">取消</el-button>
              <el-button type="primary" @click="addNewPro">确认</el-button>
            </span>
          </template>
        </el-dialog>
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

import {ElMessage} from "element-plus";

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
      projects: [],
      numPro: 0,

      searchName: '',
      searchClicked: false,
      proShow: [],
      numShow: 0,
      statusShow: '项目状态',
      sortType : 0,

      addProDialog: false,
      newProName: ''
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
      this.getProjects()
    },
    getProjects() {
      this.$axios.get('/projects/doing/'+this.team.teamId).then(response =>{
        // console.log(response)
        for(let i=0; i<response.data.data.length; i++)
          this.projects.push(response.data.data[i])

        this.$axios.get('/projects/trash/'+this.team.teamId).then(response =>{
          // console.log(response)
          for(let i=0; i<response.data.data.length; i++)
            this.projects.push(response.data.data[i])

          this.$axios.get('/projects/finish/'+this.team.teamId).then(response =>{
            // console.log(response)
            for(let i=0; i<response.data.data.length; i++)
              this.projects.push(response.data.data[i])
            this.numPro = this.projects.length

            this.proShow = this.projects
            this.numShow = this.numPro
            this.sortByCreate()
          })
        })
      })
    },
    searchPro() {
      // console.log(this.searchName)
      this.searchClicked = !this.searchClicked
      if(this.searchName===''){
        this.projects = []
        this.sortType = 0
        this.getProjects()
      }
      else {
        this.numShow = 0
        for(let i=0; i<this.numPro; i++){
          let str1 = this.projects[i].pname
          let str2 = this.searchName
          if((str1.indexOf(str2) >= 0))
            this.proShow[this.numShow++] = this.projects[i]
        }
        this.searchName = ''
        console.log(this.projects)
      }
    },
    sortByCreate(){
      if(this.sortType>2 || this.sortType<1){
        this.sortType = 1
        for (let i=0; i<this.numShow; i++){
          for (let j=0; j<this.numShow-1-i; j++){
            if(this.proShow[j].createTime < this.proShow[j+1].createTime){
              let tmp = this.proShow[j]
              this.proShow[j] = this.proShow[j+1]
              this.proShow[j+1] = tmp
            }
          }
        }
      }
      else{
        this.sortType = (this.sortType===1)?2:1
        this.proShow.reverse()
      }
    },
    sortByName(){
      if(this.sortType<3 && this.sortType>4){
        this.sortType = 3
        for (let i=0; i<this.numShow; i++){
          for (let j=0; j<this.numShow-1-i; j++){
            if(this.proShow[j].pname < this.proShow[j+1].pname){
              let tmp = this.proShow[j]
              this.proShow[j] = this.proShow[j+1]
              this.proShow[j+1] = tmp
            }
          }
        }
      }
      else{
        this.sortType = (this.sortType===3)?4:3
        this.proShow.reverse()
      }
    },
    sortByModify(){
      if(this.sortType<5){
        this.sortType = 5
        for (let i=0; i<this.numShow; i++){
          for (let j=0; j<this.numShow-1-i; j++){
            if(this.proShow[j].modifyTime < this.proShow[j+1].modifyTime){
              let tmp = this.proShow[j]
              this.proShow[j] = this.proShow[j+1]
              this.proShow[j+1] = tmp
            }
          }
        }
      }
      else {
        this.sortType = (this.sortType===5)?6:5
        this.proShow.reverse()
      }
    },
    showAll() {
      this.statusShow = '项目状态'
      this.projects = []
      this.sortType = 0
      this.getProjects()
      // console.log(this.projects)
    },
    showDoing() {
      this.statusShow = '进行中'
      this.numShow = 0
      for(let i=0; i<this.numPro; i++){
        if(this.projects[i].status === 'doing')
          this.proShow[this.numShow++] = this.projects[i]
      }
      // console.log(this.projects)
    },
    showFinish() {
      this.statusShow = '已完成'
      this.numShow = 0
      for(let i=0; i<this.numPro; i++){
        if(this.projects[i].status === 'finish')
          this.proShow[this.numShow++] = this.projects[i]
      }
      // console.log(this.projects)
    },
    showTrash() {
      this.statusShow = '已回收'
      this.numShow = 0
      for(let i=0; i<this.numPro; i++){
        if(this.projects[i].status === 'trash')
          this.proShow[this.numShow++] = this.projects[i]
      }
      // console.log(this.projects)
    },
    finishPro(i) {
      this.$axios.put('/projects/finish/' + this.projects[i].pid, {
        pid: this.projects[i].pid
      }).then(response=>{
        ElMessage({
          message: '项目已完成',
          type: 'success'
        })
        this.projects[i].status = 'finish'
      })
    },
    addNewProBtn() {
      this.addProDialog = true
    },
    addNewPro() {
      this.addProDialog = false
      this.$axios.post('/projects',{
        pname: this.newProName,
        tid: this.team.teamId,
      }).then(response =>{
        console.log(response)
        this.getProjects()
        this.sortType = 1
        this.newProName = ''
      })
      ElMessage({
        message: '创建项目成功',
        type: 'success',
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
    toDocCenter() {
      this.$router.push({
        name: 'docCenter',
        query : {
          userAccount: this.userAccount,
          teamId : this.team.teamId,
          teamName: this.team.name,
          leader: this.team.leader,
          numMembers: this.team.numMembers,
        }
      })
    },
    toProjectView(id) {
      this.$router.push({
        name: 'project',
        params: {
          p_id: this.projects[id].pid,
          ac: this.userAccount,
          teamName: this.team.name
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

.TeamManageNav .teamPro {
  background: rgba(144, 144, 144, 0.3);
}

.TeamManageNav .teamDoc:hover,
.TeamManageNav .teamMem:hover {
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
  margin-bottom: 10px;
  background: white;
  /*background: rgba(200, 200, 200, 0.5);*/
}

.mainTitle {
  display: inline-block;
  font:normal bold 20px/30px Georgia, serif;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 22%;
}

.search {
  display: inline-block;
  float: right;
  margin-top: 10px;
  margin-right: 2.5%;
  width: 260px;
}

.searchBtn {
  display: inline-block;
  float: right;
  height: 32px;
  width: 60px;
}

.proNav {
  display: inline-block;
  border: 1px black solid;
  width: 95%;
  margin-top: 10px;
}

.proNav tr {
  display: block;
  height: 40px;
}

.proNav th {
  display: inline-block;
  width: 18%;
  padding: 0;
  line-height: 40px;
  font-size: 17px;
}

.proNav th span {
  display: inline-block;
  width: 18%;
  padding: 0;
  line-height: 40px;
  font-size: 17px;
  color: black;
}

.proNav th:first-child {
  width: 10%;
}

.proNav th span {
  display: inline-block;
  height: 40px;
  width: 80px;
  cursor: pointer;
}

.proNav th span:hover {
  background: rgba(144, 144, 144, 0.2);
}

.proNav td {
  display: inline-block;
  width: 18%;
  padding: 0;
  line-height: 40px;
  font-size: 15px;
  border-top: 1px black solid;
}

.proNav td:first-child{
  width: 10%;
}

.proNav tr:nth-child(even) {
  background: rgba(144, 144, 144, 0.1);
}

.proNav tr:last-child {
  border-bottom: 0;
}

.sortImg {
  height: 15px;
  width: 15px;
}

.proDetailLink {
  cursor: pointer;
  color: #1890ff;
}

.proDetailLink:hover {
  text-decoration: underline;
}

.addProBtn {
  margin-top: 20px;
  margin-right: 2.5%;
  float: right;
}

.toHomepageBtn {
  position: fixed;
  left: 10px;
  top: 20px;
  background: white;
}
</style>
