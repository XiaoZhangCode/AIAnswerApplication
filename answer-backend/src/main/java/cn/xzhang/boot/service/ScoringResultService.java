package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultAddReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultPageReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultUpdateReqDTO;
import cn.xzhang.boot.model.entity.ScoringResult;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultSimpleVo;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【scoringResult(评分结果表)】的数据库操作Service
 */
public interface ScoringResultService extends IService<ScoringResult> {

    /**
     * 添加评分结果信息。
     *
     * @param scoringResultReqDTO 评分结果添加请求数据传输对象，包含要添加的评分结果的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addScoringResult(ScoringResultAddReqDTO scoringResultReqDTO);

    /**
     * 更新评分结果信息。
     *
     * @param scoringResultReqDTO 包含评分结果更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的评分结果属性。
     * @return boolean 返回true如果评分结果信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateScoringResult(ScoringResultUpdateReqDTO scoringResultReqDTO);

    /**
     * 删除评分结果
     *
     * @param id 评分结果的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteScoringResult(Long id);

    /**
     * 根据ScoringResult对象获取对应的ScoringResultVo对象。
     *
     * @param scoringResult 一个包含评分结果信息的ScoringResult对象。
     * @return 返回一个包含评分结果信息的ScoringResultVo对象。
     */
    ScoringResultSimpleVo getSimpleScoringResultVO(ScoringResult scoringResult);

    /**
     * 获取评分结果页面信息
     *
     * @param scoringResultPageReqDTO 包含分页和筛选条件的评分结果请求数据传输对象
     * @return 返回评分结果页面的结果，包括评分结果列表和分页信息
     */
    PageResult<ScoringResultVo> getScoringResultPage(ScoringResultPageReqDTO scoringResultPageReqDTO);

    /**
     * 根据ScoringResult对象获取对应的ScoringResultVo对象。
     *
     * @param scoringResult 一个包含评分结果信息的ScoringResult对象。
     * @return 返回一个包含评分结果信息的ScoringResultVo对象。
     */
    ScoringResultVo getScoringResultVO(ScoringResult scoringResult);
}
