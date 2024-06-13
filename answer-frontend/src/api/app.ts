// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** 创建应用 POST /app/add */
export async function addApp(body: API.AppAddReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultLong>('/app/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除应用 DELETE /app/delete */
export async function deleteApp(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteAppParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/app/delete', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取应用 GET /app/get */
export async function getApp(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultAppVo>('/app/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取应用简要信息 GET /app/get/vo */
export async function getAppVo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppVOParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultAppSimpleVo>('/app/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 分页获取应用简要信息 GET /app/get/vo/page */
export async function getAppSimplePage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppSimplePageParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultPageResultAppSimpleVo>('/app/get/vo/page', {
    method: 'GET',
    params: {
      ...params,
      appPageReqDTO: undefined,
      ...params['appPageReqDTO'],
    },
    ...(options || {}),
  });
}

/** 分页获取应用列表 GET /app/page */
export async function getAppPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getAppPageParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultPageResultAppVo>('/app/page', {
    method: 'GET',
    params: {
      ...params,
      appPageReqDTO: undefined,
      ...params['appPageReqDTO'],
    },
    ...(options || {}),
  });
}

/** 应用审核 POST /app/review */
export async function doAppReview(body: API.ReviewRequestDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultBoolean>('/app/review', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 更新应用信息 PUT /app/update */
export async function updateApp(body: API.AppUpdateReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultBoolean>('/app/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
