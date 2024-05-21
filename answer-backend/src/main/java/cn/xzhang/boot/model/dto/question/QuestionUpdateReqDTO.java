package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 题目添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "题目添加请求")
@Valid
public class QuestionUpdateReqDTO extends QuestionBaseDTO{

    @Schema(description = "id" )
    @NotBlank(message = "id不能为空")
    private Long id;

}
