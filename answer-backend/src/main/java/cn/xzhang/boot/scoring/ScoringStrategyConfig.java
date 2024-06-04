package cn.xzhang.boot.scoring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 评分策略注解
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ScoringStrategyConfig {

    /**
     * 应用类型
     */
    int appType();

    /**
     * 评分策略
     */
    int scoringStrategy();
}
