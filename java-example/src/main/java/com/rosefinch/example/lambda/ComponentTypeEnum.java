package com.rosefinch.example.lambda;

import java.util.Arrays;
import java.util.List;

public enum ComponentTypeEnum {
    /**
     * 生效范围
     */
    EFFECT_SCOPE("effect_scope", "生效范围", false),
    /**
     * 生效目标
     */
    EFFECT_TARGET("effect_target", "生效目标", false),

    /**
     * 权益
     */
    BENEFIT("benefit", "权益", false),

    /**
     * 生效条件
     */
    BENEFIT_EVENT("benefit_event", "生效条件", false),

    /**
     * 支付方式
     */
    PAYMENT_TYPE("payment_type", "支付方式", false),

    /**
     * 物流方式
     */
    LOGISTICS_TYPE("logistics_type", "物流方式", false),

    /**
     * 排除目标
     */
    EXCLUDE_TARGET("exclude_target", "排除目标", false),
    ;
    /**
     * 筛选计算的顺序
     * 而前面几个类型之间，没有顺序要求，但是最好按照先筛选区分度高的类型，以减少后续步骤调用。
     * 对于BENEFIT_EVENT， EFFECT_TARGET，BENEFIT 有先后顺序要求，这三种类型必须按照以下顺序
     * 1. BENEFIT_EVENT 、2. EFFECT_TARGET、 3. BENEFIT 处理。同时，这三个类型必须放在所有类型过滤器的后面。
     */
    private static final List<String> CONDITION_FILTER_COMPONENT_LIST = Arrays.asList(
            EXCLUDE_TARGET.getCode(),
            EFFECT_SCOPE.getCode(),
            PAYMENT_TYPE.getCode(),
            LOGISTICS_TYPE.getCode(),
            BENEFIT_EVENT.getCode(),
            EFFECT_TARGET.getCode(),
            BENEFIT.getCode()
    );

    private final String code;
    private final String desc;
    private final boolean require;
    private List<Object> value;

    ComponentTypeEnum(final String code, final String desc, final boolean require) {
        this.code = code;
        this.desc = desc;
        this.require = require;

    }

    public String getCode() {
        return this.code;
    }

    /**
     * 根据code获取枚举
     * @param code code
     * @return 枚举
     */
    public static ComponentTypeEnum getByCode(final String code) {
        return Arrays.stream(ComponentTypeEnum.values())
                .filter(x->x.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 条件计算顺序
     * @return
     */
    public static List<String>listCondiitonFitlerComponent() {
        return CONDITION_FILTER_COMPONENT_LIST;
    }
}
