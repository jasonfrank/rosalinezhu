package com.guptamedia.hiring.analytics.repository;

import java.util.List;
import com.guptamedia.hiring.analytics.entity.PathPageview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathPageviewRepository extends JpaRepository<PathPageview, Long>{
    
    @Query(value = """
        SELECT h.hostname, 
               p.page_path, 
               p.pageviews, 
               (p.pageviews * 1.0 / h.pageviews) * h.visits as estimated_visits
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain
        LIMIT 10 
        """, nativeQuery = true)
    List<Object[]> findPagePathAnalytics();

    @Query(value = """
        SELECT h.hostname, 
               SUM(p.pageviews) as total_pageviews, 
               SUM((p.pageviews * 1.0 / h.pageviews) * h.visits) as estimated_visits 
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain 
        GROUP BY h.hostname, h.pageviews, h.visits
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> findDomainAnalytics();
}