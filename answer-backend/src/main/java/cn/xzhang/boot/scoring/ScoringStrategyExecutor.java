package cn.xzhang.boot.scoring;

import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.entity.UserAnswer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.APP_STRATEGY_NOT_FOUND;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 评分策略执行器
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class ScoringStrategyExecutor {


    // 策略列表
    @Resource
    private List<ScoringStrategy> scoringStrategyList;


    /**
     * 评分
     *
     * @param choiceList
     * @param app
     * @return
     * @throws Exception
     */
    public UserAnswer doScore(List<String> choiceList, App app) {
        Integer appType = app.getAppType();
        Integer appScoringStrategy = app.getScoringStrategy();
        if (appType == null || appScoringStrategy == null) {
            throw exception(APP_STRATEGY_NOT_FOUND);
        }
        // 根据注解获取策略
        for (ScoringStrategy strategy : scoringStrategyList) {
            if (strategy.getClass().isAnnotationPresent(ScoringStrategyConfig.class)) {
                ScoringStrategyConfig scoringStrategyConfig = strategy.getClass().getAnnotation(ScoringStrategyConfig.class);
                if (scoringStrategyConfig.appType() == appType && scoringStrategyConfig.scoringStrategy() == appScoringStrategy) {
                    return strategy.doScore(choiceList, app);
                }
            }
        }
        throw exception(APP_STRATEGY_NOT_FOUND);
    }

}
