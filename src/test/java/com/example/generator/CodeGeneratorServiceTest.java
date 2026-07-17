package com.example.generator;

import com.example.generator.service.CodeGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CodeGeneratorServiceTest {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    void generateCode() {
        codeGeneratorService.generate("sys_users", "sys_roles", "sys_menus");
    }
}
