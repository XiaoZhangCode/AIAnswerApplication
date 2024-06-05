import { createApp } from "vue";
import ArcoVue from "@arco-design/web-vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router from "./router";
import "@arco-design/web-vue/dist/arco.css";
import "@/access";

const pinia = createPinia();

createApp(App).use(pinia).use(ArcoVue).use(router).mount("#app");
