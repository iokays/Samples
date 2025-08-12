/**
 * 适配器, 基于六边形架构，适配器是应用与外部世界交互的接口。
 * <p>
 * 主适配器（别名Driving Adapter）代表用户如何使用应用，
 * 从技术上来说，它们接收用户输入，调用端口并返回输出。
 * <p>
 * 次适配器（别名Driven Adapter）
 * 实现应用的出口端口，向外部工具执行操作
 */
package com.iokays.core.adapter;