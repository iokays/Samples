package com.iokays.designpattern.command.remote;


/**
 * 命令模式
 * 命令模式将请求封装成对象, 以便使用不同的请求, 队列或者日志来参数化其他对象. 命令模式也支持可撤销的操作.
 */
public interface Command {

    /**
     * 执行命令", notes = "执行命令
     */
    void execute();

    /**
     * 撤销命令", notes = "撤销命令
     */
    void undo();

}
