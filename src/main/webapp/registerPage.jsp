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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registration</h1>
        
        <form action="/register" method="POST">
            <div>
            <label/> username  <input type="input"/>
            </div>
            <div>
            <label/> password  <input type="input"/>
            </div>
            <div>
            <label/> data  <input type="date"/>
            </div>
            
            <input type="submit"/>
        </form>
        
    </body>
</html>
