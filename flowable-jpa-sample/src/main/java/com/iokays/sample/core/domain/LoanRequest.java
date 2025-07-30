package com.iokays.sample.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 作为工作流的数据存储部分, 核心能力存储数据, 建议设计为贫血模型, 并使用审计功能(历史变更记录); 但不会使用大量的JPA高级特性.
 */
@Getter
@Audited //默认会存在 flow_data_loan_request_aud 表中, 每一次数据变更都会生成一条记录
@Entity(name = "flow_data_loan_request")
public class LoanRequest implements Serializable {

    @Id
    private String id;

    private String customerName;
    private BigDecimal amount;
    private Boolean approved;

    public void setId(String id) {
        this.id = Validate.notBlank(id);
    }

    public void setCustomerName(String customerName) {
        this.customerName = Validate.notBlank(customerName);
    }

    public void setAmount(BigDecimal amount) {
        Validate.notNull(amount, "amount is null");
        Validate.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "amount <= 0");
        this.amount = amount;
    }

    public void setApproved(Boolean approved) {
        this.approved = Validate.notNull(approved, "approved is null");
    }

}