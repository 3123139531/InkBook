import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import moment from 'moment';
import 'moment/locale/zh-cn';
moment.locale('zh-cn');

const app = createApp(App)
app.use(store).use(router).use(ElementPlus).use(VueAxios,axios)

axios.defaults.baseURL = 'http://101.42.246.11:81'
app.config.globalProperties.$axios = axios
app.mount('#app')
