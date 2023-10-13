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
        <title>Elenco Anagrafiche</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
        <%@include file="header/header.jsp"%>
        <form action="/anagrafiche" method="POST">
            <table class="tavolo">
                <tr><th colspan="6">Elenco Anagrafiche</th></tr>
                <tr>
                    <th>
                        idAnagrafica
                    </th> 
                    <th>
                        Nome Cognome
                    </th> 
                    <th>
                        Data di nascita
                    </th> 
                    <th>
                        Luogo di nascita
                    </th>
                    <th>
                        idRiferimento
                    </th>
                    <th>
                        Lista Log Operazioni ANSC
                    </th>
                </tr>
                <c:forEach items="${requestScope.anagraficheConLog.entrySet()}" var="anag" >
                    <tr>
                        <td>${anag.getKey().getId()}</td>
                        <td>${anag.getKey().getNome()} ${anag.getKey().getCognome()}</td>
                        <td>${anag.getKey().getDataNascita()}</td>
                        <td>${anag.getKey().getLuogoNascita()}</td>
                        <td>
                            <select id="idRiferimento_${anag.getKey().getId()}"
                                    name="idRiferimento_${anag.getKey().getId()}">
                            <c:forEach items="${requestScope.idRiferimentoAll}" var="idRiferimento">
                                <c:choose>
                                    <c:when test="${idRiferimento.equals(anag.getKey().getIdRiferimento())}">
                                        <option selected value="${idRiferimento}">${idRiferimento}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${idRiferimento}">${idRiferimento}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>                            
                            </select>
                        </td>
                        <td>
                            <c:forEach items="${anag.getValue()}" var="logOperazioneANSC">
                                ${logOperazioneANSC.getIdArchivio()}
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Salva"/>
            <c:if test="${requestScope.error}"><span>${requestScope.error}</span></c:if>
            <c:if test="${requestScope.esito}"><span>${requestScope.esito}</span></c:if>
        </form>
        <br>
        <div><a href="/index.html">indietro</a></div>
    </body>
</html>
