package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultAddReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultPageReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultUpdateReqDTO;
import cn.xzhang.boot.model.entity.ScoringResult;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultSimpleVo;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultVo;
import cn.xzhang.boot.service.ScoringResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 评分结果管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "评分结果-ScoringResult")
@RestController
@RequestMapping("/scoring-result")
public class ScoringResultController {

    @Resource
    private ScoringResultService scoringResultService;

    /**
     * 创建ScoringResult
     *
     * @param scoringResultReqDTO ScoringResult添加请求数据传输对象，包含新增ScoringResult的信息
     * @return 返回操作结果，其中包含新添加ScoringResult的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建评分结果")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addScoringResult(@RequestBody ScoringResultAddReqDTO scoringResultReqDTO) {
        if (scoringResultReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = scoringResultService.addScoringResult(scoringResultReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新评分结果信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateScoringResult(@RequestBody @Valid ScoringResultUpdateReqDTO scoringResultReqDTO) {
        // 检查传入的请求数据是否为空
        if (scoringResultReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = scoringResultService.updateScoringResult(scoringResultReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评分结果")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "评分结果ID", required = true)
    public CommonResult<Boolean> deleteScoringResult(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = scoringResultService.deleteScoringResult(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取评分结果")
    @Parameter(name = "id", description = "评分结果ID", required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<ScoringResultVo> getScoringResult(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(scoringResultService.getScoringResultVO(scoringResultService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取评分结果简要信息")
    @Parameter(name = "id", description = "评分结果ID", required = true)
    public CommonResult<ScoringResultSimpleVo> getScoringResultVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        ScoringResult scoringresult = scoringResultService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(scoringResultService.getSimpleScoringResultVO(scoringresult));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取评分结果列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<ScoringResultVo>> getScoringResultPage(ScoringResultPageReqDTO scoringresultPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(scoringResultService
                .getScoringResultPage(scoringresultPageReqDTO));
    }

}