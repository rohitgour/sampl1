<%-- 
    Document   : adminshowcandidate
    Created on : Dec 26, 2019, 8:17:43 PM
    Author     : Shivin John
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="evoting.dao.CandidateDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.CandidateDetails'%>
<%@page import='java.util.ArrayList'%>
<html>
    <head>
        <link href="stylesheet/showcandidate.css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">      
        <script src="jsscript/jquery.js"></script>
        <script src="jsscript/imagepreview.js"></script>
        <script src="jsscript/adminoptions.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <title>Update candidate</title>
        <style>
                 .image-preview
		{
			width: 300px;
			min-height: 100px;
			border: 2px solid #dddddd;
			margin-top: 15px;
			display: flex;
			align-items: center;
			justify-content: center;
			font-weight: bold;
			color: #cccccc;
		}
		.image-preview__image
		{
			display: none;
			width: 100%;
		}
        </style>
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
        ArrayList<String>cities = null;
        try 
        {
             cities = CandidateDao.getCity();    
        } 
        catch(SQLException ex) 
        {
             ex.printStackTrace();
        }
        CandidateDetails candidate=(CandidateDetails)request.getAttribute("candidate");
        displayBlock.append("<form method='POST' enctype='multipart/form-data' id='fileUploadForm2'>"
        +"<table align='center' cellspacing='10px'><tr><th>User Id:</th><td><input type='text' disabled='disabled' id='myInput' value='"+candidate.getUserId()+"'/></td></tr>"
        +"<tr><th>Candidate Name:</th><td><input type='text' id='cname' disabled='disabled' value='"+candidate.getCandidateName()+"'></td></tr>"
        +"<tr><th>City:</th><td><select id='scripts'>");
        for(String city:cities)
            { displayBlock.append("<option id='city' value='"+city+"'>"+city+"</option>");}
         displayBlock.append("<option value='"+candidate.getCity()+"' id='opt3' ></option>");
         displayBlock.append("</select></td></tr>"
         +"<tr><th>Party:</th><td><input type='text' id='party' value='"+candidate.getParty()+"'></td></tr>"
         +"<tr><td><input type='file' name='inpFile' id='inpFile' style='color:white'></td></tr></table></form>"
         +"<div class='input-group' class='image-preview' id='imagePreview'>"
         +"<img src='' title='New Symbol' alt='Image Preview' class='image-preview__image' style='width:300px;height:200px;'>"
         +"<span class='image-preview__default-text'>Image Preview</span></div>"
         +"</div>"
         +"<span><button class='example_a'  id='myBtn' style='margin-top:20px'>Update Candidate</button></span>"
         +"<span><button class='example_a' id='btnClear'  style='margin-left:20px'>Clear Text</button></span>"
         +"<div style='margin-top:20px' id='symbol'><img onload='loadImage()' src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/></div>");       
    }
      
     out.println(displayBlock);
%>
 </body>
</html>
