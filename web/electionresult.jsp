<%-- 
    Document   : electionresult
    Created on : Jan 2, 2020, 2:04:20 PM
    Author     : Shivin John
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="evoting.dto.CandidateDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="stylesheet/result.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/voteroptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <title>show candidate</title>
    </head>
    <body>

<%
       String userid=(String)session.getAttribute("userid");
       if(userid==null) 
       {
         response.sendRedirect("accessdenied.html");
         return;
       }
       StringBuffer displayBlock=new StringBuffer("<div id='subcandidate'>Voting Result</div>"
                 +"<div class='logout'><a href='login.html'>logout</a></div>");
       Integer totalvote=(Integer)request.getAttribute("votecount");
LinkedHashMap<CandidateDetails,Integer> result=(LinkedHashMap<CandidateDetails,Integer>)request.getAttribute("result");
       Set s=result.entrySet();
       Iterator it=s.iterator();
       displayBlock.append("<table class='table-striped' border='2'>");
       displayBlock.append("<tr style=background-color:black;><th>Candidate Id</th><th>Candidate Name</th><th>Party</th><th>Symbol</th><th>Voting Count</th><th>Vote % </th></tr>");
       while(it.hasNext())
        {
              Map.Entry<CandidateDetails,Integer> e=(Map.Entry)it.next();
              CandidateDetails c=e.getKey();
              //System.out.println(c.getCandidateName());
              displayBlock.append("<tr><td>"+c.getCandidateId()+"</td>"
                               +"<td>"+c.getCandidateName()+"</td>"
                               +"<td>"+c.getParty()+"</td>"
                               +"<td><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/></td>"
                               +"<td>"+e.getValue()+"</td>"
                               +"<td>"+((e.getValue()*100)/totalvote)+"</td></tr>");
                               
//              displayBlock.append("<div class='candidateprofile'>");
//              displayBlock.append("<span>Candidate Id:"+c.getCandidateId()+"</span>"
//                               +"<span>Candidate Name:"+c.getCandidateName()+"</span>"
//                               +"<span>Party:"+c.getParty()+"</span>"
//                               +"<span style='color:white;'>Symbol:</span><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/>"
//                               +"<span>Voting Count"+e.getValue()+"</span>"
//                               +"<span>Vote % "+((e.getValue()*100)/totalvote)+"</span>"
//                               +"</div>");
             
        }
        displayBlock.append("</table>");
        out.println(displayBlock);
%>