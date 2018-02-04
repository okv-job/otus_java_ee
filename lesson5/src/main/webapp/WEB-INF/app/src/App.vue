<template>
  <div id="app">
      <app-header></app-header>
      <Menu></Menu>
      <div class="content">
      <router-view></router-view>
      <help-information></help-information>
      </div>
      <app-footer></app-footer>
  </div>
</template>

<script>
import AppHeader from "./components/AppHeader.vue";
import Menu from "./components/Menu.vue";
import HelpInformation from "./components/HelpInformation.vue";
import AppFooter from "./components/AppFooter.vue";

export default {
  name: "app",
  components: { AppHeader, Menu, HelpInformation, AppFooter }
};
</script>

<style>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
  font-size: 14pt;
}

#app {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
}

body {
  background: #fafafa;
}

.content {
  display: flex;
  justify-content: center;
  flex-grow: 1;
}
</style>
