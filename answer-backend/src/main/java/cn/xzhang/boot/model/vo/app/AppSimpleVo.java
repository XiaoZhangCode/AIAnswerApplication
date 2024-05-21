package cn.xzhang.boot.model.vo.app;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * 应用信息Vo
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "应用简要信息VO")
public class AppSimpleVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "应用名")
    private String appName;

    @Schema(description = "应用描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appDesc;

    @Schema(description = "应用图标",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appIcon;

    @Schema(description = "应用类型（0-得分类，1-测评类）")
    private Byte appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Byte scoringStrategy;

    @Schema(description = "创建用户 id")
    private Long userId;


}
