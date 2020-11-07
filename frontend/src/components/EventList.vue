<template>
<div class='EventList'>
  <div v-for='(event, key) in events' :key='key' class='Event'>
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

<style>
.EventList {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
}
</style>
