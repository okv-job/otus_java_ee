<template>
<footer>
  <div class="contact">
    <span>Контактная информация: ул. Чудновского 6 к 2</span>
        <span>Почта: korytnikov.work@gmail.com</span>
  </div>

  <div class="contact">
    <span>&#169; okv</span>
  </div>

    <div class="contact">
    <span>Мы в соц. сетях: vk.com/javaee</span>
        <span>ok.ru/javaee instagram.com/javaee</span>
  </div>

  </footer>
</template>

<style scoped>
footer {
  width: 100%;
  background: rgb(235, 233, 233);
  height: 50px;
  align-self: flex-end;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.contact {
  display: flex;
  flex-direction: column;
}
</style>
