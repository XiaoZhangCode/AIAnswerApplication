package cn.xzhang.boot.scoring;

import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.entity.UserAnswer;

import java.util.List;

/**
 * 评分策略
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 *
 */
public interface ScoringStrategy {


    /**
     * 执行评分
     *
     * @param choices 用户答案
     * @param app 应用
     * @return 评分结果
     */
    UserAnswer doScore(List<String> choices, App app);


}
