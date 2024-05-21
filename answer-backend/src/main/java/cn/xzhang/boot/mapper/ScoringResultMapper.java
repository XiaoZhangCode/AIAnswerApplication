package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultPageReqDTO;
import cn.xzhang.boot.model.entity.ScoringResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【scoringresult(评分结果表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface ScoringResultMapper extends BaseMapperPlus<ScoringResult> {

    default PageResult<ScoringResult> selectPage(ScoringResultPageReqDTO scoringresultPageReqDTO) {
        return selectPage(scoringresultPageReqDTO, new LambdaQueryWrapper<ScoringResult>()
                .eq(Objects.nonNull(scoringresultPageReqDTO.getResultName()), ScoringResult::getResultName, scoringresultPageReqDTO.getResultName())
                .eq(Objects.nonNull(scoringresultPageReqDTO.getResultDesc()), ScoringResult::getResultDesc, scoringresultPageReqDTO.getResultDesc())
                .eq(Objects.nonNull(scoringresultPageReqDTO.getResultPicture()), ScoringResult::getResultPicture, scoringresultPageReqDTO.getResultPicture())
                .eq(Objects.nonNull(scoringresultPageReqDTO.getResultProp()), ScoringResult::getResultProp, scoringresultPageReqDTO.getResultProp())
                .orderByDesc(ScoringResult::getCreateTime)

        );
    }

}




