package com.iokays.sample.core.adapter.command;

import com.iokays.sample.core.service.CustomerApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.Map;

@Slf4j
// 新版注解, 用来代替 @ShellComponent
@Command(description = "一个新的客户操作类") //command="new", 可以忽略,会默认为空, 但不能去掉这个注解
@AllArgsConstructor
public class CustomerCommander {

    private final CustomerApplicationService service;

    @Command(description = "查询所有的客户") // 默认命令,与方法名 同名
    public void findAll() {
        final Map<Integer, String> all = service.findAll();
        log.info("all: {}", all);
    }

    @Command(command = {"customer", "insert"}, description = "新增用户")
    public void insert(@Option(shortNames = 'n', defaultValue = "unKnown") String name) {
        final Integer id = service.insert(name);
        log.info("id: {}, name: {}", id, name);
    }

    //用例,不考虑并发问题
    @Command(command = {"customer", "update"}, description = "更新用户")
    public void update(@Option(shortNames = 'i') Integer id, @Option(shortNames = 'n') String name) {
        service.update(id, name);
        log.info("id: {}, name: {}", id, name);
    }

    @Command(command = {"customer"}, description = "查看用户")
    public void findById(@Option(shortNames = 'i') Integer id) {
        final String name = service.findById(id);
        log.info("id: {}, name: {}", id, name);
    }


    @Command(command = {"customer", "del"})
    public void delete(Integer id) {
        service.delete(id);
    }


}
