<template>
  <b-container class="bv-example-row bv-example-row-flex-cols">
    <b-row
      class="vh-100 text-center"
      align-v="center"
      align-h="center"
    >
      <b-col cols="6">
        <LoginCard></LoginCard>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import LoginCard from '../components/LoginCard.vue';

export default {
  name: 'Login',
  components: {
    LoginCard,
  },
};
</script>
