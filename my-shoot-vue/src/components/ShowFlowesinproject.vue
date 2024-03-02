<template>
    <el-row class="projectdisplay">
        <el-col class="projecthowli" v-for="item in projectlist" :key="item.id" :span="6">
            <img class="projecthowliimg" :src="imgurl" alt="图片" @click="linkto(item)"><br />
            <!-- <span>{{ item.src }}</span><br />
            <span>{{ item.id }}</span> -->
            <span>{{ item.name }}</span><br />
            <span>状态：{{ item.state }}</span><br>
            <span class="projectdetail">项目负责人：{{ item.manager }}</span><br />
            <!-- <span class="projectdetail">工作人员：{{ item.staff_list }}</span> -->
        </el-col>
        <slot></slot>
    </el-row>
</template>
  
<script>
export default {
    name: 'ShowFlowesinproject',
    data() {
        return {
            imgurl: require('../assets/project.png')

        }
    },
    props: {
        projectlist: []
    },
    methods: {
        linkto(e) {
            if (this.projectlist.length !== 0) {
                // console.log(this.projectlist[0]);
                // console.log(e);
                this.$router.push({ name: 'projectdeatil', params: { project: e } })
            }
        }
    }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.projectdisplay {
    /* width: 1400px;
    height: 250px; */
    margin: 20px auto;
}

/* .projectdisplay :first-child {
    margin-left: 30px;
} */

.projecthowli {
    position: relative;
    top: 0;
    float: left;
    width: 280px;
    height: 300px;
    margin: 20px 20px;
    background-color: #9bcff1;
    text-align: center;
    border-radius: 20px;
}

.projecthowliimg {
    margin: 12px 12px 0 12px;
    width: 255px;
    height: 160px;
    display: block;
    object-fit: cover;
    border-radius: 12px;
}

.projectdetail {
    margin-top: 30px;
    color: rgb(55, 46, 139);
    font: 16px/1.5 "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei",
        "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
    /* float: right;
    margin-right: 50px; */
}
</style>
  