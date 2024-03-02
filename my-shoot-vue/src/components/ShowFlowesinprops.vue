<template>
    <el-row class="flowerdisplay">
        <el-col class="floweshowli" v-for="item in propslist" :key="item.id" :span="6">
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
    name: 'ShowFlowesinprops',
    data() {
        return {

        }
    },
    props: {
        propslist: []
    },
    methods: {
        linkto(e) {
            console.log(e)
            if (this.propslist.length !== 1) {
                // console.log(this.propslist[0]);
                // console.log(e);
                this.$router.push({ name: 'propsdeatil', params: { props: e } })
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
    background-color: #f9f8f0;
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
    color: rgb(139, 130, 46);
    font: 16px/1.5 "Helvetica Neue", Helvetica, Arial, "Microsoft Yahei",
        "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
    /* float: right;
    margin-right: 50px; */
}
</style>
  