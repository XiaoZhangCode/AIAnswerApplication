package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * 题目基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "题目基础信息类", description = "题目基础信息")
public class QuestionBaseDTO implements Serializable {

    @NotNull(message = "题目内容不能为空")
    @Schema(description = "题目内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private List<QuestionDTO> questionContent;

    @Schema(description = "应用 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long appId;


}
