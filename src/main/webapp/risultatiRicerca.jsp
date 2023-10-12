<%-- 
    Document   : risultatiRicerca
    Created on : 10 ott 2023, 10:19:35
    Author     : Lavoro
--%>

<%@page import="it.advancia.model.LogOperazioniANSC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Risultati Ricerca</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
        <%@include file="header/header.jsp"%>
        <br>
        <table class="tavolo">
            <tr><th colspan="5">Elenco Log Operazioni ANSC</th></tr>
            <tr>
                <th>
                    idArchivio
                </th> 
                <th>
                    idOperazioneANSC
                </th> 
                <th>
                    data
                </th> 
                <th>
                    note
                </th>
                <th>
                    Download
                </th>
            </tr>
            <c:forEach items="${requestScope.LogOperazioniANSC}" var="op" >
                <tr>
                    <td>${op.getIdArchivio()}</td>
                    <td>${op.getIdOperazioneANSC()}</td>
                    <td>${op.getData()}</td>
                    <td>${op.getNote()}</td>
                    <td><c:if test="${op.getFileName() != null && !op.getFileName().trim().isEmpty()}"><a href="/inserisci?id=${op.getIdArchivio()}" target="_blank">${op.getFileName()}</a></c:if></td>
                </tr>
            </c:forEach>
        </table>
        <div><a href="/ricerca.jsp">indietro</a></div>
    </body>
</html>
