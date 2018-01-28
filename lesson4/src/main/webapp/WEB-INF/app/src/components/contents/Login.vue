<template>
  <div class="content"><h1>Login</h1>
  <hr>
  <form v-on:submit.prevent="validate">
    <input v-model="login" type="text" name="login" id="login" placeholder="Логин">
    <input v-model="password" type="password" name="password" id="password" placeholder="Пароль">
    <input type="submit" value="Войти">
  </form>
</div>
</template>

<script>
export default {
  data: () => {
    return {
      login: "",
      password: ""
    };
  },
  methods: {
    validate: function() {
      if (this.login.length < 5) {
        alert("Логин должен быть больше 5 символов");
        return;
      }
      if (this.password.length < 5 || this.password.length > 15) {
        alert("Пароль должен быть не менее 5 и не более 15 символов");
        return;
      }
      alert("Вы успешно вошли!");
    }
  }
};
</script>


<style scoped>
.content {
  padding: 15px;
  display: block;
  flex-grow: 3;
  margin: 20px 10px;
  border: 1px solid gray;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.content h1 {
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

input[type="text"],
input[type="password"] {
  margin: 10px;
  width: 20%;
  padding: 8px;
  font-size: 12pt;
  border-radius: 7px;
  outline: none;
}

input[type="text"]:focus,
input[type="password"]:focus {
  border: 2px solid rgb(0, 99, 192);
  font-size: 14pt;
}

input[type="submit"] {
  border: none;
  padding: 10px 15px;
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
</style>
