<template>
    <div class="stuffClassifyClassify page">
        <el-table :data="tableData" style="width: 100%">

            <el-table-column label="用户名" width="180">
                <template slot-scope="scope">
                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.username }}</el-tag>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="职责" width="180">
                <template slot-scope="scope">
                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.role }}</el-tag>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="姓名" width="180">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>姓名: {{ scope.row.name }}</p>
                        <p>住址: {{ scope.row.address }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.name }}</el-tag>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column label="e-mail" width="180">
                <template slot-scope="scope">

                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.email }}</el-tag>
                    </div>

                </template>
            </el-table-column>
            <el-table-column label="联系方式" width="180">
                <template slot-scope="scope">

                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.phone }}</el-tag>
                    </div>
                </template>
            </el-table-column>
            <!-- <el-table-column label="创建日期" width="180">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </template>
            </el-table-column> -->
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog title="人员信息" :visible.sync="dialogVisible" width="30%">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号码">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item label="职责变更">
                    <el-select v-model="form.role" placeholder="请选择用户职责">
                        <el-option label="管理员" value="admin"></el-option>
                        <el-option label="摄影器材管理员" value="equipmentadmin"></el-option>
                        <el-option label="布景道具管理员" value="propadmin"></el-option>
                        <el-option label="模特演员联络人" value="actorcontact"></el-option>
                        <el-option label="项目经理" value="projectmanager"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="E-mail">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpdate">确 定</el-button>
            </span>
        </el-dialog>
        <!-- <el-button @click="open">更新用户信息</el-button> -->
    </div>
</template>
  
<script>
export default {
    name: 'stuffClassify',
    data() {
        return {
            token: '',
            dialogVisible: false,
            form: {

            },
            tableData: []
        }
    },
    methods: {
        handleEdit(index, row) {
            // console.log(index, row);
            this.form = row
            this.dialogVisible = true
        },
        handleUpdate(row) {
            if (localStorage.getItem('role') == 'admin') {
                this.$axios.post('http://localhost:8080/api/account/update',
                    // {
                    //     'id':this.form.id,
                    //     'name':this.form.name,
                    //        'role':this.form.role,
                    //     'email':this.form.email,
                    //     'usernamne':this.form.username,
                    //     'phone':this.form.phone
                    // }
                    JSON.stringify(this.form)
                    , {
                        headers: {
                            'Authorization': this.token,
                            'Content-Type': 'application/json'
                        },

                    }).then(res => {
                        console.log(res)
                        if (res.data.code == 200) {
                            this.$notify({
                                title: '成功',
                                message: '成功',
                                type: 'success'
                            });
                        } else {
                            this.$notify({
                                title: '失败',
                                message: '更新失败',
                                type: 'error'
                            });
                        }
                    }, err => {
                        console.log(err);
                    })
                this.dialogVisible = false
            } else {
                this.$notify({
                    title: '失败',
                    message: '权限不足，请使用管理员账号',
                    type: 'error'
                });
            }

        },
        handleDelete(index, row) {
            // console.log(index, row);
            this.$confirm('此操作将永久删除该用户记录及其账号, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.delete(row)
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        delete(row) {
            //删除用户
            console.log("删除用户，逻辑未做")
        },
        open() {
            this.loading = true;
            if (!localStorage.getItem('logined')) {
                this.$router.push('/login')
                location.reload()
            }
            this.token = localStorage.getItem("token")
            this.$axios.get('http://localhost:8080/api/account/queryAll', {
                headers: {
                    'Authorization': this.token
                }
            }).then(res => {
                // console.log(res.data.code);
                // this.loading = false;
                if (res.data.code == 200) {
                    this.tableData = res.data.data
                    this.$notify({
                        title: '成功',
                        message: '成功',
                        type: 'success'
                    });
                } else {
                    this.$notify({
                        title: '失败',
                        message: '请检查',
                        type: 'error'
                    });
                }
            }, err => {
                console.log(err);
                this.loading = false;
            })
        },
        load() {
            this.loading = true;
            if (!localStorage.getItem('logined')) {
                this.$router.push('/login')
                location.reload()
            }
            this.token = localStorage.getItem("token")
            this.$axios.get('http://localhost:8080/api/account/queryAll', {
                headers: {
                    'Authorization': this.token
                }
            }).then(res => {
                if (res.data.code == 200) {
                    this.tableData = res.data.data
                }
            }, err => {
                console.log(err);
                this.loading = false;
            })
        }
    },
    created() {
        this.load()
    }
}
</script>