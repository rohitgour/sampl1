<%-- 
    Document   : failure
    Created on : Dec 25, 2019, 1:30:49 PM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String userid=(String)session.getAttribute("userid");
  if(userid==null)
  {
     response.sendRedirect("accessdenied.html");
     return;
  }
  out.println("failed");
  
%>