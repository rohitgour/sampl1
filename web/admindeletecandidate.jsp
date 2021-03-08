<%-- 
    Document   : adminshowcandidate
    Created on : Dec 26, 2019, 8:17:43 PM
    Author     : Shivin John
--%>

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
    if(result.equals("candidateList"))
    {
        ArrayList<String>candidateId=(ArrayList)request.getAttribute("candidateId");
        for(String c:candidateId)
        {
            displayBlock.append("<option value='"+c+"'>"+c+"</>");
        }
    }
    else if(result.equals("details"))
    {
        CandidateDetails candidate=(CandidateDetails)request.getAttribute("candidate");
        displayBlock.append("<div class='candidateprofile'>"
                +"<table align='center'>"
                +"<tr><th>User Id:</th><td>"+candidate.getUserId()+"</td></tr>"
                +"<tr><th>Candidate Name:</th><td>"+candidate.getCandidateName()+"</td></tr>"
                +"<tr><th>City:</th><td>"+candidate.getCity()+"</td></tr>"
                +"<tr><th>Party:</th><td>"+candidate.getParty()+"</td></tr>"
                +"<tr><th>Symbol:</th><td id='image'>"
                +"<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'"
                +"style='width:300px;height:200px;'/></td></tr>"
                +"</table>"
                +"<center><button class='example_a' onclick='removecandidate()' style='margin-top:20px'>Remove Candidate</button></center>"
                + "</div>");
    }
     out.println(displayBlock);
%>
 </body>
</html>
