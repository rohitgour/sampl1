<%-- 
    Document   : deletecandidate
    Created on : Jan 8, 2020, 4:07:25 PM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String userid=(String)session.getAttribute("userid");
         if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }     
  boolean status=(Boolean)request.getAttribute("status");
    if(status==true)
        out.println("success");
    else if(status==false)
        out.println("failed");
    else
        out.println("error");
%>
