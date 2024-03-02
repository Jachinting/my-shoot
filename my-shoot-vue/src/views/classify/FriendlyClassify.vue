<template>
  <div class="FriendlyClassify page">
      <ShowFlowesVue :flowerlist="Friendlyflowerlist"></ShowFlowesVue>
  </div>
</template>

<script>
// @ is an alias to /src


import ShowFlowesVue from '@/components/ShowFlowes.vue';

export default {
  name: 'FriendlyClassify',
  data() {
      return {
        Friendlyflowerlist: [
              {
                  id: 1,
                  name: "水仙花",
                  detail: "点赞数：30",
                  contont: "只爱自己",
                  src: require('../../assets/f5.jpg')
              },{
                  id: 2,
                  name: "仙人掌",
                  detail: "点赞数：30",
                  contont: "坚硬坚强",
                  src: require('../../assets/f6.jpg')
              },{
                  id: 3,
                  name: "波斯菊",
                  detail: "点赞数：30",
                  contont: "永远快乐",
                  src: require('../../assets/f3.jpg')
              },{
                  id: 4,
                  name: "非洲菊",
                  detail: "点赞数：30",
                  contont: "永远快乐",
                  src: require('../../assets/f7.jpg')
              },
          ]
      }
  },
  components: {
      ShowFlowesVue
  }
}
</script>
