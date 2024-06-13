<template>
  <a-form
    :model="formSearchParams"
    :style="{ marginBottom: '20px' }"
    layout="inline"
    @submit="doSearch"
  >
    <a-form-item field="appId" label="应用 id">
      <a-input
        v-model="formSearchParams.appId"
        placeholder="请输入应用 id"
        allow-clear
      />
    </a-form-item>
    <a-form-item field="userId" label="用户 id">
      <a-input
        v-model="formSearchParams.userId"
        placeholder="请输入用户 id"
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
    <template #questionContent="{ record }">
      <div
        v-for="question in JSON.parse(JSON.stringify(record.questionContent))"
        :key="question.title"
      >
        {{ question }}
      </div>
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
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { deleteQuestion, getQuestionPage } from "@/api/question";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";

// 初始化搜索条件（不应该被修改）
const initSearchParams = {
  pageNo: 1,
  pageSize: 10,
};

const formSearchParams = ref<API.QuestionPageReqDTO>(
  {} as API.QuestionPageReqDTO
);

const searchParams = ref<API.QuestionPageReqDTO>({
  ...initSearchParams,
} as API.QuestionPageReqDTO);
const dataList = ref<API.QuestionVo[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await getQuestionPage({
    questionPageReqDTO: { ...searchParams.value },
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
const doDelete = async (record: API.QuestionVo) => {
  if (!record.id) {
    return;
  }

  const res = await deleteQuestion({
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
    title: "id",
    dataIndex: "id",
  },
  {
    title: "题目内容",
    dataIndex: "questionContent",
    slotName: "questionContent",
  },
  {
    title: "应用 id",
    dataIndex: "appId",
  },
  {
    title: "用户 id",
    dataIndex: "userId",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    slotName: "updateTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
</script>
