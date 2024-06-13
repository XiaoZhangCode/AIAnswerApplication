<template>
  <a-form
    :model="formSearchParams"
    :style="{ marginBottom: '20px' }"
    layout="inline"
    @submit="doSearch"
  >
    <a-form-item field="resultName" label="结果名称">
      <a-input
        v-model="formSearchParams.resultName"
        placeholder="请输入结果名称"
        allow-clear
      />
    </a-form-item>
    <a-form-item field="resultDesc" label="结果描述">
      <a-input
        v-model="formSearchParams.resultDesc"
        placeholder="请输入结果描述"
        allow-clear
      />
    </a-form-item>
    <a-form-item>
      <a-button type="primary" html-type="submit" style="width: 100px">
        搜索
      </a-button>
    </a-form-item>
  </a-form>
  <a-table
    :columns="columns"
    :data="dataList"
    :pagination="{
      showTotal: true,
      pageSize: searchParams.pageSize,
      current: searchParams.pageNo,
      total,
    }"
    @page-change="onPageChange"
  >
    <template #resultPicture="{ record }">
      <a-image width="64" :src="record.resultPicture" />
    </template>
    <template #appType="{ record }">
      {{ APP_TYPE_MAP[record.appType] }}
    </template>
    <template #scoringStrategy="{ record }">
      {{ APP_SCORING_STRATEGY_MAP[record.scoringStrategy] }}
    </template>
    <template #createTime="{ record }">
      {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #updateTime="{ record }">
      {{ dayjs(record.updateTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #optional="{ record }">
      <a-space>
        <a-button status="danger" @click="doDelete(record)">删除</a-button>
      </a-space>
    </template>
  </a-table>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import { deleteUserAnswer, getUserMyAnswerPage } from "@/api/userAnswer";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";

const formSearchParams = ref<API.UserAnswerPageReqDTO>(
  {} as API.UserAnswerPageReqDTO
);

// 初始化搜索条件（不应该被修改）
const initSearchParams = {
  pageNo: 1,
  pageSize: 10,
};

const searchParams = ref<API.UserAnswerPageReqDTO>({
  ...initSearchParams,
} as API.UserAnswerPageReqDTO);
const dataList = ref<API.UserAnswerVo[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await getUserMyAnswerPage({
    userAnswerPageReqDTO: {
      ...searchParams.value,
    },
  });
  if (res.code === 0) {
    dataList.value = res.data?.list || [];
    total.value = res.data?.total || 0;
  } else {
    message.error("获取数据失败，" + res.msg);
  }
};

/**
 * 执行搜索
 */
const doSearch = () => {
  searchParams.value = {
    ...initSearchParams,
    ...formSearchParams.value,
  };
};

/**
 * 当分页变化时，改变搜索条件，触发数据加载
 * @param page
 */
const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    pageNo: page,
  };
};

/**
 * 删除
 * @param record
 */
const doDelete = async (record: API.UserAnswerVo) => {
  if (!record.id) {
    return;
  }

  const res = await deleteUserAnswer({
    id: record.id,
  });
  if (res.code === 0) {
    loadData();
  } else {
    message.error("删除失败，" + res.msg);
  }
};

/**
 * 监听 searchParams 变量，改变时触发数据的重新加载
 */
watchEffect(() => {
  loadData();
});

// 表格列配置
const columns = [
  {
    title: "应用",
    dataIndex: "appName",
  },
  {
    title: "选项",
    dataIndex: "choices",
  },
  {
    title: "名称",
    dataIndex: "resultName",
  },
  {
    title: "描述",
    dataIndex: "resultDesc",
  },
  {
    title: "图片",
    dataIndex: "resultPicture",
    slotName: "resultPicture",
  },
  {
    title: "得分",
    dataIndex: "resultScore",
  },

  {
    title: "应用类型",
    dataIndex: "appType",
    slotName: "appType",
  },
  {
    title: "评分策略",
    dataIndex: "scoringStrategy",
    slotName: "scoringStrategy",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
</script>
