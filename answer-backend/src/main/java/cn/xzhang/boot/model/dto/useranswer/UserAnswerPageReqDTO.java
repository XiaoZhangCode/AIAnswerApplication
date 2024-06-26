package cn.xzhang.boot.model.dto.useranswer;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评分结果分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评分结果分页请求")
@EqualsAndHashCode(callSuper = true)
public class UserAnswerPageReqDTO extends PageParam implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "应用 id")
    private Long appId;

    @Schema(description = "应用类型（0-得分类，1-角色测评类）")
    private Integer appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Integer scoringStrategy;


    @NotNull(message = "用户答案（JSON 数组）不能为空")
    @Schema(description = "用户答案（JSON 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String choices;


    @NotNull(message = "评分结果 id不能为空")
    @Schema(description = "评分结果 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long resultId;


    @NotNull(message = "结果名称，如物流师不能为空")
    @Schema(description = "结果名称，如物流师",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultName;


    @NotNull(message = "结果描述不能为空")
    @Schema(description = "结果描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultDesc;


    @NotNull(message = "结果图标不能为空")
    @Schema(description = "结果图标",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultPicture;


    @NotNull(message = "得分不能为空")
    @Schema(description = "得分",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer resultScore;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long userId;






}
