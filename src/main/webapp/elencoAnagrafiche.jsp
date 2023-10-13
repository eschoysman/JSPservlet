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
        <br>
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
                    <td>${anag.getKey().getIdRiferimentoString()}</td>
                    <td>
                        <c:forEach items="anag.getValue()" var="logOperazioneANSC">
                            logOperazioneANSC.getIdArchivio()
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div><a href="/index.html">indietro</a></div>
    </body>
</html>
