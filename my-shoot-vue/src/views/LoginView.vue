<template>
  <div class="Login page">
    <LoginForm></LoginForm>
  </div>
</template>

<script>
// @ is an alias to /src

import LoginForm from '../components/LoginForm.vue'


export default {
  name: 'LoginView',
  data() {
    return {
      
    }
  },
  components: {
    LoginForm
  }
}
</script>
