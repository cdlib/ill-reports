# ILL Reports

## Overview

The project includes reporting tools for UC interlibrary loan activity.

See the [Github wiki](https://github.com/cdlib/ill-reports/wiki) for documentation and troubleshooting.

## Web API

In a nutshell:

    GET /ill/data/by-campus/{CAMPUS}/borrowing.{EXTENSION}?startDate={DATE}&endDate={DATE}
    GET /ill/data/by-campus/{CAMPUS}/borrowing.{EXTENSION}?startDate={DATE}&endDate={DATE}

Where campus can be one of:
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
- `all` All of the above combined

Where `EXTENSION` can be one of:
- `csv` _text/csv_
- `json` _application/json_
- `xml` _application/xml_

Where `DATE` is formatted `yyyy-MM-dd`. For example, `2017-12-31`.
