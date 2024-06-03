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
* @TableName UserAnswer
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="user_answer")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAnswer extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "应用 id")
    private Long appId;

    @Schema(description = "应用类型（0-得分类，1-角色测评类）")
    private Byte appType;

    @Schema(description = "评分策略（0-自定义，1-AI）")
    private Byte scoringStrategy;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}