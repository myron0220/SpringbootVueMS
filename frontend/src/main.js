import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/global.css'
import locale from 'element-ui/lib/locale/lang/en'
// vue use http
import request from "@/utils/request";


Vue.use(ElementUI, {locale, size: "mini"});

Vue.config.productionTip = false

// vue use http
Vue.prototype.request=request

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

