<%-- 
    Document   : updateprofile
    Created on : Jan 4, 2020, 5:51:24 PM
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
            boolean result=(Boolean)request.getAttribute("result");
            if(result==true)
               out.println("success");
            else
                out.println("failed");    
    
%>