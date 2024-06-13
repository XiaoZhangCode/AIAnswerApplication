<template>
  <div id="answerResultPage">
    <a-card>
      <a-row style="margin-bottom: 16px">
        <a-col flex="auto" class="content-wrapper">
          <h2>{{ data.resultName }}</h2>
          <p>结果描述：{{ data.resultDesc }}</p>
          <!--          <p>结果 id：{{ data.resultId }}</p>-->
          <p>结果得分：{{ data.resultScore }}</p>
          <p>我的答案：{{ data.choices }}</p>
          <p>应用名称：{{ data.appName }}</p>
          <p>应用类型：{{ APP_TYPE_MAP[data.appType ?? 0] }}</p>
          <p>
            评分策略：{{ APP_SCORING_STRATEGY_MAP[data.scoringStrategy ?? 0] }}
          </p>
          <p>
            <a-space>
              答题人：
              <div :style="{ display: 'flex', alignItems: 'center' }">
                <a-avatar
                  :size="24"
                  :image-url="data.userInfo?.userAvatar"
                  :style="{ marginRight: '8px' }"
                />
                <a-typography-text
                  >{{ data.userInfo?.userName ?? "无名" }}
                </a-typography-text>
              </div>
            </a-space>
          </p>
          <p>
            答题时间：{{ dayjs(data.createTime).format("YYYY-MM-DD HH:mm:ss") }}
          </p>
          <a-space size="medium">
            <a-button type="primary" :href="`/answer/do/${data.appId}`"
              >去答题
            </a-button>
          </a-space>
        </a-col>
        <a-col flex="320px">
          <a-image width="100%" :src="data.resultPicture" />
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import { defineProps, ref, watchEffect, withDefaults } from "vue";
import API from "@/api";
import { getUserAnswerVo } from "@/api/userAnswer";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const data = ref<API.UserAnswerSimpleVo>({} as any);

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getUserAnswerVo({
    id: props.id as any,
  });
  if (res.code === 0) {
    data.value = res.data as any;
  } else {
    message.error("获取数据失败，" + res.msg);
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
#answerResultPage {
}

#answerResultPage .content-wrapper > * {
  margin-bottom: 24px;
}
</style>
