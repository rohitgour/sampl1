<%-- 
    Document   : showexception
    Created on : Dec 20, 2019, 11:12:09 AM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet"></link>
        <title>Exception Page</title>
    </head>
    <body>
        <br>
        <div class="candidate">VOTE FOR CHANGE</div>
        <br>
        <div class="subcandidate">Please Try Again Later</div>
        <%
            Exception ex=(Exception)request.getAttribute("exception");
            System.out.println(ex.getMessage());
            out.println(ex);
        %>
    </body>
</html>
