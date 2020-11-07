<template>
  <div class="Card">
    <v-card elevation="3" outlined>
      <v-card-title>{{ event.name }}</v-card-title>
      <v-card-text>{{ Date(event.eventDate * 1000) }}</v-card-text>
    </v-card>
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

<style>
.Card {
  flex: 0 0 auto;
}
</style>
