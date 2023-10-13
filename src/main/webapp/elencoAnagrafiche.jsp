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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
            var idRiferimentiAll = [];
            var idRiferimentiUsati = {};
            $.ajax("/idriferimenti?all").done(
                function(res){
                    res.forEach((item)=>{
                        idRiferimentiAll.push(item);
                    });
                });
            function setIdRiferimentoUsato(idAnagrafica,idRiferimento) {
                idRiferimentiUsati[idAnagrafica] = idRiferimento;
            }
        </script>
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
                <script>setIdRiferimentoUsato(${anag.getKey().getId()},"${anag.getKey().getIdRiferimento()}");</script>
                <tr>
                    <td>${anag.getKey().getId()}</td>
                    <td>${anag.getKey().getNome()} ${anag.getKey().getCognome()}</td>
                    <td>${anag.getKey().getDataNascita()}</td>
                    <td>${anag.getKey().getLuogoNascita()}</td>
                    <td>
                        <select id="idRiferimento_${anag.getKey().getId()}"
                                name="idRiferimento_${anag.getKey().getId()}"
                                onchange="aggiornaIdRiferimento(${anag.getKey().getId()},${anag.getKey().getIdRiferimento()})">
                        </select>
                        ${anag.getKey().getIdRiferimento()}</td>
                    <td>
                        <c:forEach items="${anag.getValue()}" var="logOperazioneANSC">
                            ${logOperazioneANSC.getIdArchivio()}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div><a href="/index.html">indietro</a></div>
        <script>
            function aggiornaIdRiferimento(idAnagrafica,idRiferimento) {
                console.log("aggiornaIdRiferimento("+idAnagrafica+","+idRiferimento+")");
                // TODO $$$ valorizzare tutti i select delle anagrafiche con le options giuste
                listSelectIdRiferimento = document.getElementsByName("select[name^=idRiferimento_]");
                for(const select of listSelectIdRiferimento) {
                    var idAnagrafe = select.getAttribute('id').replace("idRiferimento_");
                    // rimuovo tutte le opzioni presenti per valorizzare con quelle disponibili
                    let options = select.getElementsByTagName('option');
                    for(var i=options.length; i--;) {
                        select.removeChild(options[i]);
                    }
                    // per ogni idRiferimento disponibile, aggiungo una option
                    for(const idRiferimento of idRiferimentiAll) {
                        var isIdRiferimentoLibero = true;
                        for(const [key, value] of Object.entries(idRiferimentiUsati)) {
                            if(key===idAnagrafe) {
                                select.innerHTML += "<option selected value="+idRiferimento+">"+idRiferimento+"</option>";
                            }
                            else if(value===idRiferimento) {
                                isIdRiferimentoLibero = false;
                            }
                        }
                        if(isIdRiferimentoLibero) {
                            select.innerHTML += "<option value="+idRiferimento+">"+idRiferimento+"</option>";
                        }
                    }
                }
                if(idAnagrafica && idRiferimento) {
                    setIdRiferimentoUsato(idAnagrafica,idRiferimento);
                }
            }
            aggiornaIdRiferimento();
        </script>
    </body>
</html>
