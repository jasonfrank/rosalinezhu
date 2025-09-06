package com.guptamedia.hiring.analytics.repository;

import java.util.List;
import com.guptamedia.hiring.analytics.entity.PathPageview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PathPageviewRepository extends JpaRepository<PathPageview, Long>{
    
    @Query(value = """
        SELECT h.hostname, 
               p.page_path, 
               p.pageviews, 
               (p.pageviews * 1.0 / h.pageviews) * h.visits AS estimated_visits
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain
        ORDER BY estimated_visits DESC
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Object[]> findPagePathAnalytics(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = """
        SELECT COUNT(*)
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain
        """, nativeQuery = true)
    long countPagePathAnalytics();

    @Query(value = """
        SELECT h.hostname, 
               SUM(p.pageviews) as total_pageviews, 
               SUM((p.pageviews * 1.0 / h.pageviews) * h.visits) as estimated_visits 
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain 
        GROUP BY h.hostname, h.pageviews, h.visits
        ORDER BY estimated_visits DESC
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Object[]> findDomainAnalytics(@Param("limit") int limit, @Param("offset") int offset);

    @Query(value = """
        SELECT COUNT(DISTINCT h.hostname)
        FROM host_pageviews h 
        JOIN path_pageviews p ON h.hostname = p.domain
        """, nativeQuery = true)
    long countDomainAnalytics();
}