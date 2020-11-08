<template>
<b-modal id='event-form' hide-footer hide-header>
  <b-card title='Evento'>
    <b-form class='EventForm' @submit.prevent='executeMethod'>
      <div class='mt-5'>
        <b-form-input v-model='name' placeholder='Nome do Evento' autofocus>
        </b-form-input>
        <b-form-datepicker
          v-model='eventDate'
          class='mt-3'>
        </b-form-datepicker>
      </div>
      <div class='mt-5'>
        <b-button type='submit' variant='outline-primary'>OK</b-button>
      </div>
    </b-form>
  </b-card>
</b-modal>
</template>

<script>
import Vue from 'vue';
import Api from '../services/api';
import Store from '../store/index';

export default {
  name: 'EventForm',

  props: {
    bus: Vue,
  },

  data() {
    return {
      name: '',
      eventDate: '',
      method: '',
      event: {},
    };
  },

  mounted() {
    this.bus.$on('shown', (args) => {
      this.event = args.event;
      this.method = args.method;
      this.updateInputDefaults();
    });
  },

  methods: {
    async createEvent() {
      const { name, eventDate } = this;
      await Api.createEvent({ name, eventDate }, Store.getters.users);

      this.$emit('reload');
    },

    async putEvent() {
      const { name, eventDate } = this;

      await Api.putEvent(this.event.id, { name, eventDate }, Store.getters.users);

      this.$emit('reload');
    },

    executeMethod() {
      if (this.method === 'Create') {
        this.createEvent();
      } else if (this.method === 'Update') {
        this.putEvent();
      }
    },

    updateInputDefaults() {
      this.name = this.event.name;
      this.eventDate = new Date(this.event.eventDate).toISOString().slice(0, 10);
    },
  },
};
</script>
