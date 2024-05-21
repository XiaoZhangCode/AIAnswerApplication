package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerAddReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerPageReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserAnswer;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerSimpleVo;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerVo;
import cn.xzhang.boot.service.UserAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 答题记录管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 答题记录管理")
@RestController
@RequestMapping("/user-answer")
public class UserAnswerController {

    @Resource
    private UserAnswerService userAnswerService;

    /**
     * 创建答题记录
     *
     * @param userAnswerReqDTO UserAnswer添加请求数据传输对象，包含新增UserAnswer的信息
     * @return 返回操作结果，其中包含新添加UserAnswer的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建答题记录")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addUserAnswer(@RequestBody UserAnswerAddReqDTO userAnswerReqDTO) {
        if (userAnswerReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = userAnswerService.addUserAnswer(userAnswerReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新答题记录信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateUserAnswer(@RequestBody @Valid UserAnswerUpdateReqDTO userAnswerReqDTO) {
        // 检查传入的请求数据是否为空
        if (userAnswerReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = userAnswerService.updateUserAnswer(userAnswerReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除答题记录")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "答题记录ID",required = true)
    public CommonResult<Boolean> deleteUserAnswer(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = userAnswerService.deleteUserAnswer(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取答题记录")
    @Parameter(name = "id", description = "答题记录ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<UserAnswerVo> getUserAnswer(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(userAnswerService.getUserAnswerVO(userAnswerService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取答题记录简要信息")
    @Parameter(name = "id", description = "答题记录ID",required = true)
    public CommonResult<UserAnswerSimpleVo> getUserAnswerVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        UserAnswer userAnswer = userAnswerService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(userAnswerService.getSimpleUserAnswerVO(userAnswer));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取答题记录列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<UserAnswerVo>> getUserAnswerPage(UserAnswerPageReqDTO userAnswerPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(userAnswerService.getUserAnswerPage(userAnswerPageReqDTO));
    }

}