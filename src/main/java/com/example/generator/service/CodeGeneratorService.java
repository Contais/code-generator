package com.example.generator.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.generator.config.CodeGeneratorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

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
                    builder
                            .entityBuilder()
                            .enableLombok()
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableHyphenStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        log.info("代码生成完成，输出目录: {}", properties.getOutputDir());
    }
}
