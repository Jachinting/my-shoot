<template>
    <div class="Carousel">
        <el-carousel height="500px">
            <el-carousel-item v-for="item in lunbo" :key="item.id">
                <!-- <h3 class="small">{{ item.src }}</h3> -->
                <img class="imgcarousel" :src="item.src" alt="近期优秀拍摄作品">
                <!-- <el-image :src="item.src">
                </el-image> -->
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
  
<script>
export default {
    name: 'CarouselHome',
    data() {
        return {
            lunbo: [
                {
                    id: 1,
                    src: require('../assets/b4.png')
                }, {
                    id: 2,
                    src: require('../assets/b3.png')
                },
                {
                    id: 3,
                    src: require('../assets/b2.png')
                },
                {
                    id: 4,
                    src: require('../assets/b1.png')
                }
            ]
        };
    },
    methods: {

    }
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.imgcarousel {
    height: 100%;
    width: 100%;
    display: block;
    object-fit: cover;

}
.Carousel{
    max-width: 80%;
    margin: auto auto;
}
</style>
  