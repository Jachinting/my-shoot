<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      &nbsp;
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增公告 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="title" label="标题" width="140"></el-table-column>
      <el-table-column prop="context" label="内容" ></el-table-column>
      <el-table-column prop="editdate" label="时间" width="150%"></el-table-column>
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

    <el-dialog title="编辑公告" :visible.sync="dialogFormVisible" width="40%">
      <el-form label-width="80px" size="small">
        <el-form-item label="公告标题">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="公告内容">
          <el-input type="textarea" :rows="9" v-model="form.context" autocomplete="off"></el-input>
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
  name: "Notice",
  data() {
    return {
      token: '',
      tableData: [],
      total: 0,//总数据数
      pageNum: 1,//当前页
      pageSize: 5,//每页显示的行数
      name: "",//搜索的公告标题
      editor: "",//
      cbs: "",
      other: "",
      form: {},
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
      this.$axios.post("http://localhost:8080/api/notice/getlistbypage",

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

      // this.$axios.get("/role").then(res => {
      //   this.roles = res
      // })
    },
    save() {
      this.$axios.post("http://localhost:8080/api/notice/save",
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
      this.$axios.post("http://localhost:8080/api/notice/del",
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
    delBatch() {
      //未对接后端接口，不使用
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.$axios.post("/notice/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
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