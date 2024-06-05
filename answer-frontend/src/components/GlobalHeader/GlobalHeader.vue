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
        <div v-if="loginUserStore.loginUser.id">
          <a-avatar
            :src="loginUserStore.loginUser.userAvatar"
            style="margin-right: 8px"
          />
          {{ loginUserStore.loginUser.userName ?? "无名" }}
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
import { useLoginUserStore } from "@/store/userStore";

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
const visibleRoutes = flattenVisibleRoutes(routes);
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
</script>
