 <%--
    Document   : loginresponse
    Created on : Dec 21, 2019, 7:19:45 PM
    Author     : Shivin John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
  String userid=(String)request.getAttribute("userid");
  String result=(String)request.getAttribute("result");
  if(userid!=null && result!=null)
  {
      HttpSession sess=request.getSession();
      sess.setAttribute("userid", userid);
      boolean status=(Boolean)request.getAttribute("status");
      if(status==false)
      {
           out.println("not active");
      }
      else if(result.equalsIgnoreCase("Admin"))
      {
          String url="AdminControllerServlet;jsessionid="+session.getId();
          out.println(url);
      }
      else
      {
         String url="VoterControllerServlet;jsessionid="+session.getId();
         out.println(url);
      }
  }
  else
  {
      out.println("error");
  }
%>