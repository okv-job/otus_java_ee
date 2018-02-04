<template>
  <div class="content"><h1>Login <span v-if="isLoggedIn">Добро пожаловать {{firstName}} <button @click="logout">Выйти</button></span></h1>
  <hr>
  <form v-on:submit.prevent="validate" id="loginForm" v-if="!isLoggedIn">
    <input v-model="login" type="text" name="login" id="login" placeholder="Логин">
    <input v-model="password" type="password" name="password" id="password" placeholder="Пароль">
    <input type="submit" value="Войти">
  </form>
  <div v-else>
    <ul>
      <li v-for="user in users" :key="user.id" @click="openUser(user)" class="user">{{user.firstName}} {{user.secondName}} - {{user.address}}</li>
    </ul>
    <button @click="addNewUser()">Добавить нового пользователя</button>
  </div>
  <div class="userDescription cloak" ref="userDescription">
    <span class="close" @click="close">&times;</span>
    <input type="text" v-model="selectedUser.firstName" name="firstName">
    <input type="text" v-model="selectedUser.secondName" name="secondName">
    <input type="text" v-model="selectedUser.address" name="address">
    <button v-if="isAddNewUser" @click="updateUser()">Добавить пользователя</button>
    <div v-else>
      <button @click="updateUser()">Обновить</button>
      <button @click="removeUser()">Удалить пользователя</button>
</div>
  </div>
</div>
</template>

<script>
import axios from "axios";
export default {
  data: () => {
    return {
      login: "",
      password: "",
      isLoggedIn: false,
      firstName: "",
      users: [],
      isAddNewUser: false,
      selectedUser: {
        firstName: "",
        secondName: "",
        selectedUser: "",
        address: ""
      }
    };
  },
  created() {
    this.updateInformation();
  },
  methods: {
    updateInformation: function() {
      axios({
        method: "GET",
        url: "/login"
      })
        .then(response => {
          console.log(response);
          if (response.data.result) {
            this.isLoggedIn = true;
            this.firstName = response.data.firstName;
            this.users = response.data.users;
          } else {
            this.isLoggedIn = false;
            this.firstName = "";
            this.users = [];
          }
        })
        .catch(err => {
          console.log(err);
        });
    },

    close: function() {
      this.$refs.userDescription.classList.add("cloak");
      this.selectedUser.firstName = "";
      this.selectedUser.secondName = "";
      this.selectedUser.address = "";
      this.selectedUser.id = "";
      this.isAddNewUser = false;
    },
    addNewUser: function() {
      this.$refs.userDescription.classList.remove("cloak");
      this.isAddNewUser = true;
    },
    openUser: function(user) {
      this.$refs.userDescription.classList.remove("cloak");
      this.selectedUser.firstName = user.firstName;
      this.selectedUser.secondName = user.secondName;
      this.selectedUser.address = user.address;
      this.selectedUser.id = user.id;
    },
    updateUser: function() {
      this.$refs.userDescription.classList.add("cloak");
      if (this.isAddNewUser) {
        let formData = new FormData();
        formData.append("firstName", this.selectedUser.firstName);
        formData.append("secondName", this.selectedUser.secondName);
        formData.append("address", this.selectedUser.address);

        axios({
          method: "POST",
          url: "/api/users",
          data: formData
        })
          .then(response => {
            console.log(response);
            alert("Пользователь успешно добавлен!");
            this.close();
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        let formData = new FormData();
        formData.append("firstName", this.selectedUser.firstName);
        formData.append("secondName", this.selectedUser.secondName);
        formData.append("address", this.selectedUser.address);
        formData.append("id", this.selectedUser.id);

        axios({
          method: "PUT",
          url: "/api/users",
          data: formData
        })
          .then(response => {
            console.log(response);
            alert("Пользователь успешно обновлен!");
            this.close();
          })
          .catch(err => {
            console.log(err);
          });
      }
      this.isAddNewUser = false;
      this.updateInformation();
    },
    removeUser: function() {
      this.$refs.userDescription.classList.add("cloak");
      this.isAddNewUser = false;
      let formData = new FormData();
      formData.append("id", this.selectedUser.id);

      axios({
        method: "DELETE",
        url: "/api/users",
        data: formData
      })
        .then(response => {
          console.log(response);
          alert("Пользователь успешно удален!");
          this.close();
        })
        .catch(err => {
          console.log(err);
        });
      this.$refs.userDescription.classList.add("cloak");
      this.updateInformation();
    },

    validate: function() {
      if (this.login.length < 3) {
        alert("Логин должен быть больше 3 символов");
        return;
      }
      if (this.password.length < 3 || this.password.length > 15) {
        alert("Пароль должен быть не менее 3 и не более 15 символов");
        return;
      }
      let formData = new FormData();
      formData.append("login", this.login);
      formData.append("password", this.password);
      console.log(formData);
      axios({
        method: "POST",
        url: "/login",
        data: formData
      })
        .then(response => {
          console.log(response);
          if (response.data.result) {
            this.isLoggedIn = true;
            this.firstName = response.data.firstName;
            this.users = response.data.users;
          } else {
            alert("Неверный логин или пароль!");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    logout: function() {
      axios({
        method: "POST",
        url: "/logout"
      })
        .then(response => {
          console.log(response);
          if (response.data.result) {
            this.isLoggedIn = false;
            this.firstName = "";
            this.users = [];
            alert("Вы успешно вышли");
          } else {
            alert("Ошибка сервера");
          }
        })
        .catch(err => {
          console.log(err);
        });
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

.user:hover {
  cursor: pointer;
  color: aquamarine;
}

.userDescription {
  display: flex;
  position: absolute;
  top: 25%;
  left: 25%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: white;
  width: 50%;
  height: 50%;
}

.cloak {
  display: none;
}

.close {
  position: absolute;
  font-size: 24pt;
  top: 2%;
  right: 2%;
}

.close:hover {
  cursor: pointer;
  color: red;
}
</style>
