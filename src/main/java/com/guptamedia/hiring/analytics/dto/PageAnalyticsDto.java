package com.guptamedia.hiring.analytics.dto;

public record PageAnalyticsDto(String domain, String pagePath, Long pageViews, Double estimatedVisits) {}