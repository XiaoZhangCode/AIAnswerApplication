package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

/**
 * 题目信息
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "题目信息")
public class QuestionDTO {

    @Schema(description = "题目标题")
    private String title;

    @Schema(description = "题目选项列表")
    private List<Option> options;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "题目选项")
    public static class Option {
        @Schema(description = "选项对应的结果")
        private String result;
        @Schema(description = "选项分数")
        private int score;
        @Schema(description = "选项的值")
        private String value;
        @Schema(description = "选项 A B C")
        private String key;
    }
}