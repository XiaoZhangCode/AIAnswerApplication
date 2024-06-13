<template>
  <div id="addAppPage">
    <h2 style="margin-bottom: 32px">创建应用</h2>
    <a-form
      :model="form"
      :style="{ width: '480px' }"
      label-align="left"
      auto-label-width
      @submit="handleSubmit"
    >
      <a-form-item field="appName" label="应用名称">
        <a-input v-model="form.appName" placeholder="请输入应用名称" />
      </a-form-item>
      <a-form-item field="appDesc" label="应用描述">
        <a-input v-model="form.appDesc" placeholder="请输入应用描述" />
      </a-form-item>
      <a-form-item field="appIcon" label="应用图标">
        <PictureUploader
          biz="app_icon"
          :value="form.appIcon"
          :onChange="(value: string) => (form.appIcon = value)"
        />
      </a-form-item>
      <a-form-item field="appType" label="应用类型">
        <a-select
          v-model="form.appType"
          :style="{ width: '320px' }"
          placeholder="请选择应用类型"
        >
          <a-option
            v-for="(value, key) of APP_TYPE_MAP"
            :value="Number(key)"
            :label="value"
            :key="key"
          />
        </a-select>
      </a-form-item>
      <a-form-item field="scoringStrategy" label="评分策略">
        <a-select
          v-model="form.scoringStrategy"
          :style="{ width: '320px' }"
          placeholder="请选择评分策略"
        >
          <a-option
            v-for="(value, key) of APP_SCORING_STRATEGY_MAP"
            :value="Number(key)"
            :label="value"
            :key="key"
          />
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 120px">
          提交
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { defineProps, ref, watchEffect, withDefaults } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { addApp, updateApp, getApp } from "@/api/app";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";
import PictureUploader from "@/components/PictureUploader/PictureUploader.vue";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const router = useRouter();

const form = ref({
  appDesc: "",
  appIcon: "",
  appName: "",
  appType: "",
  scoringStrategy: "",
} as API.AppAddReqDTO);

const oldApp = ref<API.AppVo>();

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getApp({
    id: props.id as any,
  });
  if (res.code === 0 && res.data) {
    oldApp.value = res.data;
    //@ts-ignore
    form.value = res.data as API.AppVo;
  } else {
    message.error("获取数据失败，" + res.msg);
  }
};

// 获取旧数据
watchEffect(() => {
  loadData();
});

/**
 * 提交
 */
const handleSubmit = async () => {
  let res: any;
  // 如果是修改
  if (props.id) {
    res = await updateApp({
      id: props.id as any,
      ...form.value,
    });
  } else {
    // 创建
    res = await addApp(form.value);
  }
  if (res.code === 0) {
    message.success("操作成功，即将跳转到应用详情页");
    setTimeout(() => {
      router.push(`/app/detail/${props.id ?? res.data}`);
    }, 3000);
  } else {
    message.error("操作失败，" + res.msg);
  }
};
</script>
