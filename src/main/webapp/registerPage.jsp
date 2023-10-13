<%-- 
    Document   : register
    Created on : 6 ott 2023, 15:14:57
    Author     : Lavoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrazione utente</title>
    </head>
    <body>
        <h1>Registration</h1>

        <form action="/register" method="POST">

            <div>
                <%
                    if (request.getParameter("error") != null) {

                        if (request.getParameter("error").equals("alreadyregistered")) {
                %>

                L'utente e' gia' registrato

                <%      } else {
                %>
                            Errore generico

                <%      }
                    }
                %>
            </div>

            <div>
                <label/> username  <input name="username" type="input"/>
            </div>
            <div>
                <label/> password  <input name="password" type="input"/>
            </div>
            <div>
                <label/> data  <input name="birthDate" type="date"/>
            </div>

            <input type="submit"/>
        </form>

    </body>
</html>
