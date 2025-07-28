package com.iokays.sample.core.common;

/**
 * 领域事件接口
 * 领域事件是唯一的，但没有生命周期的东西。标识可以是显式的，例如付款的序列号，也可以从事件的各个方面派生，例如何时发生了什么。
 */
public interface DomainEvent<T extends DomainEvent<?>> {


}
