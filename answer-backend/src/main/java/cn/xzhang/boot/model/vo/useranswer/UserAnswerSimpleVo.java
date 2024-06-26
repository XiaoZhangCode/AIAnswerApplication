package cn.xzhang.boot.model.vo.useranswer;


import cn.xzhang.boot.model.vo.user.UserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * 评分结果信息Vo
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评分结果简要信息VO")
public class UserAnswerSimpleVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "应用 id")
    private Long appId;

    @Schema(description = "应用名称")
    private String appName;

    @Schema(description = "用户信息")
    private UserVo userInfo;

    @Schema(description = "应用类型（0-得分类，1-角色测评类）")
    private Integer appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Integer scoringStrategy;

    @Schema(description = "用户答案（JSON 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String choices;

    @Schema(description = "评分结果 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long resultId;

    @Schema(description = "结果名称，如物流师",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultName;

    @Schema(description = "结果描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultDesc;

    @Schema(description = "结果图标",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultPicture;

    @Schema(description = "得分",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer resultScore;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
