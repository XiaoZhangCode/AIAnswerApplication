package cn.xzhang.boot.model.vo.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;

/**
 * 题目VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题目VO")
public class QuestionVo implements Serializable {

    @Schema(description = "id")
    private Long id;
    @Schema(description = "题目内容（json格式）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String questionContent;

    @Schema(description = "应用 id")
    private Long appId;

    @Schema(description = "创建者",requiredMode = Schema.RequiredMode.REQUIRED)
    private String creator;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private java.util.Date createTime;

    @Schema(description = "更新者",requiredMode = Schema.RequiredMode.REQUIRED)
    private String updater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private java.util.Date updateTime;

    @Schema(description = "是否删除")
    private String deleted;

}
