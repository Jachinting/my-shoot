<template>
    <div class="Register page">
      <RegisterForm/>
    </div>
  </template>

<script>
// @ is an alias to /src

import RegisterForm from '../components/RegisterForm.vue'


export default {
  name: 'LoginView',
  data() {
    return {
      
    }
  },
  components: {
    RegisterForm
  }
}
</script>
