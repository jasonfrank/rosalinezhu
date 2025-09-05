package com.guptamedia.hiring.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.guptamedia.hiring.analytics.dto.DomainAnalyticsDto;
import com.guptamedia.hiring.analytics.dto.PageAnalyticsDto;
import com.guptamedia.hiring.analytics.repository.PathPageviewRepository;

@Service
public class AnalyticsService {
    
    @Autowired
    private PathPageviewRepository pathPageviewRepository;

    public List<PageAnalyticsDto> getPageAnalytics() {
        return pathPageviewRepository.findPagePathAnalytics()
            .stream()
            .map(row -> new PageAnalyticsDto(
                (String) row[0],
                (String) row[1],
                (Long) row[2],
                (Double) row[3]
            ))
            .collect(Collectors.toList());
    }

    public List<DomainAnalyticsDto> getDomainAnalytics() {
        return pathPageviewRepository.findDomainAnalytics()
            .stream()
            .map(row -> new DomainAnalyticsDto(
                (String) row[0],
                (Long) row[1],
                (Double) row[2]
            ))
            .collect(Collectors.toList());
    }
}
