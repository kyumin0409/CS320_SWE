// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import sui from 'semantic-ui/dist/semantic.min.css'
import VueResource from 'vue-resource'
import VueSession from 'vue-session'

Vue.config.productionTip = false

/* eslint-disable no-new */
Vue.use(sui)
Vue.use(VueResource)
Vue.use(VueSession)
Vue.prototype.ip = 'http://cff1c08e.ngrok.io/members-only'

Vue.http.headers.common['Access-Control-Allow-Origin'] = '*';

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
