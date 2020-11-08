<template>
  <b-card elevation="3" outlined>
    <b-card-title>{{ event.name }}</b-card-title>
    <b-card-text>{{ new Date(this.event.eventDate).toISOString().slice(0, 10) }}</b-card-text>
    <b-row>
      <b-col>
        <b-button variant="primary" @click='editEvent'>Edit</b-button>
      </b-col>
      <b-col>
        <b-button variant="danger" @click='deleteEvent'>Delete</b-button>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import Api from '../services/api';
import Store from '../store/index';

export default {
  name: 'EventCard',

  props: {
    event: Object,
  },

  methods: {
    async deleteEvent() {
      await Api.deleteEvent(this.event.id, Store.getters.users);
      this.$emit('reload');
    },

    editEvent() {
      this.$emit('edit', this.event);
    },
  },
};
</script>
