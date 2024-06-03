package cn.xzhang.boot.model.dto.app;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 审核请求
 */
@Data
@Schema(description = "审核请求DTO")
public class ReviewRequestDTO implements Serializable {

    /**
     * id
     */
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    /**
     * 状态：0-待审核, 1-通过, 2-拒绝
     */
    @Schema(description = "状态：0-待审核, 1-通过, 2-拒绝", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    @Schema(description = "审核信息")
    private String reviewMessage;


    private static final long serialVersionUID = 1L;
}