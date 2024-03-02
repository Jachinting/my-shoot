<template>
    <div class="PropsClassify page">
        <el-dialog title="道具信息" :visible.sync="dialogVisible" width="30%">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="道具名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="道具介绍">
                    <el-input v-model="form.context" type="textarea" :rows="2"></el-input>
                </el-form-item>
                <el-form-item label="道具类型">
                    <el-select v-model="form.type" placeholder="请选择">
                        <el-option label="布景" value="布景"></el-option>
                        <el-option label="设备配件" value="设备配件"></el-option>
                        <el-option label="手持道具" value="手持道具"></el-option>
                        <el-option label="存储介质" value="存储介质"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="道具数量">
                    <el-input v-model="form.count"></el-input>
                </el-form-item>
                <el-form-item label="物品照片">
                    <el-upload class="eq_upload" action="http://localhost:8080/api/image/upload" :on-preview="handlePreview"
                        :on-remove="handleRemove" :before-remove="beforeRemove" multiple :limit="1"
                        :on-exceed="handleExceed" :file-list="fileList" accept=".jpg,.png" :headers="upheaders"
                        :on-success="handleSuccess">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpdate">确 定</el-button>
            </span>
        </el-dialog>
        <div><el-button style="width:80%;" type="warning" @click="add">添加道具</el-button></div>
        <ShowFlowesinprops :propslist="propslist"></ShowFlowesinprops>
    </div>
</template>
  
<script>
// @ is an alias to /src


import ShowFlowesinprops from '@/components/ShowFlowesinprops.vue';

export default {
    name: 'PropsClassify',
    data() {
        return {
            upurl: 'http://localhost:8080/api/image/upload',
            upheaders: {
                Authorization: localStorage.getItem("token")
            },
            fileList: [],
            token: '',
            dialogVisible: false,
            form: {

            },
            propslist: [
                {
                    id: 1,
                    name: "SONY a相机",
                    detail: "快门数：3000",
                    contont: "经典相机",
                    imgurl: require('../../assets/c1.png')
                }, {
                    id: 2,
                    name: "SONY 相机",
                    detail: "快门数：7560",
                    contont: "经典相机",
                    imgurl: require('../../assets/c2.png')
                }, {
                    id: 3,
                    name: "佳能相机",
                    detail: "快门数：5240",
                    contont: "经典相机",
                    imgurl: require('../../assets/c3.png')
                }, {
                    id: 4,
                    name: "徕卡相机",
                    detail: "快门数：13230",
                    contont: "经典相机",
                    imgurl: require('../../assets/c4.png')
                },
                {
                    id: 5,
                    name: "富士康 相机",
                    detail: "快门数：22330",
                    contont: "经典相机",
                    imgurl: require('../../assets/c5.png')
                },
            ]
        }
    },
    components: {
        ShowFlowesinprops
    },
    methods: {
        load() {
            this.$axios.post("http://localhost:8080/api/props/queryAll",

                {

                }, {
                headers: {
                    'Authorization': this.token,
                    'Content-Type': 'application/json'
                }
            }

            ).then(res => {
                // console.log(res.data)
                this.propslist = res.data
                // console.log(this.propslist)
                for (let i = 0; i < this.propslist.length; i++) {
                    this.propslist[i].detail = ' 数量：' + this.propslist[i].count
                }
                // console.log(this.propslist)
                // this.tableData = res.data.data.list
                // this.total = res.data.data.total
            })

            // this.$axios.get("/role").then(res => {
            //   this.roles = res
            // })
        },
        add() {
            this.dialogVisible = true
        },
        handleUpdate() {
            console.log(this.form)
            this.$axios.post("http://localhost:8080/api/props/save",
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
                    // this.load()
                } else {
                    console.log(res)
                    this.$message.error("保存失败")
                }
            })

        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${file.name}？`);
        },
        handleSuccess(response, file, fileList) {
            console.log(response)
            this.form.imgurl = response.data
            console.log(this.form)

        }
    },
    created() {
        if (!localStorage.getItem('logined')) {
            this.$router.push('/login')
            location.reload()
        }
        this.token = localStorage.getItem("token")
        this.load()
    }
}
</script>
  