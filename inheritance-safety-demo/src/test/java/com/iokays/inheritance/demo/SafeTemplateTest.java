package com.iokays.inheritance.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SafeTemplateTest {
    @Test
    void constructorDoesNotCallOverridableHook() {
        CountingTemplate template = new CountingTemplate("READY");

        assertEquals(0, template.renderCalls());
        assertEquals("READY", template.preview());
        assertEquals(1, template.renderCalls());
    }

    @Test
    void safeSubclassRendersCorrectValueAfterConstruction() {
        HeadlineTemplate template = new HeadlineTemplate("READY");

        assertEquals("READY", template.preview());
    }
}
