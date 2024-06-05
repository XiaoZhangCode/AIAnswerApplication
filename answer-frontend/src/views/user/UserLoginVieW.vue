<template>
  <div id="userLoginPage">
    <h2 style="margin-bottom: 16px">用户登录</h2>
    <a-form
      style="max-width: 480px; margin: 0 auto"
      label-align="left"
      auto-label-width
      :model="form"
      @submit="handleSubmit"
    >
      <a-form-item field="userAccount" label="账号">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item field="userPassword" tooltip="密码不少于 8 位" label="密码">
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 120px">
          登录
        </a-button>
        <a-button
          style="margin-left: 40px"
          @click="() => router.push('/user/register')"
        >
          还没账号？速来报道！
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { useLoginUserStore } from "@/store/user/userStore";
import { userLogin } from "@/api/user";
import API from "@/api";

/**
 * 表单信息
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
} as API.UserLoginReqDTO);

const router = useRouter();
const loginUserStore = useLoginUserStore();

/**
 * 提交表单
 */
const handleSubmit = async () => {
  const res = await userLogin(form);
  // 登录成功，跳转到主页
  if (res.code === 0) {
    await loginUserStore.fetchLoginUser();
    message.success("登录成功");
    await router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登录失败，" + res.msg);
  }
};
</script>
