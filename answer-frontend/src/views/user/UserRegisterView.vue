<template>
  <div id="userLoginPage">
    <h2 style="margin-bottom: 16px">用户注册</h2>
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
      <a-form-item
        field="userPassword"
        tooltip="密码不少于 8 位"
        label="确认密码"
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请再次确认密码"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 120px">
          注册
        </a-button>
        <a-button
          style="margin-left: 40px"
          @click="() => router.push({ path: '/user/login' })"
        >
          已有账号？去登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { userRegister } from "@/api/user";
import API from "@/api";

/**
 * 表单信息
 */
const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as API.UserRegisterReqDTO);

const router = useRouter();

/**
 * 提交表单
 */
const handleSubmit = async () => {
  const res = await userRegister(form);
  // 注册成功，跳转到主页
  if (res.code === 0) {
    message.success("注册之路，你轻松迈过！接下来，是星辰大海的冒险了！");
    await router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败啦，" + res.msg);
  }
};
</script>
