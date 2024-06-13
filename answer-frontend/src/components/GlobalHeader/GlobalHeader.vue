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
        <template v-for="item in visibleRoutes">
          <template v-if="item.children">
            <a-sub-menu :key="item.path">
              <template #title>{{ item.name }}</template>
              <a-menu-item v-for="child in item.children" :key="child.path">
                {{ child.name }}
              </a-menu-item>
            </a-sub-menu>
          </template>
          <a-menu-item v-else :key="item.path">{{ item.name }}</a-menu-item>
        </template>
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
#globalHeader {
  box-sizing: border-box;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  background: linear-gradient(to right, #fefefe, #fff);
}

.title-bar {
  display: flex;
  align-items: center;
}

.title {
  margin-left: 16px;
  color: black;
  font-size: 20px;
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

function flattenVisibleRoutes(
  routes: RouteRecordRaw[],
  parentPath = ""
): any[] {
  let flatRoutes: any[] = [];

  routes.forEach((route) => {
    if (!route.meta?.hideInMenu) {
      const parts = [parentPath, route.path].filter(Boolean); // 移除可能的空字符串
      const newPath = parts.join("/").replace(/\/+/g, "/");
      const menuItem = {
        ...route,
        path: newPath,
        children: route.children
          ? flattenVisibleRoutes(route.children, newPath)
          : undefined,
      };
      if (route.name) {
        flatRoutes.push(menuItem);
      } else {
        let routes1 = flattenVisibleRoutes(
          route.children as RouteRecordRaw[],
          newPath
        );
        flatRoutes.push(...routes1);
      }
    }
  });

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
