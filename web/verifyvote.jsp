<%-- 
    Document   : verifyvote
    Created on : Dec 28, 2019, 7:14:28 PM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   String userid=(String)session.getAttribute("userid");
   boolean result=(boolean)request.getAttribute("result");
   if(userid!=null && result==true)
       out.println("success");
   else
       out.println("failed");
%>
