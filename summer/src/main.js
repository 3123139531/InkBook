import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from "axios";

const app = createApp(App)
app.use(ElementPlus).use(store).use(router)
app.mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.config.globalProperties.$axios = axios;
axios.defaults.baseURL="http://101.42.246.11/"
