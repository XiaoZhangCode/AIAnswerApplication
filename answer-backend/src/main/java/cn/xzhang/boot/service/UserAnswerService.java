package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerAddReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerPageReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserAnswer;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerSimpleVo;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【userAnswer(答题记录表)】的数据库操作Service
 */
public interface UserAnswerService extends IService<UserAnswer> {

    /**
     * 添加答题记录信息。
     *
     * @param userAnswerReqDTO 答题记录添加请求数据传输对象，包含要添加的答题记录的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addUserAnswer(UserAnswerAddReqDTO userAnswerReqDTO);

    /**
     * 更新答题记录信息。
     *
     * @param userAnswerReqDTO 包含答题记录更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的答题记录属性。
     * @return boolean 返回true如果答题记录信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateUserAnswer(UserAnswerUpdateReqDTO userAnswerReqDTO);

    /**
     * 删除答题记录
     *
     * @param id 答题记录的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteUserAnswer(Long id);

    /**
     * 根据UserAnswer对象获取对应的UserAnswerVo对象。
     *
     * @param userAnswer 一个包含答题记录信息的UserAnswer对象。
     * @return 返回一个包含答题记录信息的UserAnswerVo对象。
     */
    UserAnswerSimpleVo getSimpleUserAnswerVO(UserAnswer userAnswer);

    /**
     * 获取答题记录页面信息
     *
     * @param userAnswerPageReqDTO 包含分页和筛选条件的答题记录请求数据传输对象
     * @return 返回答题记录页面的结果，包括答题记录列表和分页信息
     */
    PageResult<UserAnswerVo> getUserAnswerPage(UserAnswerPageReqDTO userAnswerPageReqDTO);

    /**
     * 根据UserAnswer对象获取对应的UserAnswerVo对象。
     *
     * @param userAnswer 一个包含答题记录信息的UserAnswer对象。
     * @return 返回一个包含答题记录信息的UserAnswerVo对象。
     */
    UserAnswerVo getUserAnswerVO(UserAnswer userAnswer);
}
