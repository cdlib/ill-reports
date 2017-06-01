<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
    <head>
        <title>CDL Discovery &amp; Delivery Data Warehouse</title>
        <link rel="stylesheet" href="/css/report.css" />
    </head>
    <body>
        <h1>UC Data Warehouse</h1>

        <form action="/home" method="post">
            <input name="from" type="date" placeholder="MM/DD/YYYY" />
            <input name="to" type="date" placeholder="MM/DD/YYYY" />
            <input type="submit" value="Update" />
        </form>

        <c:forEach var="campus" items="${campuses}">
            <hr>
            <h2 id="${campus.campusCode}">${campus.campusCode}</h2>
            <table>
                <caption>Borrowing Requests</caption>
                <tr>
                    <th scope="col">Borrowing Institution</th>
                    <th scope="col">ISO Partner</th>
                    <th scope="col">OCLC</th>
                    <th scope="col">UC</th>
                    <th scope="col">Total</th>
                </tr>
                <c:forEach var="institution" items="${campus.institutionReports}">
                    <tr>
                        <th scope="row">${institution.name}</th>
                        <td>${institution.totalISOBorrowing}</td>
                        <td>${institution.totalOCLCBorrowing}</td>
                        <td>${institution.totalUCBorrowing}</td>
                        <td>${institution.totalBorrowing}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <th scope="row">All Institutions</th>
                    <td>${campus.totalISOBorrowing}</td>
                    <td>${campus.totalOCLCBorrowing}</td>
                    <td>${campus.totalUCBorrowing}</td>
                    <td>${campus.totalBorrowing}</td>
                </tr>
            </table>
        </c:forEach>
    </body>
</html>