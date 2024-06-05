<template>
  <div class="home">
    {{ JSON.stringify(loginUserStore.loginUser) }}
    <PictureUploader biz="user_avatar" />
  </div>
</template>

<script lang="ts" setup>
import { useLoginUserStore } from "@/store/user/userStore";
import PictureUploader from "@/components/PictureUploader/PictureUploader.vue";

const loginUserStore = useLoginUserStore();
loginUserStore.fetchLoginUser();
</script>
