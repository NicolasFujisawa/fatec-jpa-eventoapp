<template>
<div class='Events'>
  <div v-for='(event, key) in events' :key='key' class='EventList'>
        <EventCard :event='event'/>
  </div>
</div>
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
};
</script>
