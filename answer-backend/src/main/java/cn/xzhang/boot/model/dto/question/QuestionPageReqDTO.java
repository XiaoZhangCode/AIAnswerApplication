package cn.xzhang.boot.model.dto.question;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 题目分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题目分页请求")
@EqualsAndHashCode(callSuper = true)
public class QuestionPageReqDTO extends PageParam implements Serializable {

    @Schema(description = "id")
    private Long id;

    @NotNull(message = "题目内容（json格式）不能为空")
    @Schema(description = "题目内容（json格式）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String questionContent;

    @Schema(description = "应用 id")
    private Long appId;



}
