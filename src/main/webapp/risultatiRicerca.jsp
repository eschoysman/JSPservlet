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
                out.print(String.format("<div>%s: %s</div> Note: <div style=\"margin-left:5px; margin-bottom:10px\">%s</div>", logOperazioniANSC.getIdArchivio(), logOperazioniANSC.getDate(), logOperazioniANSC.getNote()));
            }
        %>
        
        
        
    </body>
</html>
