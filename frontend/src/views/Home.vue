<template>
  <b-container class='home'>
    <div id='nav'>
      <router-link to='/'>Home</router-link> |
      <a v-on:click='logout'>Logout</a>
    </div>
    <b-row class='event-list'>
      <EventList :events='events.data' @reload='loadEvents'/>
    </b-row>
    <b-row>
      <b-col>
        <b-button v-b-modal.create-event variant="outline-primary">Criar novo evento</b-button>
        <b-modal id="create-event" hide-footer>
          <CreateEvent @reload='reloadOnEventCreate'></CreateEvent>
        </b-modal>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// @ is an alias to /src
import EventList from '@/components/EventList.vue';
import CreateEvent from '@/components/CreateEvent.vue';
import Api from '@/services/api';
import Store from '@/store/index';

export default {
  name: 'Home',

  components: {
    EventList,
    CreateEvent,
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
    async reloadOnEventCreate() {
      this.$bvModal.hide('create-event');
      await this.loadEvents();
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

.event-list {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
