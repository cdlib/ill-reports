# ILL Reports

## Overview

The project includes reporting tools for UC interlibrary loan activity.

See the [Github wiki](https://github.com/cdlib/ill-reports/wiki) for documentation and troubleshooting.

## Web API

## `text/html`

The project includes an HTML form to access report data.

    GET /

## `application/json`, `application/xml`, `text/csv`

The project includes a web service for report data.

    GET /ill/report/by-campus/{CAMPUS}.{EXTENSION}?reportStartDate={DATE}&reportEndDate={DATE}
    GET /ill/report/by-campus/{CAMPUS}/borrowing.{EXTENSION}?reportStartDate={DATE}&reportEndDate={DATE}
    GET /ill/report/by-campus/{CAMPUS}/lending.{EXTENSION}?reportStartDate={DATE}&reportEndDate={DATE}

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

The `EXTENSION` may be `xml` for _application/xml_, `json` for _application/json_, or `csv` for _text/csv_.

The format for `DATE` parameters is `yyyy-MM-dd`.
