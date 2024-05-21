package cn.xzhang.boot.model.dto.scoringresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评分结果添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "评分结果添加请求")
public class ScoringResultAddReqDTO extends ScoringResultBaseDTO{


}
