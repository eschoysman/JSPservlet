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
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        <form action="inseriscimulta" method="POST">
            
            <select name="idAnagrafica">
                <c:forEach items="${requestScope.anagrafiche}" var="anagrafica" >
                    
                    <option value="${anagrafica.getId()}">${anagrafica.getNome()} ${anagrafica.getCognome()}</option>
                    
                </c:forEach>
            </select>
            
            <select name="tipo">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                
            </select>

            <input name="importo" text="number"/>
            
            
            <input type="submit"/>
        </form>
        
    </body>
</html>
