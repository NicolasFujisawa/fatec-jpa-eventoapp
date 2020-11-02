<template>
 <div>
   <form class="login" @submit.prevent="login">
     <h1>Sign in</h1>
     <label>Username</label>
     <input required v-model="name" placeholder="Name"/>
     <label>Password</label>
     <input required v-model="password" type="password" placeholder="Password"/>
     <hr/>
     <button type="submit">Login</button>
   </form>
 </div>
</template>

<script>
import Api from '../services/api';

export default {
  name: 'Login',

  data() {
    return {
      name: '',
      password: '',
    };
  },

  methods: {
    async login() {
      const { name, password } = this;
      const response = await Api.login({ name, password });

      this.$store.dispatch('login', response);
      this.$router.push('/');
    },
  },
};
</script>
