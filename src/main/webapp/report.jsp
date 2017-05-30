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
            <h2 id="${campus.code}">${campus.code}</h2>
            <table>
                <caption>Borrowing</caption>
                <tr>
                    <th scope="col">Institution</th>
                    <th scope="col">ISO Partner</th>
                    <th scope="col">OCLC</th>
                    <th scope="col">UC</th>
                    <th scope="col">Total</th>
                </tr>
                <c:forEach var="institution" items="${campus.institutions}">
                    <tr>
                        <th scope="row">${institution.name}</th>
                        <td>${institution.totalISO}</td>
                        <td>${institution.totalOCLC}</td>
                        <td>${institution.totalUC}</td>
                        <td>${institution.total}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <th scope="row">All Institutions</th>
                    <td>${campus.totalISO}</td>
                    <td>${campus.totalOCLC}</td>
                    <td>${campus.totalUC}</td>
                    <td>${campus.total}</td>
                </tr>
            </table>
        </c:forEach>
    </body>
</html>