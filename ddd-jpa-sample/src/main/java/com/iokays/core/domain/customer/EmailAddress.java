package com.iokays.core.domain.customer;

import com.iokays.common.core.domain.ValueObject;
import jakarta.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

/**
 * 客户的邮箱地址
 *
 * @param value {@link Integer}邮箱地址的值
 */
@Embeddable
public record EmailAddress(String value) implements ValueObject<EmailAddress> {

    /**
     * 默认构造函数
     *
     * @param value {@link Integer}邮箱地址的值
     */
    public EmailAddress {
        Validate.isTrue(StringUtils.isNotEmpty(value), "邮箱地址不能为空");
        Validate.isTrue(isValidEmailAddress(value), "邮箱地址不合法");
    }

    /**
     * 创建邮箱地址 {@link EmailAddress}
     *
     * @param value {@link Integer}邮箱地址的值
     * @return 邮箱地址 {@link EmailAddress}
     */
    public static EmailAddress from(String value) {
        return new EmailAddress(value);
    }

    /**
     * 验证邮箱地址是否合法
     *
     * @param value {@link String} 邮箱地址的值
     * @return {@link Boolean} 邮箱地址是否合法
     */
    private boolean isValidEmailAddress(String value) {
        return EmailValidator.getInstance().isValid(value);
    }

    @Override
    public boolean sameValueAs(EmailAddress other) {
        return Objects.equals(value, other.value);
    }
}