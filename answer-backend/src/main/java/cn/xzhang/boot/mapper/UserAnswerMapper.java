package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerPageReqDTO;
import cn.xzhang.boot.model.entity.UserAnswer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【useranswer(评分结果表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface UserAnswerMapper extends BaseMapperPlus<UserAnswer> {

    default PageResult<UserAnswer> selectPage(UserAnswerPageReqDTO useranswerPageReqDTO) {
        return selectPage(useranswerPageReqDTO, new LambdaQueryWrapper<UserAnswer>()
                .eq(Objects.nonNull(useranswerPageReqDTO.getChoices()), UserAnswer::getChoices, useranswerPageReqDTO.getChoices())
                .eq(Objects.nonNull(useranswerPageReqDTO.getResultName()), UserAnswer::getResultName, useranswerPageReqDTO.getResultName())
                .eq(Objects.nonNull(useranswerPageReqDTO.getResultDesc()), UserAnswer::getResultDesc, useranswerPageReqDTO.getResultDesc())
                .eq(Objects.nonNull(useranswerPageReqDTO.getResultPicture()), UserAnswer::getResultPicture, useranswerPageReqDTO.getResultPicture())
                .orderByDesc(UserAnswer::getCreateTime)

        );
    }

}




