package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 题目基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "题目基础信息类", description = "题目基础信息")
public class QuestionBaseDTO implements Serializable {

    @NotNull(message = "题目内容（json格式）不能为空")
    @Schema(description = "题目内容（json格式）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String questionContent;

    @Schema(description = "应用 id")
    private Long appId;


}
