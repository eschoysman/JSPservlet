<%-- 
    Document   : risultatiRicerca
    Created on : 10 ott 2023, 10:19:35
    Author     : Lavoro
--%>

<%@page import="java.util.List"%>
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
            <tr><th colspan="4">Elenco Log Operazioni ANSC</th></tr>
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
            </tr>
            <c:forEach items="${requestScope.LogOperazioniANSC}" var="op" >
                <tr>
                    <td>${op.getIdArchivio()}</td>
                    <td>${op.getIdOperazioneANSC()}</td>
                    <td>${op.getDate()}</td>
                    <td>${op.getNote()}</td>
                </tr>
            </c:forEach>

        </table>


    </body>
</html>
