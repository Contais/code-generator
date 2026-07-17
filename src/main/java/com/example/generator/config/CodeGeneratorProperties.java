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

    private String databaseUrl = "jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";

    private String username = "root";

    private String password = "root";

    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String[] includeTables = {};

    private String[] excludeTables = {};
}
