<%@page contentType="text/html" pageEncoding="UTF-8" import="evoting.dto.CandidateDto"%>
<html>
    <head>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vote Denied</title>
    </head>
    <body>
        <%
            String userid=(String)session.getAttribute("userid");
            System.out.println("Vote Denied Jsp");
            if(userid==null)
            { 
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            String isVote=(String)request.getAttribute("notvoted");
            if(isVote!=null)
                out.println("<h1 style='color:red';>You cannot see the result..Because you haven't vote till now..</h1>");
            else
           {
               CandidateDto can=(CandidateDto)request.getAttribute("candidate");
             StringBuffer displayBlock=new StringBuffer("<br><div class='candidateprofile'><b style='color:red;'>Sorry! You have already casted your VOTE to this candidate.</b>");
             displayBlock.append("<p>Candidate Id:"+can.getCandidateId()+"</p>"
                               +"<p>Candidate Name:"+can.getCandidateName()+"</p>"
                               +"<p>Party:"+can.getParty()+"</p>"
                               +"<h1 style='color:white;'>Symbol:</h1><img src='data:image/jpg;base64,"+can.getSymbol()+"' style='width:300px;height:200px;'/>"
                               +"</div>");         
            out.println(displayBlock);  
            }
        %>
    </body>
</html>
