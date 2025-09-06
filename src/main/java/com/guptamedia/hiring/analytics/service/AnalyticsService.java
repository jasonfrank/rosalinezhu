package com.guptamedia.hiring.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import com.guptamedia.hiring.analytics.dto.DomainAnalyticsDto;
import com.guptamedia.hiring.analytics.dto.PageAnalyticsDto;
import com.guptamedia.hiring.analytics.repository.PathPageviewRepository;

@Service
public class AnalyticsService {
    
    @Autowired
    private PathPageviewRepository pathPageviewRepository;

    public Page<PageAnalyticsDto> getPageAnalytics(int page, int size) {
        int offset = page * size;
        
        // Query only the data we need from database
        List<Object[]> pageResults = pathPageviewRepository.findPagePathAnalytics(size, offset);
        long totalElements = pathPageviewRepository.countPagePathAnalytics();
        
        List<PageAnalyticsDto> pageDtos = pageResults.stream()
            .map(row -> new PageAnalyticsDto(
                (String) row[0],
                (String) row[1],
                ((Number) row[2]).longValue(),
                ((Number) row[3]).doubleValue()
            ))
            .collect(Collectors.toList());
        
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(pageDtos, pageable, totalElements);
    }

    public Page<DomainAnalyticsDto> getDomainAnalytics(int page, int size) {
        int offset = page * size;
        
        // Query only the data we need from database
        List<Object[]> pageResults = pathPageviewRepository.findDomainAnalytics(size, offset);
        long totalElements = pathPageviewRepository.countDomainAnalytics();
        
        List<DomainAnalyticsDto> pageDtos = pageResults.stream()
            .map(row -> new DomainAnalyticsDto(
                (String) row[0],
                ((Number) row[1]).longValue(),
                ((Number) row[2]).doubleValue()
            ))
            .collect(Collectors.toList());
        
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(pageDtos, pageable, totalElements);
    }
}
