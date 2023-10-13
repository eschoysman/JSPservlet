<%-- 
    Document   : ricerca
    Created on : 9 ott 2023, 10:51:50
    Author     : Lavoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ricerca Multe</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
        <%@include file="header/header.jsp"%>

        <div style="margin-top:30px">
            <form action="ricercaMulte" method="GET">
                <table class="tavolo">
                    <tr >
                        <th class="title" colspan="2">
                            Ricerca Multe
                        </th>
                    </tr>
                    <tr class="whitespace"><th colspan="2"></th></tr>
                    <tr>
                        <td class="label">
                            <label for="idUtente">Utente: </label>
                            <select name="idUtente">
                                <option value=""></option>
                                <c:forEach items="${requestScope.anagrafiche}" var="anagrafica" >
                                    <option value="${anagrafica.getId()}">${anagrafica.getNome()} ${anagrafica.getCognome()}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="label">
                            <input type="submit" value="Cerca"/>
                        </td>
                    </tr>
                </table>
        </form>
    </div>
</body>
</html>
