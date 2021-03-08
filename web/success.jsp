<%-- 
    Document   : success
    Created on : Dec 25, 2019, 1:23:57 PM
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
  out.println("success");
  
%>