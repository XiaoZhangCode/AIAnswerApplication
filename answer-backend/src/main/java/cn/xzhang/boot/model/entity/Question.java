package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;


/**
 * 题目
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @TableName Question
 */
@TableName(value = "question")
@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseDO implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "题目内容（json格式）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String questionContent;

    @Schema(description = "应用 id")
    private Long appId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}