import Vue from 'vue';
import Router from 'vue-router';
import Main from './components/contents/Main.vue';
import About from './components/contents/About.vue';
import News from './components/contents/News.vue';
import Login from './components/contents/Login.vue';


Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Main',
            component: Main
        },
        {
            path: '/about',
            name: 'About',
            component: About
        },
        {
            path: '/news',
            name: 'News',
            component: News
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        }
    ]
})