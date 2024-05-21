package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.question.QuestionAddReqDTO;
import cn.xzhang.boot.model.dto.question.QuestionPageReqDTO;
import cn.xzhang.boot.model.dto.question.QuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.vo.question.QuestionSimpleVo;
import cn.xzhang.boot.model.vo.question.QuestionVo;
import cn.xzhang.boot.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 题目管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 题目管理")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 创建Question
     *
     * @param questionReqDTO Question添加请求数据传输对象，包含新增Question的信息
     * @return 返回操作结果，其中包含新添加Question的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建题目")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addQuestion(@RequestBody QuestionAddReqDTO questionReqDTO) {
        if (questionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionService.addQuestion(questionReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新题目信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateQuestion(@RequestBody @Valid QuestionUpdateReqDTO questionReqDTO) {
        // 检查传入的请求数据是否为空
        if (questionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = questionService.updateQuestion(questionReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "题目ID",required = true)
    public CommonResult<Boolean> deleteQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = questionService.deleteQuestion(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取题目")
    @Parameter(name = "id", description = "题目ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<QuestionVo> getQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionService.getQuestionVO(questionService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取题目简要信息")
    @Parameter(name = "id", description = "题目ID",required = true)
    public CommonResult<QuestionSimpleVo> getQuestionVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        Question question = questionService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionService.getSimpleQuestionVO(question));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取题目列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<QuestionVo>> getQuestionPage(QuestionPageReqDTO questionPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(questionService.getQuestionPage(questionPageReqDTO));
    }

}