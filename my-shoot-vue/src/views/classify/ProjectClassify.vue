<template>
  <div class="ProjectClassify page">
      <ShowFlowesinproject :projectlist="projectlist"></ShowFlowesinproject>
  </div>
</template>

<script>
// @ is an alias to /imgurl


import ShowFlowesinproject from '@/components/ShowFlowesinproject.vue';

export default {
  name: 'ProjectClassify',
  data() {
      return {
        projectlist: [
              {
                  
                  imgurl: require('../../assets/project.png')
              },{
                  
                  imgurl: require('../../assets/project.png')
              },{
                 
                  imgurl: require('../../assets/project.png')
              },{
                  id: 4,
                  name: "琉璃花",
                  detail: "点赞数：30",
                  contont: "回想",
                  imgurl: require('../../assets/project.png')
              }
          ]
      }
  },
  components: {
    ShowFlowesinproject
  },
  methods:{
    load() {
            this.$axios.post("http://localhost:8080/api/project/queryAll",

                {

                }, {
                headers: {
                    'Authorization': this.token,
                    'Content-Type': 'application/json'
                }
            }

            ).then(res => {
                // console.log(res.data)
                this.projectlist = res.data
                // console.log(this.projectlist)
               
                // console.log(this.projectlist)
                // this.tableData = res.data.data.list
                // this.total = res.data.data.total
            })

            // this.$axios.get("/role").then(res => {
            //   this.roles = res
            // })
        },
  },
  created() {
        if (!localStorage.getItem('logined')) {
            this.$router.push('/login')
            location.reload()
        }
        this.token = localStorage.getItem("token")
        this.load()
    }
}
</script>
