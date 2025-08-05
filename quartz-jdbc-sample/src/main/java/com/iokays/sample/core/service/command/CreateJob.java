package com.iokays.sample.core.service.command;

import lombok.Builder;
import org.quartz.Job;

import java.time.LocalDateTime;

@Builder
public record CreateJob(
        String name,
        String group,
        String cronExpression,
        LocalDateTime startAt,
        LocalDateTime endAt,
        Class<? extends Job> jobClass,
        Object input
) {
}
