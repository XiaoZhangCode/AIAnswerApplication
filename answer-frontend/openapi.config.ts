// eslint-disable-next-line @typescript-eslint/no-var-requires
const { generateService } = require("@umijs/openapi");

generateService({
  requestLibPath: "import request from '@/request'",
  schemaPath: "http://localhost:8101/api/v3/api-docs",
  serversPath: "./src",
  hook: {
    //@ts-ignore
    // 这个钩子用来处理openapi生成的文件名 将每个方法生成一个文件
    // customFileNames: (operationObject, apiPath) => {
    //   const operationId = operationObject.operationId;
    //   if (!operationId) {
    //     // @ts-ignore
    //     console.warn("[Warning] no operationId", apiPath);
    //     return;
    //   }
    //   const res = operationId.split("_");
    //   if (res.length > 1) {
    //     res.shift();
    //     if (res.length > 2) {
    //       // @ts-ignore
    //       console.warn("[Warning]  operationId has more than 2 part", apiPath);
    //     }
    //     return [res.join("_")];
    //   } else {
    //     const controllerName = (res || [])[0];
    //     if (controllerName) {
    //       return [controllerName];
    //     }
    //     return;
    //   }
    // },
    // 将后端@Tag(name="用户管理-User")提取出来User命名
    customFileNames: (operationObject, apiPath) => {
      const tags = operationObject.tags[0];
      if (!tags) {
        // @ts-ignore
        console.warn("[Warning] no tags", apiPath);
        return;
      }
      const res = tags.split("-");
      if (res.length > 1) {
        res.shift();
        if (res.length > 2) {
          // @ts-ignore
          console.warn("[Warning]  tags has more than 2 part", apiPath);
        }
        return [res.join("_")];
      } else {
        const controllerName = (res || [])[0];
        if (controllerName) {
          return [controllerName];
        }
        return;
      }
    },
  },
});
