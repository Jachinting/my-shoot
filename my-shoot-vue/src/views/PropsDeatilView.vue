<template>
  <div class="PropsDeatilView page">
    <h1>{{ dname }}</h1>
    <ShowFlowesinprops style="margin-left: 22%;" :propslist="propslist">
      <div class="deatilinfotext">
        <el-row style="margin-top: 20%;">
          <el-col :span="24" style="margin-top: 2%;">
            <div class="grid-content bg-purple">{{ name }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 15%;">
            <div class="grid-content bg-purple-light">道具类型：{{ type }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple-light">数量：{{ max }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple-light">入库时间：{{ inputdate }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 3%;">
            <div class="grid-content bg-purple">道具介绍：{{ contont }}</div>
          </el-col>
          <el-col :span="24" style="margin-top: 5%;">
            <span>借出数量：</span>
            <el-input-number v-model="num" :min="1" :max="max" size="small"></el-input-number>
          </el-col>
        </el-row>
      </div>
    </ShowFlowesinprops>
    <div class="deatilright">
      <el-button type="primary" plain @click="outeq">借出道具</el-button>
      <el-button type="success" plain @click="inputeq">归还道具</el-button>
      <el-button type="success" plain @click="inputeqall">所有道具借还信息</el-button>
      <el-button type="danger" plain @click="delprop" v-show="roleflag">道具移除</el-button>

    </div>
    <el-dialog title="借还信息" :visible.sync="dialogVisible" width="50%">
      <el-table :data="outdata" border stripe>
        <el-table-column prop="username" label="用户" width="140"></el-table-column>
        <el-table-column prop="state" label="状态" width="140"></el-table-column>
        <el-table-column prop="count" label="数量" width="60"></el-table-column>
        <el-table-column prop="outdate" label="时间"></el-table-column>
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
              icon-color="red" title="您确定已归还吗？" @confirm="del(scope.row.id, scope.row.count)">
              <el-button type="primary" icon="el-icon-delete" circle slot="reference"></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
  
<script>
// @ is an alias to /src

import ShowFlowesinprops from '@/components/ShowFlowesinprops.vue';

export default {
  name: 'PropsDeatilView',
  data() {
    return {
      dname: '道具详情',
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
      dialogVisible: false,
      roleflag: false,
      propslist: [
        {
          id: 1,
          name: "",
          detail: "",
          contont: "介绍",
          src: ""
        }
      ],
      outdata: []
    }
  },
  components: {
    ShowFlowesinprops
  },
  methods: {
    showinfo() {
      // console.log(this.$route.params.flower)
    },
    delprop() {
      this.$confirm('确认您的的选择，请确认移除此道具', '确认移除', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/props/del",
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
            message: '移除成功，此道具相关数据已删除'
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
    outeq() {
      this.$confirm('确认您的的选择，请确认借出', '确认借出', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post("http://localhost:8080/api/props/StateUpdateById",
          JSON.stringify({
            "propid": this.id,
            "option": 1,
            "count": this.num,
            "username": localStorage.getItem("username")
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
      this.dialogVisible = true

      this.$axios.post("http://localhost:8080/api/props/queryWithIdAndName",
        JSON.stringify({
          "propid": this.id,
          "username": localStorage.getItem("username")
        }), {
        headers: {
          'Authorization': this.token,
          'Content-Type': 'application/json'
        }
      }
      ).then(res => {
        // console.log("借出数据：" + JSON.stringify(res.data))
        this.outdata = res.data
        console.log(this.outdata)
      })
    },inputeqall() {
      this.dialogVisible = true

      this.$axios.post("http://localhost:8080/api/props/queryRecord",
        JSON.stringify({
          "propid": this.id
        }), {
        headers: {
          'Authorization': this.token,
          'Content-Type': 'application/json'
        }
      }
      ).then(res => {
        // console.log("借出数据：" + JSON.stringify(res.data))
        this.outdata = res.data
        console.log(this.outdata)
      })
    },
    del(rid, count) {
      console.log(rid, count)
      this.$axios.post("http://localhost:8080/api/props/StateUpdateById",
        JSON.stringify({
          "id": rid,
          "propid": this.id,
          "count": count,
          "option": 0
        }), {
        headers: {
          'Authorization': this.token,
          'Content-Type': 'application/json'
        }
      }
      ).then(res => {
        // console.log("借出数据：" + JSON.stringify(res.data))
        this.$message({
          type: 'success',
          message: '归还成功!'
        })
      })
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
    this.propslist[0] = this.$route.params.props
    // console.log(this.propslist[0])
    if (typeof (this.propslist[0]) == "undefined") {
      console.log('data is void')
      this.$router.push({ name: 'home' })
    }
    // if (this.propslist.length == 0) {
    //   console.log("length is 0")
    //   this.$router.push({ name: 'home' })
    // }

    //根据权限显示按钮
    if (localStorage.getItem("role") == "admin") {
      this.roleflag = true
    }


    var FormData = require('form-data');
    var data = new FormData();
    data.append('text', this.propslist[0].id);

    var config = {
      method: 'post',
      url: 'http://localhost:8080/api/props/queryWithIdOrName',
      headers: {
        'Authorization': this.token
      },
      data: data
    };

    this.$axios(config)
      .then(function (response) {
        // console.log(JSON.stringify(response.data));
        this.propslist[0] = response.data
      })
      .catch(function (error) {
        console.log(error);
      });

    //组件使用数组，数组为空时报错
    this.id = this.propslist[0].id
    this.name = this.propslist[0].name
    this.state = this.propslist[0].state
    this.contont = this.propslist[0].context
    this.type = this.propslist[0].type
    this.user = this.propslist[0].user
    this.inputdate = this.propslist[0].inputdate
    this.outputdate = this.propslist[0].outputdate
    this.max = this.propslist[0].count

  }
}
</script>
<style scoped>
.deatilright {
  /* background-color: bisque; */
  /* float: left; */
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
  