package com.iokays.sample.advisor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for QualityCheckAdvisor.
 * Tests verify that the advisor configuration is correct.
 */
class QualityCheckAdvisorTest {

    @Test
    void givenAdvisor_whenCreated_thenHasCorrectName() {
        var advisor = new QualityCheckAdvisor();
        assertThat(advisor.getName()).isEqualTo("QualityCheckAdvisor");
    }

    @Test
    void givenAdvisor_whenCreated_thenHasDefaultOrder() {
        var advisor = new QualityCheckAdvisor();
        assertThat(advisor.getOrder()).isNotNull();
    }

    @Test
    void givenAdvisor_whenCreatedWithCustomOrder_thenHasCorrectOrder() {
        var customOrder = 1000;
        var advisor = new QualityCheckAdvisor(customOrder);
        assertThat(advisor.getOrder()).isEqualTo(customOrder);
    }
}
