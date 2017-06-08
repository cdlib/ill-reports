<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>ILL Reports</title>
        <link rel="shortcut icon" href="http://intracdl.cdlib.org/favicon.ico" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <br>
            <p class="text-center"><img src="CDL_Logo.png" style="height: 6em;" alt="University of California - California Digital Library" /></p>
            <h1 class="page-header">ILL Reports</h1>

            <h2>Search</h2>
            <form action="/" method="post" class="form-inline">

                <div class="form-group">
                    <label for="campus" class="">Location</label>
                    <select id="campus" name="campus">
                        <option value="">All Campuses</option>
                        <c:forEach var="campus" items="${campuses}">
                            <option value="${campus.code}" <c:if test="${campus.code eq campusDefault}">selected="selected"</c:if>>${campus.description}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="from">Begin Date</label>
                    <input id="from" name="from" type="text" placeholder="YYYY-MM-DD" value="${searchStartDate}" required />
                </div>

                <div class="form-group">
                    <label for="to">End Date</label>
                    <input id="to" name="to" type="text" placeholder="YYYY-MM-DD" value="${searchEndDate}" required />
                </div>

                <div>
                    <input type="submit" value="Search" />
                    <input type="button" value="Reset" />
                </div>
            </form>

            <hr>

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
                            <dt>Borrowing Data (All Libraries)</dt>
                            <dd><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;
                                <a href="">CSV</a>
                            </dd>
                            <dd><i class="fa fa-code" aria-hidden="true"></i>&nbsp;
                                <a href="">XML / JSON</a>
                            </dd>
                            <dt>Lending Data (All Libraries)</dt>
                            <dd><i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;
                                <a href="">CSV</a>
                            </dd>
                            <dd><i class="fa fa-code" aria-hidden="true"></i>&nbsp;
                                <a href="">XML / JSON</a>
                            </dd>
                        </dl>
                        </p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Library</th>
                                    <th scope="col" colspan="4">
                                        Borrowing
                                    </th>
                                    <th scope="col" colspan="4">
                                        Lending
                                    </th>
                                </tr>
                                <tr>
                                    <td></td>
                                    <th scope="col">ISO</th>
                                    <th scope="col">OCLC</th>
                                    <th scope="col">UC</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">ISO</th>
                                    <th scope="col">OCLC</th>
                                    <th scope="col">UC</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="library" items="${report.institutionReports}">
                                    <tr>
                                        <th scope="row">${library.name}</th>
                                        <td>${library.totalISOBorrowing}</td>
                                        <td>${library.totalOCLCBorrowing}</td>
                                        <td>${library.totalUCBorrowing}</td>
                                        <td>${library.totalBorrowing}</td>
                                        <td>${library.totalISOLending}</td>
                                        <td>${library.totalOCLCLending}</td>
                                        <td>${library.totalUCLending}</td>
                                        <td>${library.totalLending}</td>
                                    </tr>
                                </c:forEach>
                                <tr style="font-weight: bold;">
                                    <th scope="row">All Libraries</th>
                                    <td>${report.totalISOBorrowing}</td>
                                    <td>${report.totalOCLCBorrowing}</td>
                                    <td>${report.totalUCBorrowing}</td>
                                    <td>${report.totalBorrowing}</td>
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
    </body>
</html>