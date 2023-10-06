<%-- 
    Document   : login
    Created on : 6 ott 2023, 11:46:04
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
        <h1>Hello World!</h1>
        
        <form action="login" method="POST">
            
            <div>
                <% if(request.getParameter("loginerror") != null) {  %>
                
                    Wrong username or password
                <% 
                    }
                %>
                
            </div>
                    
            <label> username </label> <input id="username" name="username" type="text" />
            <label> password </label> <input id="password" name="password" type="password" />

            <input type="submit"/>
        </form>
    </body>
</html>
