import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiscussionView from '@/views/DiscussionView.vue'
import ElectionDataView from '@/views/ElectionDataView.vue'
import LoginPage from "@/views/LoginPage.vue";
import CreateDiscussionView from '@/views/CreateDiscussionView.vue'
import MunicipalityView from "@/views/MunicipalityView.vue";
import VerifyEmail from "@/views/VerifyEmail.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/discussion',
      name: 'discussion',
      component: DiscussionView,
    },
    {
      path: '/electionData',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: ElectionDataView,
    },

    {
      path: '/login',  // Verander van '/user/verify' naar '/login'
      name: 'login',   // Was 'UserVerification'
      component: LoginPage
    },

    {
      path: '/municipality-results',
      name: 'municipality-results',
      component: MunicipalityView
    },

    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterPage.vue')
    },

    {
      path: '/verify-account',
      name: 'verify-account',
      component: VerifyEmail
    },

    {
      path: '/create-discussion',
      name: 'create-discussion',
      component: () => import('../views/CreateDiscussionView.vue')
    },
    {
      path: '/discussion-details/:id',
      name: 'discussion-details',
      component: () => import('../views/DiscussionDetailsView.vue')
    },
    {
      path: '/national-results',
      name: 'national-results',
      component: () => import('../views/NationalResults.vue')
    },
  ],

})


export default router
