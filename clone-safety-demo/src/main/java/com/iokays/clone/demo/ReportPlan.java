package com.iokays.clone.demo;

import java.util.ArrayList;
import java.util.List;

public final class ReportPlan {
    private final List<String> sections;

    public ReportPlan(List<String> sections) {
        this.sections = new ArrayList<>(sections);
    }

    public ReportPlan(ReportPlan other) {
        this(other.sections);
    }

    public static ReportPlan copyOf(ReportPlan other) {
        return new ReportPlan(other);
    }

    public void addSection(String section) {
        sections.add(section);
    }

    public List<String> sections() {
        return List.copyOf(sections);
    }

    @Override
    public String toString() {
        return "ReportPlan" + sections;
    }
}
