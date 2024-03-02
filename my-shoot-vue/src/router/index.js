import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/HomeView.vue'
import RegisterView from '../views/RegisterView.vue'
import SerchInput from '@/components/SerchInput.vue'
import Classify from '@/views/classify/Classify.vue'
import FloweDeatilView from '../views/FloweDeatilView.vue'
import PropsDeatilView from '../views/PropsDeatilView.vue'
import ProjectDeatilView from '../views/ProjectDeatilView.vue'
import NoticeView from '../views/NoticeView.vue'
import EventsView from '../views/EventsView.vue'


//子路由组件
import LoverClassify from '../views/classify/LoverClassify.vue'
import PropsClassify from '../views/classify/PropsClassify.vue'
import FriendlyClassify from '../views/classify/FriendlyClassify.vue'
import BirthdayClassify from '../views/classify/BirthdayClassify.vue'
import ProjectClassify from '../views/classify/ProjectClassify.vue'
import StuffClassify from '../views/classify/StuffClassify.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  
  {
    path: '/notice',
    name: 'notice',
    component: NoticeView
  },
  {
    path: '/events',
    name: 'events',
    component: EventsView
  },
  {
    path: '/serchinput',
    name: 'serchinpu',
    component: SerchInput
  }, {
    path: '/classify',
    name: 'classify',
    component: Classify,
    children: [
      {
        path: 'loverclassify',
        name: 'loverclassify',
        component: LoverClassify
      },
      {
        path: 'propsClassify',
        name: 'propsClassify',
        component: PropsClassify
      },
      {
        path: 'friendlyclassify',
        name: 'friendlyclassify',
        component: FriendlyClassify
      },
      {
        path: 'birthdayclassify',
        name: 'birthdayclassify',
        component: BirthdayClassify
      },
      {
        path: 'projectclassify',
        name: 'projectclassify',
        component: ProjectClassify
      },
      {
        path: 'StuffClassify',
        name: 'StuffClassify',
        component: StuffClassify
      },
    ]
  }, {
    path: '/flowerdeatil',
    name: 'flowerdeatil',
    component: FloweDeatilView
  },
  {
    path: '/propsdeatil',
    name: 'propsdeatil',
    component: PropsDeatilView
  },
  {
    path: '/projectdeatil',
    name: 'projectdeatil',
    component: ProjectDeatilView
  }
]

const router = new VueRouter({
  routes
})

export default router
