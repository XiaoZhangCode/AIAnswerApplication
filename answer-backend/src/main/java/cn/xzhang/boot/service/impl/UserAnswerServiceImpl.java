package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.UserAnswerMapper;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerAddReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerPageReqDTO;
import cn.xzhang.boot.model.dto.useranswer.UserAnswerUpdateReqDTO;
import cn.xzhang.boot.model.entity.UserAnswer;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerSimpleVo;
import cn.xzhang.boot.model.vo.useranswer.UserAnswerVo;
import cn.xzhang.boot.service.UserAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【userAnswer(答题记录表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class UserAnswerServiceImpl extends ServiceImpl<UserAnswerMapper, UserAnswer> implements UserAnswerService {

    @Resource
    private UserAnswerMapper userAnswerMapper;

    /**
     * 添加新答题记录
     *
     * @param userAnswerReqDTO 答题记录信息请求DTO
     * @return 添加成功返回答题记录id
     */
    @Override
    public long addUserAnswer(UserAnswerAddReqDTO userAnswerReqDTO) {
        UserAnswer userAnswer = new UserAnswer();
        BeanUtil.copyProperties(userAnswerReqDTO, userAnswer);
        if (!this.save(userAnswer)) {
            throw exception(ADD_FAIL);
        }
        return userAnswer.getId();
    }

    /**
     * 更新答题记录信息
     *
     * @param userAnswerReqDTO 答题记录信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateUserAnswer(UserAnswerUpdateReqDTO userAnswerReqDTO) {
        if (userAnswerReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        UserAnswer userAnswer = new UserAnswer();
        BeanUtil.copyProperties(userAnswerReqDTO, userAnswer);
        boolean b = this.updateById(userAnswer);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除答题记录
     *
     * @param id 答题记录id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteUserAnswer(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
    }

    /**
     * 将答题记录对象转换为答题记录VO对象
     *
     * @param userAnswer 答题记录对象
     * @return 返回答题记录VO对象
     */
    @Override
    public UserAnswerSimpleVo getSimpleUserAnswerVO(UserAnswer userAnswer) {
        if (userAnswer == null) {
            return null;
        }
        UserAnswerSimpleVo userAnswerSimpleVo = new UserAnswerSimpleVo();
        BeanUtil.copyProperties(userAnswer, userAnswerSimpleVo);
        return userAnswerSimpleVo;
    }

    /**
     * 获取答题记录分页信息
     *
     * @param userAnswerPageReqDTO 答题记录分页请求DTO
     * @return 返回答题记录分页结果
     */
    @Override
    public PageResult<UserAnswerVo> getUserAnswerPage(UserAnswerPageReqDTO userAnswerPageReqDTO) {
        PageResult<UserAnswer> pageResult = userAnswerMapper.selectPage(userAnswerPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<UserAnswerVo> userAnswerVos = pageResult.getList().stream().map(userAnswer -> {
            UserAnswerVo userAnswerVo = new UserAnswerVo();
            BeanUtil.copyProperties(userAnswer, userAnswerVo);
            return userAnswerVo;
        }).collect(Collectors.toList());
        return new PageResult<>(userAnswerVos, pageResult.getTotal());
    }

    @Override
    public UserAnswerVo getUserAnswerVO(UserAnswer userAnswer) {
        if (userAnswer == null) {
            return null;
        }
        UserAnswerVo userAnswerVo = new UserAnswerVo();
        BeanUtil.copyProperties(userAnswer, userAnswerVo);
        return userAnswerVo;
    }

}



