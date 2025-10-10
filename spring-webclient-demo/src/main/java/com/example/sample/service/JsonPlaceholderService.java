package com.example.sample.service;

import com.example.sample.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class JsonPlaceholderService {

    private final WebClient webClient;

    public JsonPlaceholderService() {
        this.webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    }

    /**
     * 异步获取单个Post
     */
    public Mono<Post> getPostByIdAsync(Long id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class);
    }

    /**
     * 同步获取单个Post
     */
    public Post getPostByIdSync(Long id) {
        return getPostByIdAsync(id).block();
    }

    /**
     * 异步获取所有Post
     */
    public Flux<Post> getAllPostsAsync() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }

    /**
     * 创建一个新的Post
     */
    public Mono<Post> createPost(Post newPost) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(newPost)
                .retrieve()
                .bodyToMono(Post.class);
    }

    /**
     * 处理错误情况
     */
    public Mono<Post> getPostWithErrorHandling(Long id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class)
                .onErrorResume(e -> {
                    System.out.println("获取Post时发生错误: " + e.getMessage());
                    return Mono.empty();
                });
    }

    /**
     * 获取Post，并在失败时自动重试
     */
    public Mono<Post> getPostWithRetry(Long id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(Post.class)
                .retry(3)
                .doOnError(e -> System.out.println("获取Post失败，已重试3次: " + e.getMessage()));
    }
}
