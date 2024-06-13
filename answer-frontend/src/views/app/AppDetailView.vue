<template>
  <div id="appDetailPage">
    <a-card>
      <a-row style="margin-bottom: 16px">
        <a-col flex="auto" class="content-wrapper">
          <div>{{ data.appName }}</div>
          <p>{{ data.appDesc }}</p>
          <p>应用类型：{{ APP_TYPE_MAP[data.appType ?? 0] }}</p>
          <p>
            评分策略：{{ APP_SCORING_STRATEGY_MAP[data.scoringStrategy ?? 0] }}
          </p>
          <p>
            <a-space>
              作者：
              <div :style="{ display: 'flex', alignItems: 'center' }">
                <a-avatar
                  :size="24"
                  :image-url="data.user?.userAvatar"
                  :style="{ marginRight: '8px' }"
                />
                <a-typography-text
                  >{{ data.user?.userName ?? "无名" }}
                </a-typography-text>
              </div>
            </a-space>
          </p>
          <p>
            创建时间：{{ dayjs(data.createTime).format("YYYY-MM-DD HH:mm:ss") }}
          </p>
          <a-space size="medium">
            <a-button type="primary" :href="`/answer/do/${id}`"
              >开始答题
            </a-button>
            <a-button>分享应用</a-button>
            <a-button v-if="isMy" :href="`/add/question/${id}`"
              >设置题目
            </a-button>
            <a-button v-if="isMy" :href="`/add/scoring_result/${id}`"
              >设置评分
            </a-button>
            <a-button v-if="isMy" :href="`/update/app/${id}`"
              >修改应用</a-button
            >
          </a-space>
        </a-col>
        <a-col flex="320px">
          <a-image width="100%" :src="data.appIcon" />
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { computed, defineProps, ref, watchEffect, withDefaults } from "vue";
import { getAppVo } from "@/api/app";
import API from "@/api";
import { useRouter } from "vue-router";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { useLoginUserStore } from "@/store/user/userStore";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const router = useRouter();

const data = ref<API.AppSimpleVo>({
  id: undefined,
  appName: undefined,
  appDesc: "",
  appIcon: "",
  appType: undefined,
  scoringStrategy: undefined,
  userId: undefined,
  createTime: undefined,
  user: {} as API.UserVo, // 确保UserVo也有相应的初始化
} as API.AppSimpleVo);

// 获取登录用户
const loginUserStore = useLoginUserStore();
let loginUserId = loginUserStore.loginUser?.id;
if (!loginUserId) {
  router.push(`/user/login?redirect=${router.currentRoute.value.fullPath}`);
}
// 是否为本人创建
const isMy = computed(() => {
  return loginUserId && loginUserId === data.value.userId;
});

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getAppVo({
    //@ts-ignore
    id: props.id as string,
  });
  if (res.code === 0) {
    data.value = res.data ? res.data : ({} as API.AppSimpleVo);
  } else {
    // Handle the case where res.data is undefined, e.g., log an error or set a default value
    console.error("Failed to fetch app data:", res);
    data.value = {} as API.AppSimpleVo; // Set a default empty object with the correct type
  }
};

/**
 * 监听 searchParams 变量，改变时触发数据的重新加载
 */
watchEffect(() => {
  loadData();
});
</script>

<style scoped>
#appDetailPage .content-wrapper > * {
  margin-bottom: 24px;
}
</style>
