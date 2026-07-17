package com.example.generator.service;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.generator.config.CodeGeneratorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
                        .disableOpenDir())
                .packageConfig(builder -> builder
                        .parent(properties.getOutputPackage())
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, properties.getOutputDir() + "/mapper/xml")))
                .strategyConfig(builder -> {
                    if (targetTables != null && targetTables.length > 0) {
                        builder.addInclude(targetTables);
                    }
                    builder
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableHyphenStyle();
                })
                .templateConfig(builder -> builder
                        .entity("/templates/entity.java")
                        .mapper("/templates/mapper.java")
                        .service("/templates/service.java")
                        .serviceImpl("/templates/serviceImpl.java")
                        .controller("/templates/controller.java"))
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        log.info("代码生成完成，输出目录: {}", properties.getOutputDir());
    }
}
