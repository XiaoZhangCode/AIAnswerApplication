package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.app.AppAddReqDTO;
import cn.xzhang.boot.model.dto.app.AppPageReqDTO;
import cn.xzhang.boot.model.dto.app.AppUpdateReqDTO;
import cn.xzhang.boot.model.dto.app.ReviewRequestDTO;
import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.vo.app.AppSimpleVo;
import cn.xzhang.boot.model.vo.app.AppVo;
import cn.xzhang.boot.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;

/**
 * 应用管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "应用管理-APP")
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    /**
     * 创建App
     *
     * @param appReqDTO App添加请求数据传输对象，包含新增App的信息
     * @return 返回操作结果，其中包含新添加App的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建应用")
    @SaCheckLogin
    public CommonResult<Long> addApp(@RequestBody AppAddReqDTO appReqDTO) {
        if (appReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = appService.addApp(appReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用信息")
    @SaCheckLogin
    public CommonResult<Boolean> updateApp(@RequestBody @Valid AppUpdateReqDTO appReqDTO) {
        // 检查传入的请求数据是否为空
        if (appReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = appService.updateApp(appReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "应用ID", required = true)
    public CommonResult<Boolean> deleteApp(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = appService.deleteApp(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取应用")
    @Parameter(name = "id", description = "应用ID", required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<AppVo> getApp(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(appService.getAppVO(appService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取应用简要信息")
    @Parameter(name = "id", description = "应用ID", required = true)
    public CommonResult<AppSimpleVo> getAppVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        App app = appService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(appService.getSimpleAppVO(app));
    }

    @GetMapping("/get/vo/page")
    @Operation(summary = "分页获取应用简要信息")
    public CommonResult<PageResult<AppSimpleVo>> getAppSimplePage(AppPageReqDTO appPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(appService.getAppSimplePage(appPageReqDTO));
    }


    @GetMapping("/page")
    @Operation(summary = "分页获取应用列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<AppVo>> getAppPage(AppPageReqDTO appPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(appService.getAppPage(appPageReqDTO));
    }


    /**
     * 进行应用审核操作。
     *
     * @param reviewRequest 包含审核信息的请求体，其中含有应用ID和审核状态。
     * @return 返回一个布尔值的审核结果，成功为true，失败为false。
     */
    @PostMapping("/review")
    @Operation(summary = "应用审核")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> doAppReview(@RequestBody ReviewRequestDTO reviewRequest) {
        // 校验请求体是否为空
        if (reviewRequest == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        appService.reviewApp(reviewRequest);
        return CommonResult.success(true);
    }

}