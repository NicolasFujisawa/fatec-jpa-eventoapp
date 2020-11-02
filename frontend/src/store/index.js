import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      id: null,
      name: null,
      password: null,
    },
    status: '',
  },
  mutations: {
    auth_success(state, user) {
      state.status = 'success';
      state.user = user;
    },
    auth_error(state) {
      state.status = 'error';
    },
    logout(state) {
      state.status = '';
      state.user = {
        id: null,
        name: null,
        password: null,
      };
    },
  },
  getters: {
    users: (state) => state.user,
    authStatus: (state) => state.status,
  },
  actions: {
    login({ commit }, user) {
      if (user) commit('auth_success', user);
      else commit('auth_error');
    },
    logout({ commit }) {
      commit('logout');
    },
  },
});
