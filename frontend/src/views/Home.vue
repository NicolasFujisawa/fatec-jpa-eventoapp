<template>
  <div class='home'>
    <div id='nav'>
      <router-link to='/'>Home</router-link> |
      <a v-on:click='logout'>Logout</a>
    </div>
    <img alt='Vue logo' src='../assets/logo.png'>
    <EventList :events='events.data'/>
  </div>
</template>

<script>
// @ is an alias to /src
import EventList from '@/components/EventList.vue';
import Api from '@/services/api';
import Store from '@/store/index';

export default {
  name: 'Home',

  components: {
    EventList,
  },

  async mounted() {
    await this.loadEvents();
  },

  data() {
    return {
      events: {},
    };
  },

  methods: {
    logout() {
      this.$store.dispatch('logout');
      this.$router.push('/login');
    },
    async loadEvents() {
      try {
        this.events = await Api.getEventsByUserName(Store.getters.users);
      } catch (e) {
        console.log(e);
      }
    },
  },
};
</script>

<style>
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  cursor: pointer;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
