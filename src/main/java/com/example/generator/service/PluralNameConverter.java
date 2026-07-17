package com.example.generator.service;

import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PluralNameConverter implements INameConvert {

    private static final Map<String, String> IRREGULAR_MAP = new ConcurrentHashMap<>();

    static {
        IRREGULAR_MAP.put("people", "Person");
        IRREGULAR_MAP.put("men", "Man");
        IRREGULAR_MAP.put("women", "Woman");
        IRREGULAR_MAP.put("children", "Child");
        IRREGULAR_MAP.put("feet", "Foot");
        IRREGULAR_MAP.put("teeth", "Tooth");
        IRREGULAR_MAP.put("mice", "Mouse");
        IRREGULAR_MAP.put("geese", "Goose");
        IRREGULAR_MAP.put("status", "Status");
        IRREGULAR_MAP.put("info", "Info");
        IRREGULAR_MAP.put("news", "News");
    }

    private final StrategyConfig strategyConfig;

    public PluralNameConverter(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    @Override
    public String entityNameConvert(TableInfo tableInfo) {
        String name = tableInfo.getName();
        Set<String> prefix = strategyConfig.getTablePrefix();
        Set<String> suffix = strategyConfig.getTableSuffix();

        String processed = name;
        if (!prefix.isEmpty()) {
            processed = NamingStrategy.removePrefix(processed, prefix);
        }
        if (!suffix.isEmpty()) {
            processed = NamingStrategy.removeSuffix(processed, suffix);
        }

        if (NamingStrategy.underline_to_camel.equals(strategyConfig.entity().getNaming())) {
            processed = NamingStrategy.underlineToCamel(processed);
        }

        return toSingular(NamingStrategy.capitalFirst(processed));
    }

    @Override
    public String propertyNameConvert(TableField field) {
        String name = field.getName();
        Set<String> prefix = strategyConfig.getFieldPrefix();
        Set<String> suffix = strategyConfig.getFieldSuffix();

        String processed = name;
        if (!prefix.isEmpty()) {
            processed = NamingStrategy.removePrefix(processed, prefix);
        }
        if (!suffix.isEmpty()) {
            processed = NamingStrategy.removeSuffix(processed, suffix);
        }

        if (NamingStrategy.underline_to_camel.equals(strategyConfig.entity().getColumnNaming())) {
            return NamingStrategy.underlineToCamel(processed);
        }
        return processed;
    }

    private String toSingular(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        String lower = name.toLowerCase();

        if (IRREGULAR_MAP.containsKey(lower)) {
            return IRREGULAR_MAP.get(lower);
        }

        if (lower.endsWith("ies") && lower.length() > 3) {
            return name.substring(0, name.length() - 3) + "y";
        }

        if (lower.endsWith("ses") && lower.length() > 3) {
            return name.substring(0, name.length() - 2);
        }

        if (lower.endsWith("es") && lower.length() > 2) {
            String stem = lower.substring(0, lower.length() - 2);
            if (stem.endsWith("s") || stem.endsWith("x") || stem.endsWith("z")
                    || stem.endsWith("ch") || stem.endsWith("sh")) {
                return name.substring(0, name.length() - 2);
            }
        }

        if (lower.endsWith("s") && !lower.endsWith("ss") && lower.length() > 1) {
            return name.substring(0, name.length() - 1);
        }

        return name;
    }
}
