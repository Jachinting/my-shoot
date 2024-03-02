<template>
    <div class="register-page">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="register-title">用户添加</span>
            </div>
            <div class="register-form">
                <el-form :model="form" :rules="registerRules" ref="registerForm">
                    <el-form-item prop="userName">
                        <el-input type="text" v-model="form.userName" auto-complete="off" placeholder="请输入用户名">
                            <template slot="prepend"><i style="font-size:20px" class="el-icon-user"></i></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="passWord">
                        <el-input type="text" v-model="form.passWord" auto-complete="off" placeholder="请输入密码">
                            <template slot="prepend"><i style="font-size:20px" class="el-icon-key"></i></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="passWordsecend">
                        <el-input type="text" v-model="form.passWordsecend" auto-complete="off" placeholder="确认密码">
                            <template slot="prepend"><i style="font-size:20px" class="el-icon-key"></i></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button style="width:100%;" type="primary" @click="handleregister"
                            :loading="loading">注册</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>
<script>
export default {
    name: "Register",
    data() {
        var passWordsecendChcek = (registerRules, value, callback) => {
            console.log(this);
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.form.passWord) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        }
        return {
            loading: false,
            form: {
                userName: '',
                passWord: '',
                passWordsecend: ''
            },

            registerRules: {
                userName: [
                    { required: true, message: '请输入账户', trigger: 'blur' },
                ],
                passWord: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
                passWordsecend: [
                    { validator: passWordsecendChcek, trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        handleregister() {
            this.$refs.registerForm.validate().then(() => {
                this.loading = true;
                console.log("表单验证通过，发起请求")

                this.$axios.post('http://127.0.0.1:8080/api/auth/register/byusername', {
                    username: this.form.userName,
                    password: this.form.passWord,
                    email: '1025324@qq.com'
                }, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(res => {
                    console.log(res.data);
                    this.loading = false;
                    if (res.data.code == 200) {
                        this.$notify({
                            title: '添加成功',
                            message: this.form.userName + '添加成功',
                            type: 'success'
                        });

                        localStorage.setItem("username", "用户：" + this.form.userName)
                        localStorage.setItem("userimg", require('../assets/userHeard.png'))

                        this.$router.push('/')
                    } else {
                        this.$notify({
                            title: '注册失败',
                            message: '注册失败，用户名已被使用',
                            type: 'error'
                        });
                    }
                }, err => {
                    console.log(err);
                    this.loading = false;
                })





                //模拟异步请求后台接口 注册操作
                // setTimeout(() => {
                //     this.$router.push('/home');
                //     this.loading = false;
                // }, 1000)
            }).catch((error => {
                this.$message({
                    message: '输入错误！',
                    type: 'warning'
                });
            }))
        }
    }
}

</script>

<style scoped>
.register-page {
    background-image: linear-gradient(180deg, #2af598 0%, #009efd 100%);
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.register-title {
    font-size: 20px;
}

.box-card {
    width: 375px;
}
</style>