package com.iokays.interfaces.demo;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractTagViewTest {
    @Test
    void skeletalImplementationReducesBoilerplate() {
        ReleaseTagView view = new ReleaseTagView(Set.of("release", "ready"));

        assertTrue(view.hasTag("release"));
        assertEquals("ReleaseTagView[release, ready]", view.toString());
    }
}
