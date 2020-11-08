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
      localStorage.setItem('user', JSON.stringify(user));
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
      localStorage.clear();
    },
  },
  getters: {
    users: (state) => state.user,
    authStatus: (state) => state.status,
  },
  actions: {
    login({ commit }, response) {
      if (response.status === 200) {
        commit('auth_success', response.data);
      } else commit('auth_error');
    },
    logout({ commit }) {
      commit('logout');
    },
    initialise({ commit }) {
      const user = JSON.parse(localStorage.getItem('user'));
      if (user) {
        commit('auth_success', user);
      } else {
        commit('auth_error');
      }
    },
  },
});
