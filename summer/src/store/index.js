import { createStore } from 'vuex'

const store = createStore ({
  state: {
    isLogin : false
  },
  getters: {
  },
  mutations: {
    login (state) {
      state.isLogin = true;
    },
    logout (state) {
      state.isLogin = false
    }
  },
  actions: {
  },
  modules: {
  }
})

export default store
