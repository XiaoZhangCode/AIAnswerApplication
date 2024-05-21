package cn.xzhang.boot.model.enums;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 审核状态枚举
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@AllArgsConstructor
@Getter
public enum ReviewStatusEnum {

    REVIEWING("待审核", 0),
    PASS("通过", 1),
    REJECT("拒绝", 2);

    private final String text;

    private final int value;

    /**
     * 根据给定的整数值获取对应的ReviewStatusEnum枚举实例。
     *
     * @param value 整数值，用于查找对应的枚举实例。如果值为空，则返回null。
     * @return ReviewStatusEnum的实例，如果找不到匹配的枚举值则返回null。
     */
    public static ReviewStatusEnum getEnumByValue(Integer value) {
        // 检查值是否为空
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        // 遍历所有枚举实例，查找值匹配的实例
        for (ReviewStatusEnum anEnum : ReviewStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        // 如果没有找到匹配的枚举实例，则返回null
        return null;
    }

    /**
     * 获取所有ReviewStatusEnum枚举实例的值列表。
     *
     * @return 包含所有枚举实例值的整数列表。
     */
    public static List<Integer> getValues() {
        // 通过流处理枚举实例，提取值并收集到列表中
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

}