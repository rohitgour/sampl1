<%-- 
    Document   : adminactions
    Created on : Dec 23, 2019, 7:00:32 PM
    Author     : Shivin John
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/voteroptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <title>Update Profile Page</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
           out.println("<body onload='updateprofile()'<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
                       "<div class='subcandidate'>Update Profile Page</div><br><br>"+
                       "<div class='logout'><a href='login.html'>logout</a></div></div>"+
                       "<div class='container'>"+ 
                       "<div align='center' id='result'></div>"+
                       "</div></body>");
        %>
    </body>
</html>
