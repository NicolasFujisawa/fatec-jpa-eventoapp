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
export default {
  name: 'Login',

  data() {
    return {
      name: '',
      password: '',
    };
  },

  methods: {
    login() {
      const { name, password } = this;

      this.$store.dispatch('login', { name, password });
      this.$router.push('/');
    },
  },
};
</script>
