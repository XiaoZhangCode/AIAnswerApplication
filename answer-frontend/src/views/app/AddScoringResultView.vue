<template>
  <div id="addScoringResultPage">
    <h2 style="margin-bottom: 32px">创建评分结果</h2>
    <a-form
      :model="form"
      :style="{ width: '480px' }"
      label-align="left"
      auto-label-width
      @submit="handleSubmit"
    >
      <a-form-item field="appName" label="应用名称" disabled>
        {{ appInfo?.appName }}
      </a-form-item>
      <a-form-item field="resultName" label="结果名称">
        <a-input v-model="form.resultName" placeholder="请输入结果名称" />
      </a-form-item>
      <a-form-item field="resultDesc" label="结果描述">
        <a-input v-model="form.resultDesc" placeholder="请输入结果描述" />
      </a-form-item>
      <a-form-item field="resultPicture" label="结果图标">
        <PictureUploader
          biz="app_icon"
          :value="form.resultPicture"
          :onChange="(value: string) => (form.resultPicture = value)"
        />
      </a-form-item>
      <a-form-item
        field="resultProp"
        label="结果集"
        v-if="appInfo?.appType == 1"
      >
        <a-input-tag
          v-model="form.resultProp"
          :style="{ width: '320px' }"
          placeholder="输入结果集"
          allow-clear
        />
      </a-form-item>
      <a-form-item field="resultScoreRange" label="结果得分范围" v-else>
        <a-input-number
          v-model="form.resultScoreRange"
          placeholder="请输入结果得分范围"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 120px">
          提交
        </a-button>
      </a-form-item>
    </a-form>
    <h2 style="margin-bottom: 32px">评分管理</h2>
    <ScoringResultTable :appId="appId" :doUpdate="doUpdate" ref="tableRef" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { addScoringResult, updateScoringResult } from "@/api/scoringResult";
import { getAppVo } from "@/api/app";
import { defineProps, withDefaults } from "vue/dist/vue";
import ScoringResultTable from "@/views/app/components/ScoringResultTable.vue";
import PictureUploader from "@/components/PictureUploader/PictureUploader.vue";

interface Props {
  appId: string;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => "",
});
// 获取app信息
const appInfo = ref<API.AppSimpleVo>();
onMounted(async () => {
  if (props.appId) {
    // 获取应用信息
    const res = await getAppVo({ id: props.appId as any });
    appInfo.value = res.data;
  }
});

// 声明 ref
const tableRef = ref();
// 调用
if (tableRef.value) {
  tableRef.value.loadData();
}

const form = reactive({
  resultDesc: "",
  resultName: "",
  resultPicture: "",
  resultProp: "",
} as API.ScoringResultVo);

const updateId = ref<any>();

/**
 * 修改
 * @param scoringResult
 */
const doUpdate = (scoringResult: API.ScoringResultVo) => {
  updateId.value = scoringResult.id;
  form.resultDesc = scoringResult.resultDesc;
  form.resultName = scoringResult.resultName;
  form.resultPicture = scoringResult.resultPicture;
  form.resultProp = scoringResult.resultProp;
  form.resultScoreRange = scoringResult.resultScoreRange;
};

/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId) {
    return;
  }
  let res;
  // 区分创建和修改
  if (updateId.value) {
    console.log(updateId.value);
    res = await updateScoringResult({
      id: updateId.value,
      ...form,
    } as API.ScoringResultUpdateReqDTO);
  } else {
    res = await addScoringResult({
      appId: props.appId as any,
      ...form,
    } as API.ScoringResultAddReqDTO);
  }
  if (res.code === 0) {
    message.success("操作成功");
  } else {
    message.error("操作失败，" + res.msg);
  }
  if (tableRef.value) {
    await tableRef.value.loadData();
    updateId.value = undefined;
  }
};
</script>
