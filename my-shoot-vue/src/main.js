import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import vueToPdf from 'vue-to-pdf';


Vue.config.productionTip = false
Vue.prototype.$axios = axios

Vue.use(vueToPdf)
new Vue({
  router,
  axios,
  render: h => h(App)
}).$mount('#app')

Vue.prototype.$islogin = false;