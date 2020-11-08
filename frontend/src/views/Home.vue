<template>
  <b-container class='home'>
    <b-row class='mb-3 mt-3'>
      <b-col>
        <b-button @click='createEvent' variant='outline-primary'>
          Criar novo evento
        </b-button>
      </b-col>
      <b-col>
        <b-button @click='logout' variant="danger">Logout</b-button>
      </b-col>
    </b-row>
    <EventList :events='events.data' @reload='loadEvents' @edit='editEvent'/>
    <EventForm
      :method='eventFormMethod'
      :event='eventFormEvent'
      :bus='bus'
      @reload='reloadOnEventCreate'
    >
    </EventForm>
  </b-container>
</template>

<script>
import Vue from 'vue';
import EventList from '@/components/EventList.vue';
import EventForm from '@/components/EventForm.vue';
import Api from '@/services/api';
import Store from '@/store/index';

export default {
  name: 'Home',

  components: {
    EventList,
    EventForm,
  },

  async mounted() {
    await this.loadEvents();
  },

  data() {
    return {
      bus: new Vue(),
      events: {},
      eventFormMethod: '',
      eventFormEvent: {},
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
      this.$bvModal.hide('event-form');
      await this.loadEvents();
    },

    editEvent(event) {
      this.bus.$emit('shown', { method: 'Update', event });
      this.$bvModal.show('event-form');
    },

    createEvent() {
      this.bus.$emit('shown', { method: 'Create', event: {} });
      this.$bvModal.show('event-form');
    },
  },
};
</script>
