<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        @menu-item-click="doMenuClick"
        :selected-keys="selectedKeys"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="title-bar">
            <img class="logo" src="../../assets/logo.png" alt="logo" />
            <div class="title">问知空间</div>
          </div>
        </a-menu-item>
        <a-menu-item
          v-for="item in visibleRoutes"
          :key="item.path"
          mode="horizontal"
        >
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <a-col flex="100px">
        <div
          v-if="loginUserStore.loginUser.id"
          style="
            display: flex;
            justify-content: space-between;
            vertical-align: center;
          "
        >
          <a-avatar
            :src="loginUserStore.loginUser.userAvatar"
            style="cursor: pointer"
          />
          <a-dropdown trigger="hover">
            <a-button
              style="
                background-color: transparent;
                margin-right: 50px;
                font-size: 16px;
                margin-top: 5px;
              "
            >
              {{ loginUserStore.loginUser.userName ?? "无名" }}
            </a-button>
            <template #content>
              <a-doption>个人信息</a-doption>
              <a-doption @click="logoutHandler">退出登录</a-doption>
            </template>
          </a-dropdown>
        </div>
        <div v-else>
          <a-button type="primary" href="/user/login">登录</a-button>
        </div>
      </a-col>
    </a-col>
  </a-row>
</template>

<style scoped>
.title-bar {
  display: flex;
  align-items: center;
}

.title {
  margin-left: 16px;
  color: black;
}

.logo {
  height: 48px;
}
</style>
<script setup lang="ts">
import { routes } from "@/router/routes";
import { RouteRecordRaw, useRouter } from "vue-router";

import { ref } from "vue";
import { useLoginUserStore } from "@/store/user/userStore";
import checkAccess from "@/access/checkAccess";
import { logout } from "@/api/user";

const loginUserStore = useLoginUserStore();
loginUserStore.fetchLoginUser();

const router = useRouter();

function flattenVisibleRoutes(routes: RouteRecordRaw[]): RouteRecordRaw[] {
  let flatRoutes: RouteRecordRaw[] = [];

  function processRoute(route: RouteRecordRaw, parentPath = "") {
    if (!route.meta?.hideInMenu) {
      // 构建新的路径，如果有必要根据父路径进行调整
      const newPath = parentPath + (parentPath ? "" : "") + route.path;

      // 创建一个新路由记录，确保路径正确，并移除children属性以实现扁平化
      const flatRoute = {
        ...route,
        path: newPath,
        children: undefined,
      } as RouteRecordRaw;
      flatRoutes.push(flatRoute);
      // 递归处理子路由，传递当前路径作为父路径
      if (route.children) {
        route.children.forEach((child) => processRoute(child, newPath));
      }
    }
  }

  routes.forEach((route) => processRoute(route));

  return flatRoutes;
}

// 展示在菜单的路由数组
const visibleRoutes = flattenVisibleRoutes(routes).filter((item) => {
  // 根据权限过滤菜单
  return checkAccess(loginUserStore.loginUser, item.meta?.access as string);
});

// 路由跳转事件
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};

// Tab 栏选中菜单项
const selectedKeys = ref([router.currentRoute.value.path]);
// 路由跳转后，更新选中的菜单项
router.afterEach((to) => {
  selectedKeys.value = [to.path];
});

// 注销
const logoutHandler = async () => {
  await logout();
  await router.push("/user/login");
};
</script>
