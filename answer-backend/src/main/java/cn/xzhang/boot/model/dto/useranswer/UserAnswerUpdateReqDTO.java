package cn.xzhang.boot.model.dto.useranswer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 评分结果添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "评分结果添加请求")
@Valid
public class UserAnswerUpdateReqDTO extends UserAnswerBaseDTO{

    @Schema(description = "id" )
    @NotBlank(message = "id不能为空")
    private Long id;

}
