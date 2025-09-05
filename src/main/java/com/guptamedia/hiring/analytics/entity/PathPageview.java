package com.guptamedia.hiring.analytics.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "path_pageviews")
public class PathPageview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "domain")
    private String domain;

    @Column(name = "page_path")
    private String pagePath;

    @Column(name = "pageviews")
    private Long pageviews;

    public PathPageview () {}

    public PathPageview (String pageTitle, String domain, String pagePath, Long pageviews) {
        this.pageTitle = pageTitle;
        this.domain = domain;
        this.pagePath = pagePath;
        this.pageviews = pageviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public Long getPageviews() {
        return pageviews;
    }

    public void setPageviews(Long pageviews) {
        this.pageviews = pageviews;
    }
}
