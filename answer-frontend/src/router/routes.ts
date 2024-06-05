import HomeView from "@/views/HomeView.vue";
import { RouteRecordRaw } from "vue-router";
import UserLayout from "@/layouts/UserLayout/UserLayout.vue";
import UserLoginVieW from "@/views/user/UserLoginVieW.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";
import BasicLayout from "@/layouts/BasicLayout/BasicLayout.vue";
import ACCESS_ENUM from "@/access/accessEnum";
import NoAuthView from "@/views/NoAuthView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: BasicLayout, // 指定基础布局
    children: [
      {
        path: "",
        name: "home",
        component: HomeView,
      },
      {
        path: "about",
        name: "about",
        component: () => import("../views/AboutView.vue"),
      },
      {
        path: "admin",
        name: "管理页面",
        component: HomeView,
        meta: {
          access: ACCESS_ENUM.ADMIN,
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
