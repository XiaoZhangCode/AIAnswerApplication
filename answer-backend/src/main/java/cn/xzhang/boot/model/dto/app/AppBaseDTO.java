package cn.xzhang.boot.model.dto.app;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 应用基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "应用基础信息类", description = "应用基础信息")
public class AppBaseDTO implements Serializable {

    @NotEmpty(message = "应用名不能为空")
    @Schema(description = "应用名")
    private String appName;

    @NotNull(message = "应用描述不能为空")
    @Schema(description = "应用描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appDesc;

    @NotNull(message = "应用图标不能为空")
    @Schema(description = "应用图标",requiredMode = Schema.RequiredMode.REQUIRED)
    private String appIcon;

    @Schema(description = "应用类型（0-得分类，1-测评类）")
    private Byte appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Byte scoringStrategy;

//    @Schema(description = "审核状态：0-待审核, 1-通过, 2-拒绝")
//    private Integer reviewStatus;

//    @Schema(description = "审核信息",requiredMode = Schema.RequiredMode.REQUIRED)
//    private String reviewMessage;
//
//    @NotNull(message = "审核人 id不能为空")
//    @Schema(description = "审核人 id",requiredMode = Schema.RequiredMode.REQUIRED)
//    private Long reviewerId;
//
//    @NotNull(message = "审核时间不能为空")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Schema(description = "审核时间",requiredMode = Schema.RequiredMode.REQUIRED)
//    private java.util.Date reviewTime;






}
