<template>
    <div class="LoverClassify page">
        <el-dialog title="设备信息" :visible.sync="dialogVisible" width="30%">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="设备名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="器材介绍">
                    <el-input v-model="form.context" type="textarea" :rows="9"></el-input>
                </el-form-item>
                <el-form-item label="相机类型">
                    <el-select v-model="form.type" placeholder="请选择">
                        <el-option label="单反" value="单反"></el-option>
                        <el-option label="无反" value="无反"></el-option>
                        <el-option label="双反" value="双反"></el-option>
                        <el-option label="旁轴" value="旁轴"></el-option>
                        <el-option label="运动相机" value="运动相机"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="快门数">
                    <el-input v-model="form.shutternumber"></el-input>
                </el-form-item>
                <el-form-item label="传感器">
                    <el-select v-model="form.sensor" placeholder="请选择">
                        <el-option label="全画幅" value="全画幅"></el-option>
                        <el-option label="APS画幅" value="APS画幅相机"></el-option>
                        <el-option label="M43画幅" value="M43画幅相机"></el-option>
                        <el-option label="中画幅" value="中画幅相机"></el-option>
                        <el-option label="大画幅" value="大画幅相机"></el-option>
                        <el-option label="其他画幅" value="其他画幅相机"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="相机价值">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
                <el-form-item label="爱机照片">
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
        <div><el-button style="width:80%;" type="primary" @click="add">添加相机</el-button></div>
        <ShowFlowesVue :flowerlist="loverflowerlist"></ShowFlowesVue>
    </div>
</template>
  
<script>
// @ is an alias to /src


import ShowFlowesVue from '@/components/ShowFlowes.vue';

export default {
    name: 'LoverClassify',
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
            loverflowerlist: [
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
        ShowFlowesVue
    },
    methods: {
        load() {
            this.$axios.post("http://localhost:8080/api/eq/queryAll",

                {

                }, {
                headers: {
                    'Authorization': this.token,
                    'Content-Type': 'application/json'
                }
            }

            ).then(res => {
                // console.log(res.data)
                this.loverflowerlist = res.data
                // console.log(this.loverflowerlist)
                for (let i = 0; i < this.loverflowerlist.length; i++) {
                    this.loverflowerlist[i].detail = this.loverflowerlist[i].sensor + ' 快门数：' + this.loverflowerlist[i].shutternumber
                }
                // console.log(this.loverflowerlist)
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
            this.$axios.post("http://localhost:8080/api/eq/save",
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
  