package com.guptamedia.hiring.analytics.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.guptamedia.hiring.analytics.dto.DomainAnalyticsDto;
import com.guptamedia.hiring.analytics.dto.PageAnalyticsDto;
import com.guptamedia.hiring.analytics.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/pages")
    public List<PageAnalyticsDto> getPageAnalytics() {
        return analyticsService.getPageAnalytics();
    }

    @GetMapping("/domain")
    public List<DomainAnalyticsDto> getDomainAnalytics() {
        return analyticsService.getDomainAnalytics();
    }
    
}
