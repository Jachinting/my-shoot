<template>
    <el-row class="flowerdisplay">
        <el-col class="floweshowli" v-for="item in flowerlist" :key="item.id" :span="6">
            <img class="floweshowliimg" :src="item.imgurl" alt="图片" @click="linkto(item)"><br />
            <!-- <span>{{ item.src }}</span><br />
            <span>{{ item.id }}</span> -->
            <span>{{ item.name }}</span>
            <span>：{{ item.state }}</span><br>
            <span class="flowerdetail">{{ item.detail }}</span>
        </el-col>
        <slot></slot>
    </el-row>
</template>
  
<script>
export default {
    name: 'ShowFlowes',
    data() {
        return {

        }
    },
    props: {
        flowerlist: []
    },
    methods: {
        linkto(e) {
            if (this.flowerlist.length !== 1) {
                // console.log(this.flowerlist[0]);
                // console.log(e);
                this.$router.push({ name: 'flowerdeatil', params: { flower: e } })
            }
        }
    }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.flowerdisplay {
    /* width: 1400px;
    height: 250px; */
    margin: 20px auto;
}

/* .flowerdisplay :first-child {
    margin-left: 30px;
} */

.floweshowli {
    position: relative;
    top: 0;
    float: left;
    width: 304px;
    height: 405px;
    margin: 20px 20px;
    background-color: #f0f9f4;
    text-align: center;
    border-radius: 20px;
}

.floweshowliimg {
    margin: 12px 12px 0 12px;
    width: 280px;
    height: 320px;
    display: block;
    object-fit: cover;
    border-radius: 12px;
}

.flowerdetail {
    color: seagreen;
    font: 16px/1.5 "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei",
        "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
    /* float: right;
    margin-right: 50px; */
}
</style>
  