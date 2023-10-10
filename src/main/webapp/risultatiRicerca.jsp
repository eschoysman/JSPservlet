<%-- 
    Document   : risultatiRicerca
    Created on : 10 ott 2023, 10:19:35
    Author     : Lavoro
--%>

<%@page import="java.util.List"%>
<%@page import="it.advancia.model.LogOperazioniANSC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        <%
        
            List<LogOperazioniANSC> logOperazioniANSCList = (List<LogOperazioniANSC>) request.getAttribute("LogOperazioniANSC");
            
            for(LogOperazioniANSC logOperazioniANSC : logOperazioniANSCList ){
                out.print(String.format("%s: %s", logOperazioniANSC.getIdArchivio(), logOperazioniANSC.getDate()));
            }
        %>
        
        
        
    </body>
</html>
