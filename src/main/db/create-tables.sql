CREATE TABLE path_pageviews (
     page_title TEXT,
     domain TEXT,
     page_path TEXT,
     pageviews INTEGER
);

CREATE TABLE host_pageviews (
   hostname TEXT,
   pageviews INTEGER,
   visits INTEGER
);
