package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatClientServiceTest {

    @Resource
    private ChatClientService chatClientService;

    @Test
    void test() {
        chatClientService.simple();
        chatClientService.pt_zero_shot();
        chatClientService.pt_one_shot_few_shots();
        chatClientService.pt_system_prompting_1();
        chatClientService.pt_system_prompting_2();
        chatClientService.pt_role_prompting_1();
        chatClientService.pt_role_prompting_2();
        chatClientService.pt_contextual_prompting();
        chatClientService.pt_step_back_prompting();
        chatClientService.pt_chain_thought_zero_shot();
        chatClientService.pt_chain_thought_single_shot_few_shots();
        chatClientService.pt_self_consistency();
        chatClientService.pt_tree_of_thought();
        chatClientService.automatic_prompt_engineering();
        chatClientService.pt_code_prompt_writing_code();
        chatClientService.pt_code_prompting_explaining_code();
        chatClientService.pt_code_prompting_translating_code();
    }

}