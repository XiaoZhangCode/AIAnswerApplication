import HomeView from "@/views/HomeView.vue";
import { RouteRecordRaw } from "vue-router";
import UserLayout from "@/layouts/UserLayout/UserLayout.vue";
import UserLoginVieW from "@/views/user/UserLoginVieW.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";
import BasicLayout from "@/layouts/BasicLayout/BasicLayout.vue";
import NoAuthView from "@/views/NoAuthView.vue";
import AdminUserView from "@/views/admin/AdminUserView.vue";
import AdminAppView from "@/views/admin/AdminAppView.vue";
import AdminQuestionView from "@/views/admin/AdminQuestionView.vue";
import AdminScoringResultView from "@/views/admin/AdminScoringResultView.vue";
import AdminUserAnswerView from "@/views/admin/AdminUserAnswerView.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import AppDetailView from "@/views/app/AppDetailView.vue";
import AddAndUpdateAppView from "@/views/app/AddAndUpdateAppView.vue";
import AddAndQuestionView from "@/views/app/AddAndQuestionView.vue";
import AddScoringResultView from "@/views/app/AddScoringResultView.vue";
import DoAnswerView from "@/views/app/DoAnswerView.vue";
import AnswerResultView from "@/views/app/AnswerResultView.vue";
import MyAnswerView from "@/views/app/MyAnswerView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: BasicLayout, // 指定基础布局
    children: [
      {
        path: "/",
        name: "主页",
        component: HomeView,
      },
      {
        path: "app/detail/:id",
        name: "应用详情",
        component: AppDetailView,
        props: true,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "noAuth",
        name: "无权限",
        component: NoAuthView,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "/answer/do/:appId",
        name: "答题",
        component: DoAnswerView,
        props: true,
        meta: {
          hideInMenu: true,
          access: ACCESS_ENUM.USER,
        },
      },
      {
        path: "/answer/result/:id",
        name: "答题结果",
        component: AnswerResultView,
        props: true,
        meta: {
          hideInMenu: true,
          access: ACCESS_ENUM.USER,
        },
      },
      {
        path: "/answer/my",
        name: "我的答题",
        component: MyAnswerView,
        meta: {
          access: ACCESS_ENUM.USER,
        },
      },
    ],
  },
  {
    path: "/admin",
    name: "管理中心",
    component: BasicLayout,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
    children: [
      {
        path: "user",
        name: "用户管理",
        component: AdminUserView,
      },
      {
        path: "app",
        name: "应用管理",
        component: AdminAppView,
      },
      {
        path: "add/app",
        name: "添加应用",
        component: AddAndUpdateAppView,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "/update/app/:id",
        name: "修改应用",
        props: true,
        component: AddAndUpdateAppView,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "/add/question/:appId",
        name: "创建题目",
        component: AddAndQuestionView,
        props: true,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "/add/scoring_result/:appId",
        name: "创建评分",
        component: AddScoringResultView,
        props: true,
        meta: {
          hideInMenu: true,
        },
      },
      {
        path: "question",
        name: "题目管理",
        component: AdminQuestionView,
      },
      {
        path: "scoringResult",
        name: "评分管理",
        component: AdminScoringResultView,
      },
      {
        path: "userAnswer",
        name: "回答管理",
        component: AdminUserAnswerView,
      },
    ],
  },
  {
    path: "/user",
    name: "用户",
    meta: {
      hideInMenu: true,
    },
    component: UserLayout,
    children: [
      {
        path: "login",
        name: "用户登录",
        component: UserLoginVieW,
      },
      {
        path: "register",
        name: "用户注册",
        component: UserRegisterView,
      },
    ],
  },
];
