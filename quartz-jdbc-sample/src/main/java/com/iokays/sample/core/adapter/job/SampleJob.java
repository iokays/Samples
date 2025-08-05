package com.iokays.sample.core.adapter.job;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务调度, 可以作为一个业务的入栈, 和Web, 命令行等同级别包.
 */
@Slf4j
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SampleJob implements Job {

    // 编写任务内容
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void execute(JobExecutionContext context) {
        // 获取JobDetail
        JobDetail jobDetail = context.getJobDetail();

        // 可以输出任务的一些详情信息
        log.info("任务名称：{}", jobDetail.getKey().getName());
        log.info("任务分组名称：{}", jobDetail.getKey().getGroup());
        log.info("任务类名字：{}", jobDetail.getJobClass().getName());
        log.info("本次任务执行时间：{}", context.getFireTime());
        log.info("下次任务执行时间：{}", context.getNextFireTime());
        // 还可以通过JobDataMap存储数据
        final JobDataMap jobDataMap = jobDetail.getJobDataMap();
        log.info("jobInputDataMap: {}", jobDataMap.get("input"));
        int count = Try.of(() -> jobDataMap.getInt("count")).getOrElse(0);
        // 或者：Integer count1 = (Integer) jobDataMap.get("count");
        log.info("第{}次执行", count);
        // 更新共享数据
        jobDataMap.put("count", ++count);
    }
}
