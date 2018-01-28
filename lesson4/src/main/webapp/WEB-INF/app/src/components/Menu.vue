<template>
<nav>
    <ul>
        <router-link v-for="(item, index) in menu" v-bind:key="index" 
        :to="item.id" tag="li">
        {{item.name}}
        </router-link>
    </ul>
</nav>
</template>


<script>
export default {
  data() {
    return {
      menu: [
        { id: "/", name: "главная" },
        { id: "/news", name: "новости" },
        { id: "/about", name: "О нас" },
        { id: "/login", name: "личный кабинет" }
      ]
    };
  }
};
</script>


<style scoped>
nav {
  width: 100%;
  background: rgb(179, 179, 179);
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 16pt;
  margin: 5px auto;
  padding: 0;
}

nav > ul {
  flex-wrap: wrap;
  display: flex;
  justify-content: space-around;
  text-decoration: none;
  list-style-type: none;
  align-self: stretch;
  margin: 0;
  padding: 0;
}

nav > ul > li {
  align-self: stretch;
  display: flex;
  align-items: center;
  padding: 0 40px;
  list-style-type: none;
  min-height: 40px;
}

nav > ul > li:not(:first-child) {
  border-right: 1px solid black;
}

nav > ul > li:first-child {
  border-left: 1px solid black;
  border-right: 1px solid black;
}

nav > ul > li:hover {
  cursor: pointer;
  background: rgb(148, 148, 148);
}
</style>