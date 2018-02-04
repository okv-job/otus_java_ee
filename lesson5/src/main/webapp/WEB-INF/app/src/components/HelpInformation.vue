<template>
  <div class="info">
  <h2>Блок бокового меню</h2>
  <hr>
  <h3>Информация о валюте:</h3>
  <ul>
    <li>AUD: {{AUD}}</li>
    <li>USD: {{USD}}</li>
    <li>UAH: {{UAH}}</li>
  </ul>
  <button class="jsoup-button" v-on:click="getJsoup">Активировать JSOUP</button>
  <ul class="answer">
    <li v-for="(item,index) in answers" v-bind:key="index">
      <a :href="item.url">{{item.title}}</a>
    </li>
  </ul>
  </div>
</template>

<script>
export default {
  created() {
    this.refreshMessage();
  },
  data: () => {
    return {
      AUD: "",
      USD: "",
      UAH: "",
      answers: []
    };
  },
  methods: {
    refreshMessage(resource) {
      this.$http.get("/getsomedata").then(response => {
        console.log(response.data);
        this.AUD = response.data.Valute.AUD.Value;
        this.USD = response.data.Valute.USD.Value;
        this.UAH = response.data.Valute.UAH.Value;
      });
    },
    getJsoup() {
      this.$http.get("/getjsoup").then(response => {
        console.log(response);
        response.body.forEach((element, id) => {
          if (id < 6) {
            this.answers.push(element);
          }
        });
      });
    }
  }
};
</script>



<style scoped>
.info {
  padding: 15px;
  width: 25%;
  margin: 20px 10px;
  display: block;
  border: 1px solid gray;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.jsoup-button {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
  background: red;
  color: white;
  font-size: 16pt;
  border: none;
  border-radius: 15px;
}

.jsoup-button:hover {
  cursor: pointer;
  font-weight: 700;
}

.answer {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
</style>
