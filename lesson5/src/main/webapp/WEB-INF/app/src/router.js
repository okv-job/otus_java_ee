import Vue from 'vue';
import Router from 'vue-router';
import Main from './components/contents/Main.vue';
import About from './components/contents/About.vue';
import News from './components/contents/News.vue';
import Login from './components/contents/Login.vue';
import axios from 'axios';


Vue.use(Router)

const router = new Router({
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
});

router.beforeEach((to, from, next) => {
    if (to.path != from.path) {
        console.log("IN ROUTE CHANGE", to, from);
        axios({
            url: "/mark",
            data: {
                pageName: to.path
            },
            method: "post"
        }).then(resp => console.log(resp)).catch(err => console.log(err));
    }
    next();
})

export default router;