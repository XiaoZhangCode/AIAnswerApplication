// @ts-ignore
/* eslint-disable */
import request from '@/request';

/** 创建用户 POST /user/add */
export async function addUser(body: API.UserAddReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultLong>('/user/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 删除用户 DELETE /user/delete */
export async function deleteUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteUserParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/user/delete', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取用户 GET /user/get */
export async function getUser(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultUserVo>('/user/get', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 获取当前登录用户 GET /user/get/login */
export async function getLoginUser(options?: { [key: string]: any }) {
  return request<API.CommonResultLoginUserVO>('/user/get/login', {
    method: 'GET',
    ...(options || {}),
  });
}

/** 获取用户 GET /user/get/vo */
export async function getUserVo(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserVOParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultUserSimpleVo>('/user/get/vo', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 用户登录 POST /user/login */
export async function userLogin(body: API.UserLoginReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultLoginUserVO>('/user/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 注销 DELETE /user/logout */
export async function logout(options?: { [key: string]: any }) {
  return request<API.CommonResultBoolean>('/user/logout', {
    method: 'DELETE',
    ...(options || {}),
  });
}

/** 分页获取用户列表 GET /user/page */
export async function getUserPage(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getUserPageParams,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultPageResultUserVo>('/user/page', {
    method: 'GET',
    params: {
      ...params,
      userPageReqDTO: undefined,
      ...params['userPageReqDTO'],
    },
    ...(options || {}),
  });
}

/** 用户注册 POST /user/register */
export async function userRegister(body: API.UserRegisterReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultLong>('/user/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 更新用户信息 PUT /user/update */
export async function updateUser(body: API.UserUpdateReqDTO, options?: { [key: string]: any }) {
  return request<API.CommonResultBoolean>('/user/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** 修改个人信息 PUT /user/update/profile */
export async function updateProfile(
  body: API.UserProfileUpdateReqDTO,
  options?: { [key: string]: any },
) {
  return request<API.CommonResultBoolean>('/user/update/profile', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
