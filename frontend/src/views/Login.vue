<template>
  <b-container class="bv-example-row bv-example-row-flex-cols">
    <b-row
      class="vh-100 text-center"
      align-v="center"
      align-h="center"
    >
      <b-col cols="6">
        <LoginPage></LoginPage>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import LoginPage from '../components/LoginPage.vue';

export default {
  name: 'Login',
  components: {
    LoginPage,
  },
};
</script>
