package com.iokays.sample.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class FlowableApplicationService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    // 启动流程
    public String start(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                processDefinitionKey, variables);
        return processInstance.getProcessInstanceId();
    }

    // 查询任务
    public Task task(final String processInstanceId) {
        return taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }

    //认领任务
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    //完成任务
    public void complete(String taskId, String userId, Map<String, Object> variables) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task.getAssignee() == null) {
            throw new IllegalStateException("必须先认领任务才能完成！");
        }

        if (!Objects.equals(task.getAssignee(), userId)) {
            throw new IllegalStateException("必须是认领人才可以完成任务!");
        }

        taskService.complete(taskId, userId, variables);
    }

}

