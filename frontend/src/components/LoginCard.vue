<template>
  <b-card title="Entrar">
    <b-form class="login" @submit.prevent="login">
      <div class="mt-5">
        <b-form-input v-model="name" placeholder="E-mail" autofocus>
        </b-form-input>
        <b-form-input
          v-model="password"
          type="password"
          placeholder="Password"
          autofocus
          class="mt-3">
        </b-form-input>
      </div>
      <div class="mt-5">
        <b-button type="submit" variant="outline-primary">Entrar</b-button>
      </div>
    </b-form>
  </b-card>
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
