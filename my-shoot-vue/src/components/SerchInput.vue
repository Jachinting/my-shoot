<template>
    <div>
        <el-input style="width: 500px;" v-model="input" placeholder="请输入内容"></el-input>
        <ShowFlowesVue :flowerlist="getDataList"></ShowFlowesVue>
    </div>
</template>
  
<script>
import ShowFlowesVue from '@/components/ShowFlowes.vue';
export default {
    name: "SerchInput",
    data() {
        return {
            input: "",
            flowerlist: [
                {
                    id: 1,
                    name: "白玫瑰",
                    detail: "点赞数：30",
                    contont: "我足以与你相配",
                    src: require('../assets/f1.jpg')
                }, {
                    id: 2,
                    name: "波斯菊",
                    detail: "点赞数：668",
                    contont: "天天快乐",
                    src: require('../assets/f2.jpg')
                }, {
                    id: 3,
                    name: "紫罗兰",
                    detail: "点赞数：43",
                    contont: "对我而言你永远那么美",
                    src: require('../assets/f3.jpg')
                }, {
                    id: 4,
                    name: "樱花",
                    detail: "点赞数：323",
                    contont: "生命/等你回来",
                    src: require('../assets/f4.jpg')
                }, {
                    id: 5,
                    name: "红玫瑰",
                    detail: "点赞数：330",
                    contont: "我爱你",
                    src: require('../assets/f5.jpg')
                }, {
                    id: 6,
                    name: "杜鹃花",
                    detail: "点赞数：545",
                    contont: "为了我保重你自己",
                    src: require('../assets/f6.jpg')
                }, {
                    id: 7,
                    name: "仙人掌",
                    detail: "点赞数：1330",
                    contont: "你是我的天使 / 温暖",
                    src: require('../assets/f7.jpg')
                }, {
                    id: 8,
                    name: "忘忧草",
                    detail: "点赞数：3302",
                    contont: "放下他(她)放下忧愁",
                    src: require('../assets/f8.jpg')
                }, {
                    id: 9,
                    name: "长春花",
                    detail: "点赞数：30",
                    contont: "快乐的回忆",
                    src: require('../assets/f8.jpg')
                }, {
                    id: 10,
                    name: "西洋报春",
                    detail: "点赞数：30",
                    contont: "青年时代",
                    src: require('../assets/f1.jpg')
                }, {
                    id: 11,
                    name: "雏菊",
                    detail: "点赞数：30",
                    contont: "天真无邪",
                    src: require('../assets/f6.jpg')
                }, {
                    id: 12,
                    name: "琉璃花",
                    detail: "点赞数：30",
                    contont: "回想",
                    src: require('../assets/f2.jpg')
                },
                {
                    id: 13,
                    name: "水仙花",
                    detail: "点赞数：30",
                    contont: "只爱自己",
                    src: require('../assets/f5.jpg')
                }, {
                    id: 14,
                    name: "仙人掌",
                    detail: "点赞数：30",
                    contont: "坚硬坚强",
                    src: require('../assets/f6.jpg')
                }, {
                    id: 15,
                    name: "波斯菊",
                    detail: "点赞数：30",
                    contont: "永远快乐",
                    src: require('../assets/f3.jpg')
                }, {
                    id: 16,
                    name: "非洲菊",
                    detail: "点赞数：30",
                    contont: "永远快乐",
                    src: require('../assets/f7.jpg')
                },
            ]
        }
    },
    mounted() {

    },
    methods: {
        load(){
            
        }
    },
    computed: {
        getDataList() {
            return this.flowerlist.filter(item => item.name.indexOf(this.input) > -1)
        }
    },
    components: {
        ShowFlowesVue
    },
    created(){
        if (!localStorage.getItem('logined')) {
            this.$router.push('/login')
            location.reload()
        }
        this.token = localStorage.getItem("token")
        this.load()
    }
}
</script>