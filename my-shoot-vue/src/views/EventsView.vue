<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入活动名称或发起人名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      &nbsp;
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">添加活动 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="eventname" label="活动名称" width="200"></el-table-column>
      <el-table-column prop="content" label="活动内容"></el-table-column>
      <el-table-column prop="startdate" label="活动起始时间" width="120"></el-table-column>
      <el-table-column prop="enddate" label="活动结束时间" width="120"></el-table-column>
      <el-table-column prop="location" label="活动地点" width="180"></el-table-column>
      <el-table-column prop="username" label="发起人" width="80"></el-table-column>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" circle @click="handleEdit(scope.row)"></el-button>
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" icon="el-icon-delete" circle slot="reference"></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog title="新增活动" :visible.sync="dialogFormVisible" width="40%">
      <el-form label-width="80px" size="small">
        <el-form-item label="活动名称">
          <el-input v-model="form.eventname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动内容">
          <el-input type="textarea" :rows="9" v-model="form.content" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="活动起止时间">
          <el-input type="textarea" :rows="2" v-model="form.starttime" autocomplete="off"></el-input>
        </el-form-item> -->
        <el-form-item label="活动时间">
          <el-col :span="11">
            <el-form-item >
              <el-date-picker type="date" placeholder="选择日期" v-model="form.startdate"
                style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-form-item >
              <el-date-picker placeholder="选择日期" v-model="form.enddate" style="width: 100%;"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="活动地点">
          <el-input type="textarea" :rows="1" v-model="form.location" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="编辑时间">
            <el-date-picker v-model="form.editdate" type="date" value-format="yyyy-MM-dd" default-value=""
              autocomplete="off"></el-date-picker>
          </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
   
<script>
export default {
  name: "Events",
  data() {
    return {
      token: '',
      tableData: [],
      total: 0,//总数据数
      pageNum: 1,//当前页
      pageSize: 5,//每页显示的行数
      name: "",//搜索关键字
      editor: "",//
      cbs: "",
      other: "",
      form: {
        "username": ""
      },
      roles: [],
      dialogFormVisible: false,
      multipleSelection: []
    }
  },
  created() {

    if (!localStorage.getItem('logined')) {
      this.$router.push('/login')
      location.reload()
    }
    this.token = localStorage.getItem("token")
    this.load()
  },
  methods: {
    load() {
      this.$axios.post("http://localhost:8080/api/event/getlistbypage",

        JSON.stringify({
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }), {
        headers: {
          'Authorization': this.token,
          'Content-Type': 'application/json'
        }
      }

      ).then(res => {
        console.log(res.data)
        this.tableData = res.data.data.list
        this.total = res.data.data.total
      })
      this.form.username = localStorage.getItem("username")
      // this.$axios.get("/role").then(res => {
      //   this.roles = res
      // })
    },
    save() {
      this.$axios.post("http://localhost:8080/api/event/save",
        JSON.stringify(this.form)
        , {
          headers: {
            'Authorization': this.token,
            'Content-Type': 'application/json'
          },
        }
      ).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          console.log(res)
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      this.$axios.post("http://localhost:8080/api/event/del",
        JSON.stringify({
          id: id
        }), {
        headers: {
          'Authorization': this.token,
          'Content-Type': 'application/json'
        },
      }).then(res => {
        if (res.data.code == 200) {
          this.$message.success("删除成功")
          this.load()
        } else {
          console.log(res)
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    reset() {
      this.name = ""
      // this.email = ""
      // this.address = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>
   
<style>
.headerBg {
  background: #eee !important;
}
</style>