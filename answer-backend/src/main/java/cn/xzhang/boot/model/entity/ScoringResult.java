package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
 * 评分结果
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @TableName ScoringResult
 */
@TableName(value = "scoring_result")
@Data
@EqualsAndHashCode(callSuper = true)
public class ScoringResult extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "结果名称，如物流师")
    private String resultName;

    @Schema(description = "结果描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultDesc;

    @Schema(description = "结果图片", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultPicture;

    @Schema(description = "结果属性集合 JSON，如 [I,S,T,J]", requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultProp;

    @Schema(description = "结果得分范围，如 80，表示 80及以上的分数命中此结果", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer resultScoreRange;

    @Schema(description = "应用 id")
    private Long appId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}