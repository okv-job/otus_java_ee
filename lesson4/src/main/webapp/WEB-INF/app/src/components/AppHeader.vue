<template>
    <header>
        <div class="logo-container">
          <img src="/static/images/logo.png" alt="Logo" srcset="" class="logo-img">
        </div>
        <span class="site-name">{{siteName}}</span>
        <span class="site-description">{{description}}</span>
        <form v-on:submit.prevent>
          <input type="text" name="search" id="search" placeholder="Поиск по сайту">
          <input type="submit" value="Поиск">
        </form>
    </header>
</template>


<script>
export default {
  data() {
    return {
      siteName: "OKV",
      description: "Время не любит, когда его тратят впустую"
    };
  }
};
</script>


<style scoped>
header {
  padding: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  background: #232323;
  color: whitesmoke;
}

.site-description {
  margin: 0 auto;
  align-self: flex-end;
  font-size: 16pt;
}

input[type="text"] {
  outline:none;
  border: none;
  padding: 5px;
  border-radius: 15px;
  font-size: 12pt;
  margin-right: 5px;
}

input[type="text"]:focus {
  border: 3px solid rgb(0, 99, 192);
}

input[type="submit"] {
  border: none;
  padding: 6px;
  font-size: 14pt;
  border-radius: 5px;
  background: rgb(0, 99, 192);
  color: whitesmoke;
  font-weight: 700;
}

input[type="submit"]:hover {
  cursor: pointer;
  background: rgb(0, 83, 161);
  color: whitesmoke;
}

header form {
  margin-right: 15px;
}

.site-name {
  margin-right: auto;
  margin-left: 10px;
  font-size: 24pt;
}

.logo-img {
  width: 40px;
  height: 40px;
}
</style>
