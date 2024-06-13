// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** 创建评分结果 POST /scoring-result/add */
export async function addScoringResult(
  body: API.ScoringResultAddReqDTO,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultLong>('/scoring-result/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除评分结果 DELETE /scoring-result/delete */
export async function deleteScoringResult(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteScoringResultParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/scoring-result/delete', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取评分结果 GET /scoring-result/get */
export async function getScoringResult(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getScoringResultParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultScoringResultVo>('/scoring-result/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取评分结果简要信息 GET /scoring-result/get/vo */
export async function getScoringResultVo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getScoringResultVOParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultScoringResultSimpleVo>('/scoring-result/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 分页获取评分结果列表 GET /scoring-result/page */
export async function getScoringResultPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getScoringResultPageParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultPageResultScoringResultVo>('/scoring-result/page', {
    method: 'GET',
    params: {
      ...params,
      scoringresultPageReqDTO: undefined,
      ...params['scoringresultPageReqDTO'],
    },
    ...(options || {}),
  });
}

/** 更新评分结果信息 PUT /scoring-result/update */
export async function updateScoringResult(
  body: API.ScoringResultUpdateReqDTO,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/scoring-result/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
