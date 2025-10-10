package com.example.sample;

import com.example.sample.model.Post;
import com.example.sample.service.JsonPlaceholderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Main implements CommandLineRunner {

    private final JsonPlaceholderService service;

    public Main(JsonPlaceholderService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- WebClient 功能演示 (已集成扩展功能) ---");

        // 1. 同步阻塞获取单个资源
        System.out.println("
1. 同步获取ID为1的Post:");
        Post post1 = service.getPostByIdSync(1L);
        System.out.println("   " + post1);

        // 2. 异步非阻塞获取，使用虚拟线程简化并发处理
        System.out.println("
2. 异步获取ID为2的Post (使用虚拟线程):");
        Thread.startVirtualThread(() -> {
            service.getPostByIdAsync(2L).subscribe(
                post -> System.out.println("   " + post)
            );
        }).join();

        // 3. 异步获取资源列表 (Flux)
        System.out.println("
3. 异步获取前5个Post标题:");
        service.getAllPostsAsync()
            .take(5)
            .subscribe(post -> System.out.println("   - " + post.title()));

        // 4. 创建资源 (POST)
        System.out.println("
4. 创建一个新的Post:");
        Thread.startVirtualThread(() -> {
            Post newPost = new Post(101L, null, "一个创新的标题", "这是Post的内容主体。");
            service.createPost(newPost).subscribe(
                createdPost -> System.out.println("   成功创建: " + createdPost)
            );
        }).join();

        // 5. 错误处理
        System.out.println("
5. 演示错误处理 (尝试获取不存在的Post):");
        Thread.startVirtualThread(() -> {
            service.getPostWithErrorHandling(9999L).subscribe(
                p -> System.out.println("   意外成功: " + p),
                e -> System.err.println("   发生错误: " + e.getMessage()),
                () -> System.out.println("   错误处理演示完成，未获取到Post。")
            );
        }).join();
        
        // 6. 扩展点：自动重试
        System.out.println("
6. 扩展点演示：自动重试 (尝试获取一个会临时失败的资源):");
        Thread.startVirtualThread(() -> {
            // 模拟一个会失败的ID，实际场景中可能是网络问题
            service.getPostWithRetry(99999L).subscribe(
                p -> System.out.println("   获取成功: " + p),
                e -> System.err.println("   在3次重试后仍然失败!")
            );
        }).join();


        System.out.println("
--- 演示结束 ---");
    }
}
