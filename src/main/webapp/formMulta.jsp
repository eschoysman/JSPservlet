<%-- 
    Document   : formMulte
    Created on : 13 ott 2023, 15:32:42
    Author     : Lavoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
        <%@include file="header/header.jsp"%>

        <form action="inseriscimulta" method="POST">
            <table class="tavolo">
                <tr>
                    <th class="label">Utente</th>

                    <td>
                        <select name="idAnagrafica">
                            <c:forEach items="${requestScope.anagrafiche}" var="anagrafica" >

                                <option value="${anagrafica.getId()}">${anagrafica.getNome()} ${anagrafica.getCognome()}</option>

                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <th class="label">tipo</th>
                    <td>
                <select name="tipo">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                </select>
                        </td>
</tr>
<tr>
    <th class="label">importo</th>
    <td>
                <input name="importo" text="number"/>
</td>
</tr>
<tr><th colspan="2"> <input type="submit"/></th></tr>
            </table>
        </form>

    </body>
</html>
