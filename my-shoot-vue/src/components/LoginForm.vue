<template>
    <div class="login-page">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span class="login-title">🔐用户登录</span>
            </div>
            <div class="login-form">
                <el-form :model="form" :rules="loginRules" ref="loginForm">
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
                    <el-form-item>
                        <el-button style="width:100%;" type="primary" @click="handleLogin" :loading="loading">登录</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>
<script>
import router from '@/router';

export default {
    name: "LoginForm",
    data() {
        return {
            loading: false,
            form: {
                userName: '',
                passWord: ''
            },
            loginRules: {
                userName: [
                    { required: true, message: '请输入账户', trigger: 'blur' },
                ],
                passWord: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        handleLogin() {
            this.$refs.loginForm.validate().then(() => {
                console.log("表单验证通过，发起请求")
                this.loading = true;
                // console.log(this.form);


                this.$axios.post('http://127.0.0.1:8080/api/auth/login', {
                    username: this.form.userName,
                    password: this.form.passWord
                }, {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(res => {
                    console.log(res.data);
                    this.loading = false;
                    if (res.data.code == 200) {
                        this.$notify({
                            title: '登录成功',
                            message: res.data.data.username + '登录成功,欢迎使用',
                            type: 'success'
                        });

                        localStorage.setItem("username",res.data.data.name)
                        localStorage.setItem("role",res.data.data.role)
                        localStorage.setItem("token", "Bearer " + res.data.data.token)
                        localStorage.setItem("userimg", require('../assets/userHeard.png'))
                        localStorage.setItem("logined", true)
                        this.$router.push('/')
                        location.reload()
                    } else {
                        this.$notify({
                            title: '登录失败',
                            message: '登录失败，请检查用户名或密码',
                            type: 'error'
                        });
                    }
                }, err => {
                    console.log(err);
                    this.loading = false;
                })








                //模拟异步请求后台接口 登录操作
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
.login-page {
    background-image: linear-gradient(180deg, #13d8ce 0%, #447b9b 100%);
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-title {
    font-size: 20px;
}

.box-card {
    width: 375px;
}
</style>