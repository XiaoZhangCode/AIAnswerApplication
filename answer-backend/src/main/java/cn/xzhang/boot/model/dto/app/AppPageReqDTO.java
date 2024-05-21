package cn.xzhang.boot.model.dto.app;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "应用分页请求")
@EqualsAndHashCode(callSuper = true)
public class AppPageReqDTO extends PageParam implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "应用名")
    private String appName;

    @Schema(description = "应用描述")
    private String appDesc;

    @Schema(description = "应用类型（0-得分类，1-测评类）")
    private Byte appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Byte scoringStrategy;

    @Schema(description = "审核状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;

    @Schema(description = "创建用户 id")
    private Long userId;




}
