/**
 * 这一层是特意创建的, 属于业务层与安全层的适配器, 更能清楚的理解Spring Security的原理.
 * <p>
 * 这样就能很清晰的将里面的默认的内存存储修改为适合本业务的Service,
 * 而不是在业务层中实现 Spring Security 提供的接口, 让业务层尽量避免感知Spring Security的存在.
 *
 */
package com.iokays.authorization.config.security.adapter.persistence;