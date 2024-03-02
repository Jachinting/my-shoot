<template>
  <div class="BirthdayClassify page">
      <ShowFlowesVue :flowerlist="Birthdayflowerlist"></ShowFlowesVue>
  </div>
</template>

<script>
// @ is an alias to /src


import ShowFlowesVue from '@/components/ShowFlowes.vue';

export default {
  name: 'BirthdayClassify',
  data() {
      return {
        Birthdayflowerlist: [
              {
                  id: 1,
                  name: "金凤花",
                  detail: "点赞：30",
                  contont: "赤子心",
                  src: require('../../assets/f3.jpg')
              },{
                  id: 2,
                  name: "虎茸草",
                  detail: "点赞：30",
                  contont: "纯真",
                  src: require('../../assets/f5.jpg')
              },{
                  id: 3,
                  name: "白杨",
                  detail: "点赞：30",
                  contont: "勇气",
                  src: require('../../assets/f1.jpg')
              },{
                  id: 4,
                  name: "寿星草",
                  detail: "点赞：30",
                  contont: "永久的幸福",
                  src: require('../../assets/f8.jpg')
              },
          ]
      }
  },
  components: {
      ShowFlowesVue
  }
}
</script>
