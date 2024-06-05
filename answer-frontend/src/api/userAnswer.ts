// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** 创建答题记录 POST /user-answer/add */
export async function addUserAnswer(
  body: API.UserAnswerAddReqDTO,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultLong>('/user-answer/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除答题记录 DELETE /user-answer/delete */
export async function deleteUserAnswer(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUserAnswerParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/user-answer/delete', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取答题记录 GET /user-answer/get */
export async function getUserAnswer(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserAnswerParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultUserAnswerVo>('/user-answer/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取答题记录简要信息 GET /user-answer/get/vo */
export async function getUserAnswerVo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserAnswerVOParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultUserAnswerSimpleVo>('/user-answer/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 分页获取答题记录列表 GET /user-answer/page */
export async function getUserAnswerPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserAnswerPageParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultPageResultUserAnswerVo>('/user-answer/page', {
    method: 'GET',
    params: {
      ...params,
      userAnswerPageReqDTO: undefined,
      ...params['userAnswerPageReqDTO'],
    },
    ...(options || {}),
  });
}

/** 更新答题记录信息 PUT /user-answer/update */
export async function updateUserAnswer(
  body: API.UserAnswerUpdateReqDTO,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/user-answer/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
