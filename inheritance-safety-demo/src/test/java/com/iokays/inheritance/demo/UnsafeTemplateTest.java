package com.iokays.inheritance.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnsafeTemplateTest {
    @Test
    void superclassConstructorSeesSubclassStateTooEarly() {
        BrokenHeadlineTemplate template = new BrokenHeadlineTemplate("READY");

        assertNull(template.preview());
        assertEquals("READY", template.headline());
    }
}
