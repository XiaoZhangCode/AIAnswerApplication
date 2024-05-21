package cn.xzhang.boot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评分策略枚举
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@AllArgsConstructor
@Getter
public enum ScoringStrategyEnum {

    CUSTOM("自定义", 0),
    AI("AI", 1),

    ;

    /**
     * 枚举值
     */
    private final String text;
    /**
     * 枚举值
     */
    private final int value;

    /**
     * 根据枚举值获取枚举
     *
     * @param value 枚举值
     * @return 枚举
     */
    public static ScoringStrategyEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (ScoringStrategyEnum anEnum : ScoringStrategyEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 获取枚举值列表
     *
     * @return 枚举值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(ScoringStrategyEnum.values()).map(ScoringStrategyEnum::getValue).collect(Collectors.toList());
    }


}
