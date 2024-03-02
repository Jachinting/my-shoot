<template>
  <el-menu id="elm" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
    background-color="#4169E1" text-color="#fff" active-text-color="#FFFF99" router>
    <el-menu-item index="1" route="/">首页</el-menu-item>
    <el-menu-item index="2" route="/classify">管理菜单</el-menu-item>
    <!-- <el-menu-item index="3" route="/serchinput">搜索</el-menu-item> -->
    <el-menu-item index="7" route="/events">日程活动</el-menu-item>
    <el-menu-item index="8" route="/notice">公告</el-menu-item>
    <el-menu-item class="usera" index="5" route="/register" v-show="username">添加用户</el-menu-item>
    <el-menu-item class="usera" index="4" route="/login" v-show="!username">登录</el-menu-item>
    <el-menu-item class="usera" index="6" @click="logout()" route="/" v-show="username">退出登录</el-menu-item>
    
    <div class="userspace" key="this.islogin" v-show="username">
      <!-- <img :src="userimg" /> -->
      <el-avatar size="small" :src="userimg" @error="errorHandler"></el-avatar>
      <p>{{ username }}</p>
    </div>
  </el-menu>
</template>
  
<script>
import router from '@/router';

export default {
  name: 'Nav',
  
  data() {
    return {
      activeIndex: '1',
      username: '',
      userimg: ''
      
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      // console.log(key, keyPath);
    },
    errorHandler() {
        return true
      },
    logout() {
      console.log(localStorage.getItem("logined"));
      if (localStorage.getItem("logined")) {
        this.username = null
        this.userimg = null
        localStorage.setItem("logined", false)
        this.$notify({
          title: '登出成功',
          message: '退出登录成功,期待再次使用',
          type: 'success'
        })
        localStorage.removeItem("username")
        localStorage.removeItem("userimg")
        localStorage.removeItem("logined")

        router.push('/login')

      }else{
        this.$notify({
          title: '请先登录',
          message: '请先登录',
          type: 'error'
        })
      }
    }
  },
 created() {
    this.username = localStorage.getItem("username")
    this.userimg = localStorage.getItem("userimg")
  }
}
</script>
  #elm{
    max-width:80%
  }
  .usera{
    float:l
  }
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.userspace {
  float: right;
  color: white;
  margin-top: 5px;
}

.userspace img {
  max-width: 50px;
  max-height: 50px;
  border-radius: 30px;
  float: left;
  margin: auto auto;
}

.userspace p {
  max-width: 80px;
  max-height: 50px;
  border-radius: 30px;
  float: left;
  margin-left: 10px;
  margin-right: 10px;
  /* margin: auto auto; */
}
</style>
  