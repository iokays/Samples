package com.example.sample.model;

/**
 * 用于映射JSONPlaceholder API返回的Post数据
 * @param userId 用户ID
 * @param id 帖子ID
 * @param title 标题
 * @param body 内容
 */
public record Post(Long userId, Long id, String title, String body) {
}
