<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
    <head>
        <title>CDL Discovery &amp; Delivery Data Warehouse</title>
        <link rel="stylesheet" href="/css/report.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <br>
            <p class="text-center"><img src="CDL_Logo.png" style="width: 20%" alt="University of California - California Digital Library" /></p>
            <h1 class="page-header">UC ILL Reports</h1>

            <h2>Search</h2>
            <form action="/" method="post" class="form-inline">

                <div class="form-group">
                    <label for="campus" class="">Campus</label>
                    <select id="campus" name="campus">
                        <option value="">All Campuses</option>
                        <c:forEach var="campus" items="${campuses}">
                            <option value="${campus}">${campus}</option>
                        </c:forEach>
                    </select>
                </div>

                <fmt:formatDate value="${reportStartDate}" var="fmtStartDate" pattern="MM/dd/yyyy" />
                <fmt:formatDate value="${reportEndDate}" var="fmtEndDate" pattern="MM/dd/yyyy" />

                <div class="form-group">
                    <label for="from">Begin Date</label>
                    <input id="from" name="from" type="date" placeholder="MM/DD/YYYY" value="${fmtStartDate}" />
                </div>

                <div class="form-group">
                    <label for="to">End Date</label>
                    <input id="to" name="to" type="date" placeholder="MM/DD/YYYY" value="${fmtEndDate}" />
                </div>

                <div>
                    <input type="submit" value="Search" />
                    <input type="button" value="Reset" />
                </div>
            </form>

            <hr>

            <section>
                <h2>UC Davis (May 1, 2017 - June 1, 2017)</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Library</th>
                            <th scope="col">
                                Borrowing
                                <span class="pull-right">
                                    Download:
                                    &nbsp;
                                    <i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;<a href="">CSV</a>
                                    <i class="fa fa-code" aria-hidden="true"></i>&nbsp;<a href="">XML/JSON</a>
                                </span>
                            </th>
                            <th scope="col">
                                Lending
                                <span class="pull-right">
                                    Download:
                                    &nbsp;
                                    <i class="fa fa-file-text-o" aria-hidden="true"></i>&nbsp;<a href="">CSV</a>
                                    <i class="fa fa-code" aria-hidden="true"></i>&nbsp;<a href="">XML/JSON</a>
                                </span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">UCD Law Library</th>
                            <td>
                                <div class="well">
                                    {borrowing summary}<br>
                                    ISO/OCLC/UC/Total
                                </div>
                            </td>
                            <td>
                                <div class="well">
                                    {lending summary}<br>
                                    ISO/OCLC/UC/Total
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">UCD Shields Library</th>
                            <td>
                                <div class="well">
                                    {borrowing summary}<br>
                                    ISO/OCLC/UC/Total
                                </div>
                            </td>
                            <td>
                                <div class="well">
                                    {lending summary}<br>
                                    ISO/OCLC/UC/Total
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </section>
        </div>
    </body>
</html>