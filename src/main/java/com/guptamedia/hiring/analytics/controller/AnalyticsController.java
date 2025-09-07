package com.guptamedia.hiring.analytics.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;

import java.util.List;

import com.guptamedia.hiring.analytics.dto.DomainAnalyticsDto;
import com.guptamedia.hiring.analytics.dto.PageAnalyticsDto;
import com.guptamedia.hiring.analytics.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/pages")
    public Page<PageAnalyticsDto> getPageAnalytics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return analyticsService.getPageAnalytics(page, size);
    }

    @GetMapping("/domain")
    public Page<DomainAnalyticsDto> getDomainAnalytics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return analyticsService.getDomainAnalytics(page, size);
    }
    
}
