<template>
  <div class="home">{{ JSON.stringify(loginUserStore.loginUser) }}</div>
</template>

<script lang="ts" setup>
import { useLoginUserStore } from "@/store/userStore";

const loginUserStore = useLoginUserStore();
loginUserStore.fetchLoginUser();
</script>
