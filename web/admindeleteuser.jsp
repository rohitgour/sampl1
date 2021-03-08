<%-- 
    Document   : adminshowcandidate
    Created on : Dec 26, 2019, 8:17:43 PM
    Author     : Shivin John
--%>

<%@page import="evoting.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.CandidateDetails'%>
<%@page import='java.util.ArrayList'%>
<html>
    <head>
        <link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <title>Delete candidate</title>
    </head>
    <body>
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
    if(result.equals("userIdList"))
    {
        
        ArrayList<String>userId=(ArrayList)request.getAttribute("userId");
        System.out.println("userId in JSP = "+userId);
        for(String c:userId)
        {
            displayBlock.append("<option value='"+c+"'>"+c+"</>");
        }
    }
    else if(result.equals("details"))
    {
       UserDetails user=(UserDetails)request.getAttribute("user");
       displayBlock.append("<div class='candidateprofile'>"
                +"<table align='center'>"
                +"<tr><th>User Id:</th><td>"+user.getUserid()+"</td></tr>"
                +"<tr><th>UserName:</th><td>"+user.getUsername()+"</td></tr>"
                +"<tr><th>City:</th><td>"+user.getCity()+"</td></tr>"
                +"<tr><th>Email Id:</th><td>"+user.getEmail()+"</td></tr>"
                +"<tr><th>Mobile No:</th><td>"+user.getMobile()+"</td></tr>"
                +"<tr><th>Address:</th><td>"+user.getAddress()+"</td></tr>"
                +"</table>"
                +"<center><button class='example_a' onclick='removeuser()' style='margin-top:20px'>Remove User</button></center>"
                + "</div>");
    }
     out.println(displayBlock);
%>
 </body>
</html>
