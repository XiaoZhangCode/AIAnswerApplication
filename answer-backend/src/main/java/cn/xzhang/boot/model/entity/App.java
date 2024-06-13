package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 应用
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @TableName App
 */
@TableName(value = "app")
@Data
@EqualsAndHashCode(callSuper = true)
public class App extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @NotEmpty(message = "应用名不能为空")
    @Schema(description = "应用名")
    private String appName;

    @NotNull(message = "应用描述不能为空")
    @Schema(description = "应用描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String appDesc;

    @NotNull(message = "应用图标不能为空")
    @Schema(description = "应用图标", requiredMode = Schema.RequiredMode.REQUIRED)
    private String appIcon;

    @Schema(description = "应用类型（0-得分类，1-测评类）")
    private Integer appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Integer scoringStrategy;

    @Schema(description = "审核状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;

    @Schema(description = "审核信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @Schema(description = "审核人 id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long reviewerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date reviewTime;

    @Schema(description = "创建用户 id")
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}