package cn.xzhang.boot.model.vo.question;

import cn.hutool.json.JSONUtil;
import cn.xzhang.boot.model.dto.question.QuestionDTO;
import cn.xzhang.boot.model.entity.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.List;

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
    @Schema(description = "题目内容（json格式）", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<QuestionDTO> questionContent;

    @Schema(description = "应用 id")
    private Long appId;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    private String creator;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private java.util.Date createTime;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    private String updater;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private java.util.Date updateTime;

    @Schema(description = "是否删除")
    private String deleted;


    /**
     * 封装类转对象
     *
     * @param questionVO 题目VO
     * @return question
     */
    public static Question voToObj(QuestionVo questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<QuestionDTO> questionContentDTO = questionVO.getQuestionContent();
        question.setQuestionContent(JSONUtil.toJsonStr(questionContentDTO));
        return question;
    }

    /**
     * 对象转封装类
     *
     * @param question  question
     * @return questionVo
     */
    public static QuestionVo objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo questionVO = new QuestionVo();
        BeanUtils.copyProperties(question, questionVO);
        String questionContent = question.getQuestionContent();
        if (questionContent != null) {
            questionVO.setQuestionContent(JSONUtil.toList(questionContent, QuestionDTO.class));
        }
        return questionVO;
    }

}
