<template>
  <div class="Classify page">
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="送给爱人" name="first">
        <LoverClassify></LoverClassify>
      </el-tab-pane>
      <el-tab-pane label="送给朋友" name="second">
        <FriendlyClassify></FriendlyClassify>

      </el-tab-pane>
      <el-tab-pane label="生日庆祝" name="third">
        <BirthdayClassify></BirthdayClassify>
      </el-tab-pane>
      <el-tab-pane label="节日纪念" name="fourth">
        <MemorialClassify></MemorialClassify>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
  
<script>
// @ is an alias to /src

import LoverClassify from './LoverClassify.vue'
import FriendlyClassify from './FriendlyClassify.vue'
import BirthdayClassify from './BirthdayClassify.vue'
import MemorialClassify from './MemorialClassify.vue'


export default {
  name: 'Classify',
  data() {
    return {
      activeName: 'first'
    }
  },
  components: {
    LoverClassify,
    FriendlyClassify,
    BirthdayClassify,
    MemorialClassify
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
}
</script>
  