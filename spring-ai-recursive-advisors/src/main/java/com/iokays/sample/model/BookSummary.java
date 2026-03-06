package com.iokays.sample.model;

import java.util.List;

/**
 * Record representing a book summary with title, author, and themes.
 */
public record BookSummary(String title, String author, List<String> themes) {}
