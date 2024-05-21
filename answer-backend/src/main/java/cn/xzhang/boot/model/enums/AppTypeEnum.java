package cn.xzhang.boot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用类型枚举
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@AllArgsConstructor
@Getter
public enum AppTypeEnum {

    DERIVATION("得分类", 0),
    TEST("测评类", 1),

    ;

    /**
     * 类型名称
     */
    private final String name;

    /**
     * 类型值
     */
    private final int value;

    /**
     * 根据 value 获取枚举
     *
     * @param value 类型值
     * @return 枚举
     */
    public static AppTypeEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (AppTypeEnum anEnum : AppTypeEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 获取值列表
     *
     * @return 值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(AppTypeEnum::getValue).collect(Collectors.toList());
    }


}
