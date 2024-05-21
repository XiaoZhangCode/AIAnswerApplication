package cn.xzhang.boot.model.vo.scoringresult;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

/**
 * 评分结果信息Vo
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评分结果简要信息VO")
public class ScoringResultSimpleVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "结果名称，如物流师")
    private String resultName;

    @Schema(description = "结果描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultDesc;

    @Schema(description = "结果图片",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultPicture;

    @Schema(description = "结果属性集合 JSON，如 [I,S,T,J]",requiredMode = Schema.RequiredMode.REQUIRED)
    private String resultProp;

    @Schema(description = "结果得分范围，如 80，表示 80及以上的分数命中此结果",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer resultScoreRange;

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
