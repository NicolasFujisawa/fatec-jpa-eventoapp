<template>
  <b-row
    align-h='center'
  >
    <b-col v-for='(event, key) in events' :key='key' class='col-4'>
          <EventCard @reload='reloadEvents' @edit='editEvent' :event='event' class='mb-5'/>
    </b-col>
  </b-row>
</template>

<script>
import EventCard from './EventCard.vue';

export default {
  name: 'EventList',
  components: {
    EventCard,
  },

  props: {
    events: Array,
  },

  methods: {
    reloadEvents() {
      this.$emit('reload');
    },

    editEvent(event) {
      this.$emit('edit', event);
    },
  },
};
</script>
