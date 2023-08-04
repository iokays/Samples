package com.iokays.pattern.analysis.summaryaccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Entry(BigDecimal amount, LocalDateTime whenCharged, LocalDateTime whenBooked) {}
