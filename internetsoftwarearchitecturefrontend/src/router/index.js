import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/login',
        name: 'Login',
        component: () =>
            import ('../views/Login.vue')
    },
    {
        path: '/registerClient',
        name: 'RegisterClient',
        component: () =>
            import ('../views/RegisterClient.vue')
    },
    {
        path: '/confirmRegistration/:id',
        name: 'ConfirmRegistration',
        component: () => import('../views/ConfirmRegistration.vue')
    },
    {
        path: '/registerCottageOwner',
        name: 'RegisterCottageOwner',
        component: () =>
            import ('../views/RegisterCottageOwner.vue')
    },
    {
        path: '/registerShipOwner',
        name: 'RegisterShipOwner',
        component: () =>
            import ('../views/RegisterShipOwner.vue')
    },
    {
        path: '/registerFishingInstructor',
        name: 'RegisterFishingInstructor',
        component: () =>
            import ('../views/RegisterFishingInstructor.vue')
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router