package com.example.generator;

import com.example.generator.service.CodeGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGeneratorServiceTest {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Test
    void generateCode() {
        codeGeneratorService.generate();
    }

    @Test
    void generateForSpecificTables() {
        codeGeneratorService.generate("user", "order");
    }
}
