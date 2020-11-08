<template>
<b-container class='EventList'>
  <b-row>
    <b-col v-for='(event, key) in events' :key='key' class='col-4'>
          <EventCard @reload='reloadEvents' :event='event' class='mb-5'/>
    </b-col>
  </b-row>
</b-container>
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
  },
};
</script>

<style>
.EventList {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
}
</style>
