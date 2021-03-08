<%-- 
    Document   : adminshowcandidate
    Created on : Dec 26, 2019, 8:17:43 PM
    Author     : Shivin John
--%>

<%@page import="evoting.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.CandidateDetails'%>
<%@page import='java.util.ArrayList'%>
<%
         String userid=(String)session.getAttribute("userid");
         if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }    
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock=new StringBuffer("");
    if(result.equals("details"))
    {
        ArrayList<UserDetails> userList=(ArrayList<UserDetails>)request.getAttribute("user");
         if(userList.isEmpty())
             out.println("<h3 style='color:red;'>No deactive user present</h3>");
         else
         {
             displayBlock.append("<table border='2' align='center' cellpadding='10' cellspacing='10'>"
             +"<tr align='center'><th>User Id</th><th>UserName</th><th>City</th><th>Email Id</th><th>Mobile No</th><th>Address</th><th>Active</th></tr>");
     
             for(UserDetails user:userList)
             {       
                displayBlock.append("<tr>"
                +"<td>"+user.getUserid()+"</td>"
                +"<td>"+user.getUsername()+"</td>"
                +"<td>"+user.getCity()+"</td>"
                +"<td>"+user.getEmail()+"</td>"
                +"<td>"+user.getMobile()+"</td>"
                +"<td>"+user.getAddress()+"</td>"
                +"<td><button class='btnActivate' onclick='activateuser("+user.getUserid()+")'>Activate</button></td>"
                +"</tr>");       
             }
             displayBlock.append("</table>");
             out.println(displayBlock);
        }
    }
%>
