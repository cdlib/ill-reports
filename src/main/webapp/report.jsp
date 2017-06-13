<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>ILL Reports (VDX)</title>
        <link rel="shortcut icon" href="http://intracdl.cdlib.org/favicon.ico" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="report.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <br>
            <p class="text-center"><img src="CDL_Logo.png" class="banner" alt="University of California - California Digital Library" /></p>
            <h1 class="page-header">ILL Reports (VDX)</h1>

            <div class="hidden-print">
                <h2>Search</h2>
                <form action="/" method="post" class="form-inline">
                    <div class="row">
                        <div class="col-sm-3">
                            <label for="campus">Location</label><br>
                            <select id="campus" name="campus" class="form-control">
                                <option value="">All Campuses</option>
                                <c:forEach var="campus" items="${campuses}">
                                    <option value="${campus.code}" <c:if test="${campus.code eq campusDefault}">selected="selected"</c:if>>${campus.description}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="from">Begin Date</label><br>
                            <input id="from" name="from" class="form-control" type="text" placeholder="YYYY-MM-DD" value="${searchStartDate}" required />
                        </div>
                        <div class="col-sm-2">
                            <label for="to">End Date</label><br>
                            <input id="to" name="to" class="form-control" type="text" placeholder="YYYY-MM-DD" value="${searchEndDate}" required />
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-xs-12">
                            <input name="command" type="submit" value="Search" class="btn" />
                            &nbsp;&nbsp;
                            <input name="command" type="submit" value="Reset" class="btn" />
                        </div>
                    </div>
                </form>

                <hr>
            </div>

            <c:forEach var="report" items="${reports}">
                <section>
                    <h2>${report.campusCode} (${searchStartDate} - ${searchEndDate})</h2>
                    <c:if test="${report.institutionReports.size() eq 0}">
                        <p>
                            <em>No borrowing or lending to show.</em>
                        </p>
                    </c:if>
                    <c:if test="${report.institutionReports.size() gt 0}">
                        <p>
                        <dl>
                            <dt>Summary Data (All Libraries)</dt>
                            <dd><i class="fa fa-code" aria-hidden="true"></i>&nbsp;
                                <a href="/ill/data/by-campus/${report.campusCode}.xml?startDate=${searchStartDate}&endDate=${searchEndDate}">XML</a>
                                /
                                <a href="/ill/data/by-campus/${report.campusCode}.json?startDate=${searchStartDate}&endDate=${searchEndDate}">JSON</a>
                            </dd>
                            <dt>Raw Borrowing Data (All Libraries)</dt>
                            <dd><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;
                                <a href="/ill/data/by-campus/${report.campusCode}/borrowing.csv?startDate=${searchStartDate}&endDate=${searchEndDate}">CSV</a>
                            </dd>
                            <dd><i class="fa fa-code" aria-hidden="true"></i>&nbsp;
                                <a href="/ill/data/by-campus/${report.campusCode}/borrowing.xml?startDate=${searchStartDate}&endDate=${searchEndDate}">XML</a>
                                /
                                <a href="/ill/data/by-campus/${report.campusCode}/borrowing.json?startDate=${searchStartDate}&endDate=${searchEndDate}">JSON</a>
                            </dd>
                            <dt>Raw Lending Data (All Libraries)</dt>
                            <dd><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;
                                <a href="/ill/data/by-campus/${report.campusCode}/lending.csv?startDate=${searchStartDate}&endDate=${searchEndDate}">CSV</a>
                            </dd>
                            <dd><i class="fa fa-code" aria-hidden="true"></i>&nbsp;
                                <a href="/ill/data/by-campus/${report.campusCode}/lending.xml?startDate=${searchStartDate}&endDate=${searchEndDate}">XML</a>
                                /
                                <a href="/ill/data/by-campus/${report.campusCode}/lending.json?startDate=${searchStartDate}&endDate=${searchEndDate}">JSON</a>
                            </dd>
                        </dl>
                        </p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Library</th>
                                    <th></th>
                                    <th scope="col">ISO</th>
                                    <th scope="col">OCLC</th>
                                    <th scope="col">UC</th>
                                    <th scope="col">All Categories</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="library" items="${report.institutionReports}">
                                    <tr class="borrowing-row">
                                        <th scope="row" rowspan="2" class="library-header">${library.name}</th>
                                        <td><i class="fa fa-sign-in" aria-hidden="true"></i> Borrowing</td>
                                        <td>${library.totalISOBorrowing}</td>
                                        <td>${library.totalOCLCBorrowing}</td>
                                        <td>${library.totalUCBorrowing}</td>
                                        <td>${library.totalBorrowing}</td>
                                    </tr>
                                    <tr class="lending-row">
                                        <td><i class="fa fa-sign-out" aria-hidden="true"></i> Lending</td>
                                        <td>${library.totalISOLending}</td>
                                        <td>${library.totalOCLCLending}</td>
                                        <td>${library.totalUCLending}</td>
                                        <td>${library.totalLending}</td>
                                    </tr>
                                </c:forEach>
                                <tr class="total-row">
                                    <th scope="row" rowspan="2">All Libraries</th>
                                    <th scope="row">Total Borrowing</th>
                                    <td>${report.totalISOBorrowing}</td>
                                    <td>${report.totalOCLCBorrowing}</td>
                                    <td>${report.totalUCBorrowing}</td>
                                    <td>${report.totalBorrowing}</td>
                                </tr>
                                <tr class="total-row">
                                    <th scope="row">Total Lending</th>
                                    <td>${report.totalISOLending}</td>
                                    <td>${report.totalOCLCLending}</td>
                                    <td>${report.totalUCLending}</td>
                                    <td>${report.totalLending}</td>
                                </tr>
                            </tbody>
                        </table>
                    </c:if>
                </section>
            </c:forEach>

        </div>
        <p class="text-center">
            <br>
            <br>
            Copyright &copy; 2017 The Regents of The University of California &nbsp; <img src="uc_seal.gif" alt="University of California Seal"> &nbsp; California Digital Library
            <br>
            <a href="mailto:vdxtrouble-l@ucop.edu">Questions? Comments?</a>
        </p>
    </body>
</html>