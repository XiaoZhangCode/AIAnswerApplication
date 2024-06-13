package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.AppMapper;
import cn.xzhang.boot.mapper.ScoringResultMapper;
import cn.xzhang.boot.mapper.UserMapper;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultAddReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultPageReqDTO;
import cn.xzhang.boot.model.dto.scoringresult.ScoringResultUpdateReqDTO;
import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.entity.ScoringResult;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultSimpleVo;
import cn.xzhang.boot.model.vo.scoringresult.ScoringResultVo;
import cn.xzhang.boot.service.ScoringResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;
import static java.util.stream.Collectors.toMap;

/**
 * 针对表【scoringResult(评分结果表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class ScoringResultServiceImpl extends ServiceImpl<ScoringResultMapper, ScoringResult> implements ScoringResultService {

    @Resource
    private ScoringResultMapper scoringResultMapper;

    @Resource
    private AppMapper appMapper;

    @Resource
    private UserMapper userMapper;


    /**
     * 添加新评分结果
     *
     * @param scoringResultReqDTO 评分结果信息请求DTO
     * @return 添加成功返回评分结果id
     */
    @Override
    public long addScoringResult(ScoringResultAddReqDTO scoringResultReqDTO) {
        if (scoringResultReqDTO.getAppId() == null) {
            throw exception(BAD_REQUEST);
        }
        if (appMapper.selectCount(App::getId, scoringResultReqDTO.getAppId()) == 0) {
            throw exception(APP_NOT_EXIST);
        }
        ScoringResult scoringResult = new ScoringResult();
        BeanUtil.copyProperties(scoringResultReqDTO, scoringResult);
        if (!this.save(scoringResult)) {
            throw exception(ADD_FAIL);
        }
        return scoringResult.getId();
    }

    /**
     * 更新评分结果信息
     *
     * @param scoringResultReqDTO 评分结果信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateScoringResult(ScoringResultUpdateReqDTO scoringResultReqDTO) {
        if (scoringResultReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        // 判断应用是否存在
        if (appMapper.selectCount(App::getId, scoringResultReqDTO.getAppId()) == 0) {
            throw exception(APP_NOT_EXIST);
        }
        ScoringResult scoringResult = new ScoringResult();
        BeanUtil.copyProperties(scoringResultReqDTO, scoringResult);
        boolean b = this.updateById(scoringResult);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除评分结果
     *
     * @param id 评分结果id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteScoringResult(Long id) {
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
     * 将评分结果对象转换为评分结果VO对象
     *
     * @param scoringResult 评分结果对象
     * @return 返回评分结果VO对象
     */
    @Override
    public ScoringResultSimpleVo getSimpleScoringResultVO(ScoringResult scoringResult) {
        if (scoringResult == null) {
            return null;
        }
        ScoringResultSimpleVo scoringResultSimpleVo = new ScoringResultSimpleVo();
        BeanUtil.copyProperties(scoringResult, scoringResultSimpleVo);
        return scoringResultSimpleVo;
    }

    /**
     * 获取评分结果分页信息
     *
     * @param scoringResultPageReqDTO 评分结果分页请求DTO
     * @return 返回评分结果分页结果
     */
    @Override
    public PageResult<ScoringResultVo> getScoringResultPage(ScoringResultPageReqDTO scoringResultPageReqDTO) {
        PageResult<ScoringResult> pageResult = scoringResultMapper.selectPage(scoringResultPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<ScoringResult> list = pageResult.getList();
        if (list.isEmpty()) {
            return PageResult.empty();
        }
        // 应用名称
        Set<Long> appIds = list.stream().map(ScoringResult::getAppId).collect(Collectors.toSet());
        Map<Long, App> appMap;
        if (CollUtil.isNotEmpty(appIds)) {
            List<App> apps = appMapper.selectList(App::getId, appIds);
            appMap = apps.stream().collect(toMap(App::getId, app -> app));
        } else {
            appMap = null;
        }
        // 用户名称
        Set<String> userIdsStrs = list.stream().map(ScoringResult::getCreator).collect(Collectors.toSet());
        Map<Long, User> userMap;
        if (CollUtil.isNotEmpty(userIdsStrs)) {
            List<User> users = userMapper.selectList(User::getId, userIdsStrs);
            userMap = users.stream().collect(toMap(User::getId, user -> user));
        } else {
            userMap = null;
        }

        List<ScoringResultVo> scoringResultVos = pageResult.getList().stream().map(scoringResult -> {
            ScoringResultVo scoringResultVo = new ScoringResultVo();
            BeanUtil.copyProperties(scoringResult, scoringResultVo);
            if (appMap != null) {
                App app = appMap.get(scoringResult.getAppId());
                if (app != null) {
                    scoringResultVo.setAppName(app.getAppName());
                }
            }
            if (userMap != null) {
                User user = userMap.get(scoringResult.getCreator());
                if (user != null) {
                    scoringResultVo.setUserInfo(user);
                }
            }
            return scoringResultVo;
        }).collect(Collectors.toList());
        return new PageResult<>(scoringResultVos, pageResult.getTotal());
    }

    @Override
    public ScoringResultVo getScoringResultVO(ScoringResult scoringResult) {
        if (scoringResult == null) {
            return null;
        }
        ScoringResultVo scoringResultVo = new ScoringResultVo();
        BeanUtil.copyProperties(scoringResult, scoringResultVo);
        return scoringResultVo;
    }

}



