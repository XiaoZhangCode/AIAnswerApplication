<template>
  <a-form
    :model="formSearchParams"
    :style="{ marginBottom: '20px' }"
    layout="inline"
    @submit="doSearch"
  >
    <a-form-item field="appName" label="应用名称">
      <a-input
        v-model="formSearchParams.appName"
        placeholder="请输入应用名称"
        allow-clear
      />
    </a-form-item>
    <a-form-item field="appDesc" label="应用描述">
      <a-input
        v-model="formSearchParams.appDesc"
        placeholder="请输入应用描述"
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
    <template #appIcon="{ record }">
      <a-image width="64" :src="record.appIcon" />
    </template>
    <template #appType="{ record }">
      {{ APP_TYPE_MAP[record.appType] }}
    </template>
    <template #scoringStrategy="{ record }">
      {{ APP_SCORING_STRATEGY_MAP[record.scoringStrategy] }}
    </template>
    <template #reviewStatus="{ record }">
      {{ REVIEW_STATUS_MAP[record.reviewStatus] }}
    </template>
    <template #reviewTime="{ record }">
      {{
        record.reviewTime &&
        dayjs(record.reviewTime).format("YYYY-MM-DD HH:mm:ss")
      }}
    </template>
    <template #createTime="{ record }">
      {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #updateTime="{ record }">
      {{ dayjs(record.updateTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #optional="{ record }">
      <a-space>
        <a-button
          v-if="record.reviewStatus !== REVIEW_STATUS_ENUM.PASS"
          status="success"
          @click="doReview(record, REVIEW_STATUS_ENUM.PASS, '')"
        >
          通过
        </a-button>
        <a-button
          v-if="record.reviewStatus !== REVIEW_STATUS_ENUM.REJECT"
          status="warning"
          @click="doReview(record, REVIEW_STATUS_ENUM.REJECT, '不符合上架要求')"
        >
          拒绝
        </a-button>
        <a-button status="danger" @click="doDelete(record)">删除</a-button>
      </a-space>
    </template>
  </a-table>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import {
  APP_SCORING_STRATEGY_MAP,
  APP_TYPE_MAP,
  REVIEW_STATUS_ENUM,
  REVIEW_STATUS_MAP,
} from "@/constant/app";
import { deleteApp, doAppReview, getAppPage } from "@/api/app";

const initSearchParams = {
  pageNo: 1,
  pageSize: 10,
};
const formSearchParams = ref<API.AppPageReqDTO>({
  ...initSearchParams,
});

// 初始化搜索条件（不应该被修改）
const searchParams = ref<API.AppPageReqDTO>({
  ...initSearchParams,
});
const dataList = ref<API.AppVo[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await getAppPage({
    appPageReqDTO: { ...searchParams.value },
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
const doDelete = async (record: API.AppVo) => {
  if (!record.id) {
    return;
  }

  const res = await deleteApp({
    id: record.id,
  });
  if (res.code === 0) {
    loadData();
  } else {
    message.error("删除失败，" + res.msg);
  }
};

/**
 * 审核
 * @param record
 * @param reviewStatus
 * @param reviewMessage
 */
const doReview = async (
  record: API.AppVo,
  reviewStatus: number,
  reviewMessage?: string
) => {
  if (!record.id) {
    return;
  }

  const res = await doAppReview({
    id: record.id,
    reviewStatus,
    reviewMessage,
  });
  if (res.code === 0) {
    loadData();
  } else {
    message.error("审核失败，" + res.msg);
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
  // {
  //   title: "id",
  //   dataIndex: "id",
  // },
  {
    title: "名称",
    dataIndex: "appName",
  },
  {
    title: "描述",
    dataIndex: "appDesc",
  },
  {
    title: "图标",
    dataIndex: "appIcon",
    slotName: "appIcon",
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
    title: "审核状态",
    dataIndex: "reviewStatus",
    slotName: "reviewStatus",
  },
  // {
  //   title: "审核信息",
  //   dataIndex: "reviewMessage",
  // },
  {
    title: "审核时间",
    dataIndex: "reviewTime",
    slotName: "reviewTime",
  },
  // {
  //   title: "创建时间",
  //   dataIndex: "createTime",
  //   slotName: "createTime",
  // },
  // {
  //   title: "更新时间",
  //   dataIndex: "updateTime",
  //   slotName: "updateTime",
  // },
  {
    title: "操作",
    slotName: "optional",
  },
];
</script>
