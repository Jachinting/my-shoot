<template>
  <div><el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" router>
      <el-menu-item index="1" route="/classify/loverclassify">摄影器材器材管理</el-menu-item>
      <el-menu-item index="2" route="/classify/stuffclassify">人員管理</el-menu-item>
      <el-menu-item index="3" route="/classify/propsClassify">道具管理</el-menu-item>
      <el-menu-item index="4" route="/classify/projectclassify">项目管理</el-menu-item>
    </el-menu>
    <router-view></router-view>
  </div>
</template>
  
<script>
// @ is an alias to /src

export default {
  name: 'Classify',
  data() {
    return {
      activeIndex: "1"
    }
  },
  components: {
  },
  methods: {
  }
}
</script>
  