<%-- 
    Document   : useractivated
    Created on : Jan 14, 2020, 12:06:17 PM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   boolean status=(Boolean)request.getAttribute("status");
    if(status==true)
        out.println("success");
    else if(status==false)
        out.println("failed");
    else
        out.println("error");
          
%>