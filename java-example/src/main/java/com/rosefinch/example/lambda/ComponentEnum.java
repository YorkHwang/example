package com.rosefinch.example.lambda;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum ComponentEnum {

    /** 生效范围 */
    APP_SCOPE("app_scope", "平台范围", ComponentTypeEnum.EFFECT_SCOPE, -1),
    STORE_SCOPE("store_scope", "店铺范围", ComponentTypeEnum.EFFECT_SCOPE, 0),
    CATEGORY_SCOPE("category_scope", "指定分类", ComponentTypeEnum.EFFECT_SCOPE, 1),
    PRODUCT_SCOPE("product_scope", "指定商品", ComponentTypeEnum.EFFECT_SCOPE, 2),
    TAG_SCOPE("tag_scope", "指定标签", ComponentTypeEnum.EFFECT_SCOPE, 3),
    ORDER_SCOPE("order_scope", "订单范围", ComponentTypeEnum.EFFECT_SCOPE, 4),

    /** 生效目标 */
    STORE_TARGET("store_target", "指定店铺", ComponentTypeEnum.EFFECT_TARGET, 0),
    CATEGORY_TARGET("category_target", "指定分类", ComponentTypeEnum.EFFECT_TARGET, 1),
    PRODUCT_TARGET("product_target", "指定商品", ComponentTypeEnum.EFFECT_TARGET, 2),
    LABEL_TARGET("label_target", "指定标签", ComponentTypeEnum.EFFECT_TARGET, 3),

    /** 权益 */
    MINUS_BENEFIT("minus_benefit", "减金额", ComponentTypeEnum.BENEFIT, 1),
    DISCOUNT_BENEFIT("discount_benefit", "打折", ComponentTypeEnum.BENEFIT, 2),
    FREE_SHIPPING_BENEFIT("free_shipping_benefit", "免运费", ComponentTypeEnum.BENEFIT, 3),
    CART_ADD_ONS_BENEFIT("cart_add_ons_benefit", "加价品", ComponentTypeEnum.BENEFIT, 4),
    ANY_BUNDLE_BENEFIT("any_bundle_benefit", "任意N件优惠价", ComponentTypeEnum.BENEFIT, 5),
    GROUP_BUNDLE_BENEFIT("group_bundle_benefit", "A+B组合优惠价", ComponentTypeEnum.BENEFIT, 6),
    GIFT_BENEFIT("gift_benefit", "赠品", ComponentTypeEnum.BENEFIT, 7),
    FIX_AMOUNT_BENEFIT("fix_amount", "固定售价", ComponentTypeEnum.BENEFIT, 8),
    FREE_MIN_PRICE_BENEFIT("free_min_price", "最低价商品免费", ComponentTypeEnum.BENEFIT, 9),

    /** 生效条件 */
    NO_EVENT("no_event", "无条件", ComponentTypeEnum.BENEFIT_EVENT, -1),
    REACH_AMOUNT_EVENT("reach_amount_event", "满金额", ComponentTypeEnum.BENEFIT_EVENT, 0),
    REACH_COUNT_EVENT("reach_count_event", "满件数", ComponentTypeEnum.BENEFIT_EVENT, 1),
    ANY_BUNDLE_EVENT("any_bundle_event", "任意N件", ComponentTypeEnum.BENEFIT_EVENT, 2),
    GROUP_BUNDLE_EVENT("group_bundle_event", "A+B组合", ComponentTypeEnum.BENEFIT_EVENT, 3),

    /** 支付方式 */
    ALL_PAYMENT("all_payment", "所有支付方式", ComponentTypeEnum.PAYMENT_TYPE, -1),
    LIMIT_PAYMENT("limit_payment", "指定支付方式", ComponentTypeEnum.PAYMENT_TYPE, 0),

    /** 物流方式 */
    ALL_LOGISTICS("all_logistics", "所有配送方式", ComponentTypeEnum.LOGISTICS_TYPE, -1),
    LIMIT_LOGISTICS("limit_logistics", "指定配送方式", ComponentTypeEnum.LOGISTICS_TYPE, 0),

    /** 排除目标 */
    PRODUCT_EXCLUDE("product_exclude", "排除指定商品", ComponentTypeEnum.EXCLUDE_TARGET, 0),
    NO_EXCLUDE("no_exclude", "没有指定排除的商品", ComponentTypeEnum.EXCLUDE_TARGET, 0),
    ;

    private static final int UN_KNOW_VALUE = -2;
    /**
     * 筛选计算的顺序
     */
    private static final List<String> EFFECT_TARGET_LIST = Arrays.asList(
            PRODUCT_TARGET.getCode(),
            LABEL_TARGET.getCode(),
            CATEGORY_TARGET.getCode(),
            STORE_TARGET.getCode()
    );
    private static Map<ComponentTypeEnum, List<ComponentEnum>> typeMap = new HashMap<>();
    private static Map<String, ComponentEnum> codeMap = new HashMap<>();

    static {
        typeMap = Arrays.asList(ComponentEnum.values())
                .stream()
                .collect(Collectors.groupingBy(x -> x.getType()));
        codeMap = Arrays.asList(ComponentEnum.values())
                .stream()
                .collect(Collectors
                        .toMap(ComponentEnum::getCode, Function.identity(), (k1, k2) -> k1));
    }

    private final String code;
    private final String name;
    private final ComponentTypeEnum type;
    private final Integer value;

    ComponentEnum(final String code, final String name, final ComponentTypeEnum type, final Integer value) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public static ComponentEnum getByComponentCode(final String componentCode) {
        return codeMap.get(componentCode);
    }

    public static ComponentEnum getComponentEnum(final ComponentTypeEnum type, final Integer value) {
        if (type == null || value == null) {
            return null;
        }
        return Arrays.stream(ComponentEnum.values()).filter(e -> type.equals(e.getType()) && value.equals(e.getValue()))
                .findFirst().orElse(null);
    }

    public static ComponentEnum getByCode(final ComponentTypeEnum componentType, final String code) {
        return Arrays.stream(ComponentEnum.values())
                .filter(x -> Objects.equals(componentType, x.getType()) && Objects.equals(code, x.getCode()))
                .findFirst().orElse(null);
    }

    public static ComponentEnum getByValue(final ComponentTypeEnum componentType, final Integer value) {
        return Arrays.stream(ComponentEnum.values())
                .filter(x -> Objects.equals(componentType, x.getType()) && Objects.equals(value, x.getValue()))
                .findFirst().orElse(null);
    }

    public static Integer getValueByCode(final ComponentTypeEnum componentType, final String code) {
        final ComponentEnum component = Arrays.stream(ComponentEnum.values())
                .filter(x -> Objects.equals(componentType, x.getType()) && Objects.equals(code, x.getCode()))
                .findFirst().orElse(null);

        if (component == null) {
            return UN_KNOW_VALUE;
        }

        return component.value;
    }

    /**
     * 生效目标计算顺序
     * @return
     */
    public static List<String> listEffectTargetFilterComponent() {
        return EFFECT_TARGET_LIST;
    }

    public boolean isMe(final String code) {
        return this.code.equals(code);
    }

    public boolean isMe(final Integer value) {
        return this.value.equals(value);
    }

}
