# ILL Reports

## Overview

The project includes reporting tools for UC inter-library loans.

A Maven build (`mvn install`) produces an executable JAR using Spring Boot & Tomcat.
You can run the web server from the command line.
The server will listen on port 8080 by default.

    java -jar JAR_PATH -Dserver.port=PORT_NUMBER

## `text/html`

The project includes an HTML form to access report data.

    GET /

## `application/json`, `application/xml`

The project includes a web service for report data.

    GET /ill/report/by-campus/{CAMPUS}?reportStartDate=2016-12-31&reportEndDate=2017-06-01

The date range query parameters are optional. Their format is `yyyy-MM-dd`.

The `CAMPUS` component of the URI may be any of the following values:
- `OELA` UCLA Document Delivery
- `NRLF` Northern Regional Library Facility
- `SRLF` Southern Regional Library Facility
- `UCB` UC Berkeley
- `UCD` UC Davis
- `UCI` UC Irvine
- `UCLA` UC Los Angeles
- `UCM` UC Merced
- `UCR` UC Riverside
- `UCSB` UC Santa Barbara
- `UCSC` UC Santa Cruz
- `UCSD` UC San Diego
- `UCSF` UC San Francisco