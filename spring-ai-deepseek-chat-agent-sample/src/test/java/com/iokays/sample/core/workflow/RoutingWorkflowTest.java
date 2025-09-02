package com.iokays.sample.core.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@SpringBootTest
class RoutingWorkflowTest {

    @Resource
    private RoutingWorkflow routingWorkflow;

    /**
     *
     * systemPrompt: 对用户的提问进行分类, 请直接回答如下分类(账单/一般咨询/技术)
     * category: 账单
     * result: 非常理解您的担忧。发现信用卡被重复扣款确实会让人感到不安。作为您的账单专员，我将协助您解决这个问题......
     */
    @Test
    void test() {
        final Map<String, String> routes = Map.of(
                "账单", "您是一名账单专员。请协助解决账单问题...",
                "技术", "您是一名技术支持工程师。请协助解决技术问题...",
                "一般咨询", "您是一名客服代表。请处理一般性咨询..."
        );

        final String userInput = "我的信用卡帐户上周被扣了两次款";

        final String result = routingWorkflow.route(userInput, routes);
        log.info("result: {}", result);
    }


}