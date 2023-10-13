<%-- 
    Document   : elencoMulte
    Created on : 13 ott 2023, 17:21:57
    Author     : Lavoro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Risultati Ricerca</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
        <%@include file="header/header.jsp"%>
        <table class="tavolo">
            <tr>
                <th colspan="2">Totale: ${requestScope.totaleMulte} €</th>
            </tr>
            <tr>
                <th class="label">tipo</th>
                <th class="label">importo</th>
            </tr>
            <c:if test="${requestScope.listaMulte==null || requestScope.listaMulte.isEmpty()}">
                <tr>
                    <td colspan="2">Nessuna multa presente</td>
                </tr>
            </c:if>
            <c:forEach items="${requestScope.listaMulte}" var="multa">
            <tr>
                <td>${multa.getTipo()}</td>
                <td>${multa.getImporto()} €</td>
            </tr>            
            </c:forEach>

            
        </table>        

</html>
