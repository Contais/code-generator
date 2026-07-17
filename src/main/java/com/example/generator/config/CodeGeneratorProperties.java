package com.example.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "generator")
public class CodeGeneratorProperties {

    private String outputPackage = "com.example.code";

    private String author = "generator";

    private String outputDir = "./src/main/java";

    private String[] includeTables = {};

    private String[] excludeTables = {};

    private String[] tablePrefix = {};

    private String[] tableSuffix = {};

    private boolean pluralToSingular = true;
}
