<template>
  <b-card title="CreateEventCard">
    <b-form class="EventForm" @submit.prevent="createEvent">
      <div class="mt-5">
        <b-form-input v-model="name" placeholder="Nome do Evento" autofocus>
        </b-form-input>
        <b-form-input
          v-model="eventDate"
          placeholder="Data"
          autofocus
          class="mt-3">
        </b-form-input>
      </div>
      <div class="mt-5">
        <b-button type="submit" variant="outline-primary">Criar</b-button>
      </div>
    </b-form>
  </b-card>
</template>

<script>
import Api from '../services/api';
import Store from '../store/index';

export default {
  name: 'CreateEvent',

  data() {
    return {
      name: '',
      eventDate: '',
    };
  },

  methods: {
    async createEvent() {
      const { name, eventDate } = this;

      await Api.createEvent({ name, eventDate }, Store.getters.users);

      this.$emit('reload');
    },
  },
};
</script>
