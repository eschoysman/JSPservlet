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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="static/style.css" />
    </head>
    <body>
                <%@include file="static/modal/modal.jsp"%>

        
        <%@include file="header/header.jsp"%>

        <form action="ricerca" method="POST">
        <div style="margin-top:30px">
        <table class="tavolo">


            <tr >

                <th class="title" colspan="4">
                    <div class="plus-icon">+</div>
                    Ricerca Log Operazioni Particolari
                </th>

            </tr>

            <tr class="whitespace"><th colspan="4"></th></tr>

            <tr>
                <td class="label">
                    idArchivio
                </td>
                <td>
                    <input name="idArchivio" type="text" />
                </td>
                <td class="label">
                    id Operazione ANSC
                </td>
                <td>
                    <input name="idOperazioneANSC" type="text" />

                </td>
            </tr>

            <tr>
                <td class="label">
                    id Operazione Comune
                </td>
                <td>
                    <input name="idOperazioneComune" type="text" />
                </td>
                <td class="label">
                    id Riferimento
                </td>
                <td>
                    <input name="idRiferimento" type="text" />

                </td>
            </tr>

            <tr>
                <td class="label">
                    Codice operazione ANSC
                </td>
                <td colspan="3">
                    <input name="codiceOperazioneANSC" type="text" />
                </td>



            </tr>

            <tr>
                <td class="label">
                    Cognome Nome
                </td>
                <td colspan="3">
                    <input name="cognome" type="text" />
                    <input name="nome" type="text" />


                </td>



            </tr>



            <tr>
                <td class="label">
                    Eseguita
                </td>
                <td>
                    <select>

                    </select>               
                </td>
                <td class="label">
                    Note
                </td>
                <td>
                    <input name="note" type="text" />

                </td>
            </tr>
            <tr>
                <td class="label">
                    Operatore
                </td>
                <td>
                    <select>

                    </select>                
                </td>
                <td class="label">
                    id Atto
                </td>
                <td>
                    <input name="idAtto" type="text" />

                </td>
            </tr>
            <tr>
                <td class="label">
                    Data da
                </td>
                <td>
                    <input type="date" name="dataFrom" />

                </td>
                <td class="label">
                    Data a
                </td>
                <td>
                    <input type="date" name="dataTo"/>


                </td>
            </tr>
            <tr>
                <td class="label">
                    Id Operazione Ann ANSC
                </td>
                <td>
                    <input name="idOperazioneAnnANSC" type="text" />
                </td>
                <td class="label">
                    id Operazione Ann Comune
                </td>
                <td>
                    <input name="idOperazioneAnnComune" type="text" />

                </td>
            </tr>



        </table>
        <table class="tavolo">

            <tr><th class="label"> Numeri documenti da visualizzare 

                    <select>

                        <option>10</option>
                    </select>

                </th> <th><input type="submit" value="Conferma"/></th></tr>

        </table>
        </form>
        </div>
    </body>
</html>
