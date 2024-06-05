import axios, {
  AxiosError,
  AxiosInstance,
  AxiosRequestConfig,
  AxiosResponse,
} from "axios";

const registerInterceptor = (
  instance: AxiosInstance,
  interceptors: any[],
  type: "request" | "response"
) => {
  interceptors.forEach((interceptor) => {
    const ejectableInterceptor = instance.interceptors[type].use(
      async (configOrResponse) => {
        if (Array.isArray(interceptor)) {
          if (interceptor.length === 2) {
            const { url, options } = await interceptor[0](
              // @ts-ignore
              type === "request" ? configOrResponse.url : configOrResponse,
              configOrResponse
            );
            return type === "request" ? { ...options, url } : options;
          } else {
            return interceptor[0](configOrResponse);
          }
        } else {
          return interceptor(configOrResponse);
        }
      },
      interceptor.length === 2 ? interceptor[1] : undefined
    );
    if (ejectableInterceptor) {
      // 可能需要存储ejectableInterceptor以便于后续移除，但此处示例中直接使用了
    }
  });
};

type IResponseInterceptor = <T = any>(
  response: AxiosResponse<T>
) => WithPromise<AxiosResponse<T>>;

type WithPromise<T> = T | Promise<T>;

type IRequestInterceptorAxios = (
  config: IRequestOptions
) => WithPromise<IRequestOptions>;

type IRequestInterceptorUmiRequest = (
  url: string,
  config: IRequestOptions
) => WithPromise<{ url: string; options: IRequestOptions }>;
type IErrorInterceptor = (error: Error) => Promise<Error>;

type IRequestInterceptor =
  | IRequestInterceptorAxios
  | IRequestInterceptorUmiRequest;

type IRequestInterceptorTuple =
  | [IRequestInterceptor, IErrorInterceptor]
  | [IRequestInterceptor]
  | IRequestInterceptor;

type IResponseInterceptorTuple =
  | [IResponseInterceptor, IErrorInterceptor]
  | [IResponseInterceptor]
  | IResponseInterceptor;

// request 方法 opts 参数的接口
interface IRequestOptions extends AxiosRequestConfig {
  skipErrorHandler?: boolean;
  requestInterceptors?: IRequestInterceptorTuple[];
  responseInterceptors?: IResponseInterceptorTuple[];

  [key: string]: any;
}

interface IRequestOptionsWithResponse extends IRequestOptions {
  getResponse: true;
}

interface IRequestOptionsWithoutResponse extends IRequestOptions {
  getResponse: false;
}

interface IRequest {
  <T = any>(url: string, opts: IRequestOptionsWithResponse): Promise<
    AxiosResponse<T>
  >;

  <T = any>(url: string, opts: IRequestOptionsWithoutResponse): Promise<T>;

  <T = any>(url: string, opts: IRequestOptions): Promise<T>; // getResponse 默认是 false， 因此不提供该参数时，只返回 data
  <T = any>(url: string): Promise<T>; // 不提供 opts 时，默认使用 'GET' method，并且默认返回 data
}

type RequestError = AxiosError | Error;

interface IErrorHandler {
  (error: RequestError, opts: IRequestOptions): void;
}

export interface RequestConfig<T = any> extends AxiosRequestConfig {
  errorConfig?: {
    errorHandler?: IErrorHandler;
    errorThrower?: (res: T) => void;
  };
  requestInterceptors?: IRequestInterceptorTuple[];
  responseInterceptors?: IResponseInterceptorTuple[];
}

let config: RequestConfig;
let requestInstance: AxiosInstance;

const getConfig = (): RequestConfig => {
  if (config) return config;
  return {
    baseURL: "http://localhost:8101/api",
    timeout: 10000,
    withCredentials: true,
    errorConfig: {
      errorHandler: (error: RequestError, opts: IRequestOptions) => {
        // 错误处理
        if (opts?.skipErrorHandler) {
          throw error;
        }
        throw error;
      },
      errorThrower: (res: any) => {
        // 错误处理
        throw res;
      },
    },
    responseInterceptors: [
      (res: AxiosResponse) => {
        const { data } = res;
        // 未登录
        if (data.code === 401) {
          // 不是获取用户信息接口，或者不是登录页面，则跳转到登录页面
          if (
            !res.request.responseURL.includes("user/get/login") &&
            !window.location.pathname.includes("/user/login")
          ) {
            window.location.href = `/user/login?redirect=${window.location.href}`;
          }
        }
        return res;
      },
    ],
    requestInterceptors: [
      (config: IRequestOptions) => {
        // 请求拦截器
        return config;
      },
    ],
  };
};

const getRequestInstance = (): AxiosInstance => {
  if (requestInstance) return requestInstance;

  requestInstance = axios.create(getConfig());

  const config = getConfig();
  registerInterceptor(
    requestInstance,
    config.requestInterceptors || [],
    "request"
  );
  registerInterceptor(
    requestInstance,
    config.responseInterceptors || [],
    "response"
  );
  return requestInstance;
};

const request: IRequest = async (
  url: string,
  opts: any = { method: "GET" }
) => {
  const requestInstance = getRequestInstance();
  const {
    getResponse = false,
    requestInterceptors,
    responseInterceptors,
  } = opts;

  // 动态注册请求和响应拦截器（如果有的话）
  if (requestInterceptors)
    registerInterceptor(requestInstance, requestInterceptors, "request");
  if (responseInterceptors)
    registerInterceptor(requestInstance, responseInterceptors, "response");

  try {
    const res = await requestInstance.request({ ...opts, url });
    return getResponse ? res : res.data;
    // eslint-disable-next-line no-useless-catch
  } catch (error) {
    // 统一错误处理逻辑...
    throw error;
  } finally {
    // 如果有必要，这里可以实现拦截器的移除逻辑，但需确保在所有可能的执行路径上都能正确执行
    if (requestInterceptors)
      requestInterceptors.forEach((interceptor: number | number[]) => {
        if (Array.isArray(interceptor)) {
          requestInstance.interceptors.request.eject(interceptor[0]);
        } else {
          requestInstance.interceptors.request.eject(interceptor);
        }
      });
  }
};

export default request;
