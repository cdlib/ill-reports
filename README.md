# ILL Reports

## Overview

The project includes reporting tools for UC inter-library loans.

A Maven build `mvn install` produces an executable JAR using Spring Boot & Tomcat.
You can run the web server from the command line.
The server will listen on port 8080 by default.

    java -jar JAR_PATH -Dserver.port=PORT_NUMBER

## `text/html`

The project includes an HTML form to access report data.

    GET /

## `application/json`, `application/xml`

The project includes a web service for report data.

    GET /ill/report/by-campus/{CAMPUS}?reportStartDate=2016-12-31&reportEndDate=2017-06-01