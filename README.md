# Web Traffic Statistics Application

## Overview
Create a web application to show simple web traffic statistics for government sites and pages. The data being displayed is about the number of pageviews and visits to different domains and pages.

Your job is to write the front end and back end to render this data on a web page.

## Technologies
- **Front end:** React
- **Back end:** Spring Boot

A skeleton project is provided with some of the pieces in place.

## Application Description
The application will have one page, with:
- **Toggle:** Which view of the data to show.
- **Table:** The web traffic data.

### View 1: 'Page Path' View
- **Domain:** Domain of the website
- **Page Path:** URL of the page being viewed on that website
- **Pageviews:** Number of pageviews this page got in the past 90 days
- **Estimated Visits:** Inferred based on another database table (see more below)

### View 2: 'Domain' View
- **Domain:** Domain of the website
- **Pageviews:** Number of pageviews this domain got, across all its page paths
- **Estimated Visits:** Inferred based on another database table (see more below)

## Data Notes
See the `create-tables.sql` file to look at the table definitions. See the CSV files in the same folder to get an idea of the data itself. **Note:** The tables have already been created and the CSVs have already been imported into the SQLite database. These files are there for reference only.

One of the database tables, `path_pageviews`, has most of the data we will be using. It contains all the domains and page_paths, along with their pageviews. 

The second table, `host_pageviews`, has domain-level data for pageviews and visits. (Note that the column in this table called `hostname` is equivalent to the column called `domain` in the other table.) The purpose of this table is to let us know how visits and pageviews relate for a given domain.

The **Estimated Visits** column is inferred by matching the domain and hostname, and then using the pageviews from `path_pageviews` and the ratio of visits per pageview in `host_pageviews`. For example, if we have 100 pageviews for a given page path, and its corresponding domain in the `host_pageviews` table has 500 pageviews and 250 visits, the estimated visits for that page path would be 50.

## Mockups
Note that your views do not have to look exactly like these. It should have "decent" styling but does not need to have a lot of polish.

### Page Path View
![Page Path View](mocks/PagePath.png)

### Domain View
![Domain View](mocks/Domain.png)