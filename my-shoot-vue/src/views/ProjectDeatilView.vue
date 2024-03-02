<template>
  <div class="ProjectDeatilView page" ref="exportPdf">
    <h1>{{ dname }}</h1>
    <div class="deatilinfotext">
      <el-row style="margin-top: 20px;">
        <el-col :span="24" style="margin-top: 20px;">
          <div class="grid-content bg-purple">项目名称：{{ name }}</div>
        </el-col>
        <el-col :span="24" style="margin-top: 30px;">
          <div class="grid-content bg-purple-light">创建时间：{{ setdate }}</div>
        </el-col>
        <el-col :span="24" style="margin-top: 10px;">
          <div class="grid-content bg-purple-light">项目负责人：{{ manager }}</div>
        </el-col>
        <el-col :span="24" style="margin-top: 10px;">
          <div class="grid-content bg-purple-light">项目人员：{{ staff_list }}</div>
        </el-col>
        <el-col :span="24" style="margin-top: 10px;">
          <div class="grid-content bg-purple">项目内容：{{ contont }}</div>
        </el-col>

      </el-row>
    </div>
    <div class="deatilright">
      <el-button type="primary" plain @click="outp">导出存档</el-button>
      <el-button type="success" plain @click="editp">编辑</el-button>
      <el-button type="danger" plain @click="delp" v-show="roleflag">项目移除</el-button>
      <!-- <el-button type="success" plain @click="saveindesk">导出保存</el-button> -->
    </div>
  </div>
</template>
  
<script>
// @ is an alias to /src




export default {
  name: 'projectDeatilView',
  data() {
    return {
      dname: '项目详情',
      num: 1,
      id: 0,
      name: "",
      contont: "",
      state: "",
      token: "",
      user: "",
      setdate: "",
      staff_list: "",
      manager: "",
      dialogVisible: false,
      roleflag: false,
      projectlist: [
        {
          id: 0,
          name: "",
          contont: "",
          state: "",
        }
      ],
      outdata: []
    }
  },
  components: {

  },
  methods: {
    showinfo() {
      // console.log(this.$route.params.flower)
    },
    delp() {
      this.$confirm('确认您的的选择，请确认移除此项目', '确认移除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/project/del",
          JSON.stringify({
            "id": this.id
          }), {
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json'
          }
        }
        ).then(res => {
          // console.log(res.data)
          this.$message({
            type: 'success',
            message: '移除成功，已删除'
          })
        })
          ;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    outp() {
      // console.log(this.$vueToPdf)
      // this.$vueToPdf(this.$refs.exportPdf, "我的文件");
      const date = new Date();
      this.$PDFSave(this.$refs.exportPdf, this.name + date.toLocaleDateString());
    },
    editp() {
      
    }

  },
  mounted() {
  },
  updated() {

  },
  computed: {
    // getTotal() {
    //   return this.price * this.num
    // }
  },
  created() {
    // console.log(this)
    // console.log('created')
    // console.log(`the component is now mounted.`)
    // if(typeof(this.$route.params.flower) == "undefined"){
    //   console.log('data is void')
    // }
    this.token = localStorage.getItem("token")
    this.projectlist[0] = this.$route.params.project
    // console.log(this.projectlist[0])
    if (typeof (this.projectlist[0]) == "undefined") {
      console.log('data is void')
      this.$router.push({ name: 'home' })
    }
    // if (this.projectlist.length == 0) {
    //   console.log("length is 0")
    //   this.$router.push({ name: 'home' })
    // }

    //根据权限显示按钮
    if (localStorage.getItem("role") == "admin") {
      this.roleflag = true
    }


    var FormData = require('form-data');
    var data = new FormData();
    data.append('text', this.projectlist[0].id);

    var config = {
      method: 'post',
      url: 'http://localhost:8080/api/project/queryWithIdOrName',
      headers: {
        'Authorization': this.token
      },
      data: data
    };

    this.$axios(config)
      .then(function (response) {
        // console.log(JSON.stringify(response.data));
        this.projectlist[0] = response.data
      })
      .catch(function (error) {
        console.log(error);
      });

    // console.log(this.projectlist[0])
    //组件使用数组，数组为空时报错
    this.id = this.projectlist[0].id
    this.name = this.projectlist[0].name
    this.state = this.projectlist[0].state
    this.contont = this.projectlist[0].contont
    this.manager = this.projectlist[0].manager
    this.setdate = this.projectlist[0].setdate
    this.staff_list = this.projectlist[0].staffList


  }
}
</script>
<style scoped>
.deatilright {
  /* background-color: bisque; */
  /* float: left; */
  width: 100%;
  padding-left: 8%;
  /* height: 100px; */
  align-self: center;
}

.deatilinfotext {
  align-self: center;
  align-items: flex-start;
  position: relative;
  top: 0;
  float: left;
  width: 100%;
  height: 480px;
  margin: 20px 20px;
  /* background-color: #f0f9f4; */
  text-align: left;
  border-radius: 20px;
}

.deatilright {
  margin-top: 3%;
  width: 80%;

}
</style>
  