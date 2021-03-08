<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,evoting.dto.CandidateDto"%>
<html>
    <head>
        <link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
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
          StringBuffer displayBlock=new StringBuffer("<div id='subcandidate'>Whom do you want to vote ?</div>"
                 +"<div class='logout'><a href='login.html'>logout</a></div>");
          ArrayList<CandidateDto> candidate=(ArrayList)request.getAttribute("candidateList");
          if(candidate.isEmpty()==true)
          {
             CandidateDto can=(CandidateDto)request.getAttribute("candidate");
             displayBlock.append("<div class='candidateprofile'><b>Sorry! You have already casted your VOTE to this candidate.</b>");
             displayBlock.append("<p>Candidate Id:"+can.getCandidateId()+"</p>"
                               +"<p>Candidate Name:"+can.getCandidateName()+"</p>"
                               +"<p>Party:"+can.getParty()+"</p>"
                               +"<h1 style='color:white;'>Symbol:</h1><img src='data:image/jpg;base64,"+can.getSymbol()+"' style='width:300px;height:200px;'/>"
                               +"</div>");
          }
          else
         {
          for(CandidateDto c:candidate)
          {
            displayBlock.append("<div class='candidateprofile'>");
            displayBlock.append("<p>Candidate Id:"+c.getCandidateId()+"</p>"
                               +"<p>Candidate Name:"+c.getCandidateName()+"</p>"
                               +"<p>Party:"+c.getParty()+"</label><br/></div></p>"
                               +"<h1 style='color: #009999;'>Symbol:</h1><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/>"
                               +"<p><button class='example_a' align='center' onclick=addvote('"+session.getAttribute("userid")+"','"+c.getCandidateId()+"')"+";>Click To Vote Me</button></p></div>");
          }
         }
          out.println(displayBlock);
      %>
  </div>
</div> 
 </body>
</html>
