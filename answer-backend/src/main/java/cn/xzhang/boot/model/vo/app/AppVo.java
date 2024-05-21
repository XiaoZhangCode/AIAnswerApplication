package cn.xzhang.boot.model.vo.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;

/**
 * 应用VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "应用VO")
public class AppVo implements Serializable {

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

    @Schema(description = "审核状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;

    @Schema(description = "审核信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @Schema(description = "审核人 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long reviewerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.Date reviewTime;

    @Schema(description = "创建用户 id")
    private Long userId;



}
