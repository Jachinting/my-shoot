<template>
  <div class="home">
    <div
      style="width: 100%;  border-color: rgb(70, 70, 223); background-color: azure; max-height: 120px; overflow: auto ;margin: 20px 0px">
      <span style="text-align: center;color: rgb(122, 83, 214);">最近公告：</span>
      <span>{{ gonggao_title }}  </span>
      <span>{{ gonggao_time }}</span>
      <br/>
      <span>{{ gonggao_context }}</span>
    </div>
    <CarouselHome />

    <ShowFlowesVue :flowerlist="flowerlist"></ShowFlowesVue>
  </div>
</template>

<script>
// @ is an alias to /src

import { Loading } from 'element-ui';
import CarouselHome from '../components/CarouselHome.vue';
import ShowFlowesVue from '@/components/ShowFlowes.vue';


export default {
  name: 'HomeView',
  data() {
    return {
      //首页数据：
      gonggao_title:"",
      gonggao_time:"",
      gonggao_context: '默认认公告默认告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默告默认公告默认公告默认公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告默认公告',
      flowerlist: [

      ]
    }
  },
  components: {
    CarouselHome,
    ShowFlowesVue
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$axios.get("http://localhost:8080/api/notice/latest"
      ).then(res => {
        // console.log(res.data)
        this.gonggao_context = res.data.context
        this.gonggao_title = res.data.title
        this.gonggao_time = res.data.editdate
      })
    }
  },
  mounted() {
    document.title = '拍摄活动管理'
  }
}
</script>