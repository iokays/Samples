package com.iokays.pattern.analysis.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

 record Entry(BigDecimal amount, LocalDateTime whenCharged, LocalDateTime whenBooked) {

    protected Entry(BigDecimal amount, LocalDateTime whenCharged, LocalDateTime whenBooked) {
        this.amount = amount;
        this.whenCharged = whenCharged;
        this.whenBooked = whenBooked;
    }

}
