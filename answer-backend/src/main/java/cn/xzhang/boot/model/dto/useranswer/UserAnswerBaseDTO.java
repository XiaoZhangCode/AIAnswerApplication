package cn.xzhang.boot.model.dto.useranswer;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * 评分结果基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "评分结果基础信息类", description = "评分结果基础信息")
public class UserAnswerBaseDTO implements Serializable {

    @Schema(description = "应用 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long appId;

    @Schema(description = "应用类型（0-得分类，1-角色测评类）")
    private Byte appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Byte scoringStrategy;

    @NotNull(message = "用户答案（JSON 数组）不能为空")
    @Schema(description = "用户答案（JSON 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> choices;

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


}
