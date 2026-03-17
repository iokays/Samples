package com.iokays.access.demo;

public final class ReportSummary {
    private final String title;
    private final double revenue;

    public ReportSummary(String title, double revenue) {
        this.title = title;
        this.revenue = revenue;
    }

    public String title() {
        return title;
    }

    public double revenue() {
        return revenue;
    }

    @Override
    public String toString() {
        return "ReportSummary[title=%s, revenue=%.1f]".formatted(title, revenue);
    }
}
