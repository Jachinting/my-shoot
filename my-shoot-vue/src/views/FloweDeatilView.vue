<template>
  <div class="FlowerDeatilView page">
    <h1>{{ dname }}</h1>
    <ShowFlowesVue style="margin-left: 22%;" :flowerlist="flowerdeatil">
      <div class="deatilinfotext">
        <el-row style="margin-top: 20%;">
          <el-col :span="24" style="margin-top: 2%;">
            <div class="grid-content bg-purple">{{ name }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 2%;">
            <div class="grid-content bg-purple-light">相机类型：{{ type }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple-light">状态：{{ state }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple-light">入库时间：{{ inputdate }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple-light">出库时间：{{ outputdate }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple">器材介绍：{{ contont }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 5%;">
            <div class="grid-content bg-purple">最近责任人：{{ user }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 5%;">
            <span>借出数量：</span>
            <el-input-number v-model="num" :min="1" :max="num" size="small"></el-input-number>
          </el-col>
        </el-row>
      </div>
    </ShowFlowesVue>
    <div class="deatilright">
      <el-button type="primary" plain @click="outeq">借出器材</el-button>
      <el-button type="success" plain @click="inputeq">归还器材</el-button>
      <el-button type="danger" plain @click="breakeq" v-show="roleflag">器材损坏</el-button>
      <el-button type="danger" plain @click="delkeq" v-show="roleflag">器材移除</el-button>
    </div>
  </div>
</template>
  
<script>
// @ is an alias to /src

import ShowFlowesVue from '@/components/ShowFlowes.vue';

export default {
  name: 'FlowerDeatilView',
  data() {
    return {
      dname: '相机设备',
      num: 1,
      id: 0,
      name: "",
      detail: "",
      contont: "",
      src: "",
      state: "123",
      token: "",
      type: "",
      user: "",
      inputdate: "",
      outputdate: "",
      roleflag: false,
      flowerdeatil: [
        {
          id: 1,
          name: "",
          detail: "",
          contont: "介绍",
          src: ""
        }
      ]
    }
  },
  components: {
    ShowFlowesVue
  },
  methods: {
    showinfo() {
      // console.log(this.$route.params.flower)
    },
    outeq() {
      this.$confirm('确认您的的选择，请确认借出', '确认借出', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/eq/StateUpdateById",
          JSON.stringify({
            "id": this.id,
            "state": 1,
            "user": localStorage.getItem("username")
          }), {
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json'
          }
        }
        ).then(res => {
          console.log(res.data)
          this.$message({
            type: 'success',
            message: '借出成功! 请尽快归还！'
          })
        })
          ;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消借出'
        });
      });
    },
    inputeq() {
      if (this.user != localStorage.getItem("username")) {
        this.$message({
          type: 'error',
          message: '您目前非该器材责任人，请确认相关信息'
        })
        return;
      }
      this.$confirm('确认您的的选择，请确认归还', '确认归还', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/eq/StateUpdateById",
          JSON.stringify({
            "id": this.id,
            "state": 0,
            "user": localStorage.getItem("username")
          }), {
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json'
          }
        }
        ).then(res => {
          console.log(res.data)
          this.$message({
            type: 'success',
            message: '归还成功！'
          })
        })
          ;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消归还'
        });
      });
    },
    breakeq() {
      this.$confirm('确认器材进入维修，请确认', '确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/eq/StateUpdateById",
          JSON.stringify({
            "id": this.id,
            "state": 2,
            "user": localStorage.getItem("username")
          }), {
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json'
          }
        }
        ).then(res => {
          console.log(res.data)
          this.$message({
            type: 'success',
            message: '状态更改成功'
          })
        })
          ;
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消进入维修状态'
        });
      });
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
    this.flowerdeatil[0] = this.$route.params.flower
    // console.log(this.flowerdeatil[0])
    if (typeof (this.flowerdeatil[0]) == "undefined") {
      console.log('data is void')
      this.$router.push({ name: 'home' })
    }
    // if (this.flowerdeatil.length == 0) {
    //   console.log("length is 0")
    //   this.$router.push({ name: 'home' })
    // }

    //根据权限显示按钮
    if (localStorage.getItem("role") == "admin") {
      this.roleflag = true
    }


    var FormData = require('form-data');
    var data = new FormData();
    data.append('text', this.flowerdeatil[0].id);

    var config = {
      method: 'post',
      url: 'http://localhost:8080/api/eq/queryWithIdOrName',
      headers: {
        'Authorization': this.token
      },
      data: data
    };

    this.$axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
        this.flowerdeatil[0] = response.data
      })
      .catch(function (error) {
        console.log(error);
      });

    //组件使用数组，数组为空时报错
    this.id = this.flowerdeatil[0].id
    this.name = this.flowerdeatil[0].name
    this.state = this.flowerdeatil[0].state
    this.contont = this.flowerdeatil[0].context
    this.type = this.flowerdeatil[0].type
    this.user = this.flowerdeatil[0].user
    this.inputdate = this.flowerdeatil[0].inputdate
    this.outputdate = this.flowerdeatil[0].outputdate

  }
}
</script>
<style>
.deatilright {
  /* background-color: bisque; */
  /* float: left; */
  margin-top: 100px;
  margin-left: 1%;
}

.deatilinfotext {
  position: relative;
  top: 0;
  float: left;
  width: 304px;
  height: 405px;
  margin: 20px 20px;
  /* background-color: #f0f9f4; */
  text-align: center;
  border-radius: 20px;
}
</style>
  