package cn.xzhang.boot.model.dto.scoringresult;

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
public class ScoringResultPageReqDTO extends PageParam implements Serializable {


    @Schema(description = "id")
    private Long id;


    @NotEmpty(message = "结果名称，如物流师不能为空")
    @Schema(description = "结果名称，如物流师")
    private String resultName;


    @NotNull(message = "结果描述不能为空")
    @Schema(description = "结果描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultDesc;


    @NotNull(message = "结果图片不能为空")
    @Schema(description = "结果图片", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultPicture;


    @NotNull(message = "结果属性集合 JSON，如 [I,S,T,J]不能为空")
    @Schema(description = "结果属性集合 JSON，如 [I,S,T,J]", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultProp;


    @NotNull(message = "结果得分范围，如 80，表示 80及以上的分数命中此结果不能为空")
    @Schema(description = "结果得分范围，如 80，表示 80及以上的分数命中此结果", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer resultScoreRange;


    @Schema(description = "应用 id")
    private Long appId;


}
