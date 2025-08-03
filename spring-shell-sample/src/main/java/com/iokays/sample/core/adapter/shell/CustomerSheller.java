//package com.iokays.sample.core.adapter.shell;
//
//import com.iokays.sample.core.service.CustomerApplicationService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.shell.command.annotation.Option;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//
//import java.util.Map;
//
//@Slf4j
//@AllArgsConstructor
//@ShellComponent(value = "old") // 旧版, 用@Command代替, 详见 CustomerCommander
//public class CustomerSheller {
//
//    private final CustomerApplicationService service;
//
//
//    @ShellMethod(value = "查询所有的客户") // 默认命令,与方法名 同名
//    public void findAll() {
//        final Map<Integer, String> all = service.findAll();
//        log.info("all: {}", all);
//    }
//
//    @ShellMethod(key = {"customer", "insert"}, value = "新增用户")
//    public void insert(@ShellOption(value = "-n", defaultValue = "unKnown") String name) {
//        final Integer id = service.insert(name);
//        log.info("id: {}, name: {}", id, name);
//    }
//
//    //用例,不考虑并发问题
//    @ShellMethod(key = {"customer", "update"}, value = "更新用户")
//    public void update(@ShellOption(value = "-i") Integer id, @Option(shortNames = 'n') String name) {
//        service.update(id, name);
//        log.info("id: {}, name: {}", id, name);
//    }
//
//    @ShellMethod(key = {"customer"}, value = "查看用户")
//    public void findById(@ShellOption(value = "-i") Integer id) {
//        final String name = service.findById(id);
//        log.info("id: {}, name: {}", id, name);
//    }
//
//
//    @ShellMethod(key = {"customer", "del"})
//    public void delete(Integer id) {
//        service.delete(id);
//    }
//
//}