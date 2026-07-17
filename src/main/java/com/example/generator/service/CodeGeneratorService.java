package com.example.generator.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.generator.config.CodeGeneratorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeGeneratorService {

    private final CodeGeneratorProperties properties;
    private final DataSourceProperties dataSourceProperties;

    public void generate(String... tables) {
        String[] targetTables = (tables != null && tables.length > 0) ? tables : properties.getIncludeTables();

        FastAutoGenerator.create(dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword())
                .globalConfig(builder -> builder
                        .author(properties.getAuthor())
                        .outputDir(properties.getOutputDir())
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(properties.getOutputPackage())
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .xml("mapper.xml"))
                .strategyConfig(builder -> {
                    if (targetTables != null && targetTables.length > 0) {
                        builder.addInclude(targetTables);
                    }
                    if (properties.getTablePrefix() != null && properties.getTablePrefix().length > 0) {
                        builder.addTablePrefix(properties.getTablePrefix());
                    }
                    if (properties.getTableSuffix() != null && properties.getTableSuffix().length > 0) {
                        builder.addTableSuffix(properties.getTableSuffix());
                    }
                    builder
                            .entityBuilder()
                            .enableLombok()
                            .javaTemplate("/templates/entity.java")
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .mapperTemplate("/templates/mapper.java")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .serviceTemplate("/templates/service.java")
                            .serviceImplTemplate("/templates/serviceImpl.java")
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableHyphenStyle()
                            .template("/templates/controller.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        log.info("代码生成完成，输出目录: {}", properties.getOutputDir());
    }
}
