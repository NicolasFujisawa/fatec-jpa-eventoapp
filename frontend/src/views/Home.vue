<template>
  <b-container class='home' align-h="center">
    <b-row class='mb-3 mt-3'>
      <b-col>
        <b-button v-b-modal.create-event variant='outline-primary'>
          Criar novo evento
        </b-button>
      </b-col>
      <b-col>
        <b-button @click='logout' variant="danger">Logout</b-button>
      </b-col>
    </b-row>
    <EventList :events='events.data' @reload='loadEvents'/>
    <CreateEvent :method="Create" @reload='reloadOnEventCreate'></CreateEvent>
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
