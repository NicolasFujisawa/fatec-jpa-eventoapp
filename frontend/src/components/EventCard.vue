<template>
  <div class="Card">
    <span class="Name">{{ event.name }}</span>
    <br>
    <span class="Date">{{ Date(event.eventDate * 1000) }}</span>
  </div>
</template>

<script>
export default {
  name: 'EventCard',
  props: {
    event: Object,
  },
};
</script>
