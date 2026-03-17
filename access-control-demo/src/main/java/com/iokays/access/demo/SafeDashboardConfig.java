package com.iokays.access.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SafeDashboardConfig {
    private static final String[] PRIVATE_SECTIONS = {"overview", "revenue", "risk"};

    public static final List<String> DEFAULT_SECTIONS =
            Collections.unmodifiableList(Arrays.asList(PRIVATE_SECTIONS));

    private SafeDashboardConfig() {
    }

    public static String[] defaultSectionsCopy() {
        return PRIVATE_SECTIONS.clone();
    }
}
