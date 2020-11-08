<template>
  <div class="Card">
    <b-card elevation="3" outlined>
      <b-card-title>{{ event.name }}</b-card-title>
      <b-card-text>{{ new Date(event.eventDate).toLocaleDateString('en-US') }}</b-card-text>
      <b-row>
        <b-col>
          <b-button variant="primary">Edit</b-button>
        </b-col>
        <b-col>
          <b-button variant="danger" @click='deleteEvent'>Delete</b-button>
        </b-col>
      </b-row>
    </b-card>
  </div>
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
      console.log(this.event);
      await Api.deleteEvent(this.event.id, Store.getters.users);
      this.$emit('reload');
    },
  },
};
</script>

<style>
.Card {
  flex: 0 0 auto;
}
</style>
