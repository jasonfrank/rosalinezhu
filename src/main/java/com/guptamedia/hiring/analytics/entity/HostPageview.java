package com.guptamedia.hiring.analytics.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "host_pageviews")
public class HostPageview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hostname")
    private String hostname;

    @Column(name = "pageviews")
    private Long pageviews;

    @Column(name = "visits")
    private Long visits;

    public HostPageview() {}

    public HostPageview(String hostname, Long pageviews, Long visits) {
        this.hostname = hostname;
        this.pageviews = pageviews;
        this.visits = visits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname(){
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Long getPageviews() {
        return pageviews;
    }

    public void setPageviews(Long pageviews) {
        this.pageviews = pageviews;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

}
