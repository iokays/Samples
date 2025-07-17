package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeminiServiceTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GeminiServiceTest.class);

    @Resource
    private GeminiService geminiService;

    /**
     * 运行结果:
     * ====
     * 以下是您的选项：
     *
     * .Red Pill
     * [%collapsible]
     * ======
     * 逃离到真实世界。
     * ======
     *
     * .I AM YOU
     * [%collapsible]
     * ======
     * 在模拟现实中生活，无欲无求，无所畏惧。
     * ======
     * ====
     *
     * 狼人相信 [.small]#小字# 吗？
     *
     * [.big]##从前## 有个无限循环。
     */
    @Test
    void test() {
        final var content = """
                ====
                Here are your options:
                
                .Red Pill
                [%collapsible]
                ======
                Escape into the real world.
                ======
                
                .I AM YOU
                [%collapsible]
                ======
                Live within the simulated reality without want or fear.
                ======
                ====
                
                Do werewolves believe in [.small]#small print#? 

                [.big]##O##nce upon an infinite loop.
                """;

        final var result = geminiService.transaction(content);
        Assertions.assertTrue(StringUtils.isNotBlank(result));
        log.debug("content: {}", result);
    }

}


