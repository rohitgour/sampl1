<%-- 
    Document   : index
    Created on : Jan 4, 2020, 1:40:39 PM
    Author     : Shivin John
--%>

<%@page import="evoting.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="stylesheet/main.css" rel="stylesheet">
    <script src="jsscript/voteroptions.js"></script>
</head>

<body>
    <%
            String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
            UserDetails user=(UserDetails)request.getAttribute("user");
            StringBuffer displayBlock=new StringBuffer("<div class='page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins'>"
            +"<div class='wrapper wrapper--w780'>"
            +"<div class='card card-3'>"
            +"<div class='card-heading'></div>"
            +"<div class='card-body'>"
            +"<h2 class='title'>Update Profile</h2>"
            +"<form method='POST'>"
            +"<div class='input-group'>"
            +"<b>Cannot Change Adhar no</b><input class='input--style-3' disabled='disabled' type='text' placeholder='Adhar number' value='"+user.getUserid()+"' id='adhar'></div>"
            +"<div class='input-group'>"
            +"<b>Change Name?</b><input class='input--style-3' type='text' placeholder='Name' value='"+user.getUsername()+"' id='username'></div>"
            +"<div class='input-group'>"
            +"<b>Change Password?</b><input class='input--style-3' type='text' placeholder='Password' value='"+user.getPassword()+"'id='password'></div>"
            +"<div class='input-group'>"
            +"<b>Confirm Password</b><input class='input--style-3' type='text' placeholder='Confirm Password' value='"+user.getPassword()+"'id='cpassword'></div>"
            +"<div class='input-group'>"
            +"<b>Change City?</b><input class='input--style-3' type='text' placeholder='City' value='"+user.getCity()+"'id='city'></div>"
            +"<div class='input-group'>"
            +"<b>Change Email Id?</b><input class='input--style-3' type='email' placeholder='Email' value='"+user.getEmail()+"' id='email'></div>"
            +"<div class='input-group'>"
            +"<b>Change Mobile number?</b><input class='input--style-3' type='text' placeholder='Mobile no.' value='"+user.getMobile()+"' id='mobile'></div>"
            +"<div class='input-group'>"
            +"<b>Change Address?</b><input class='input--style-3' type='text' placeholder='Address' value='"+user.getAddress()+"' id='address'></div>"
            +"<div class='p-t-10'>"
            +"<button class='btn btn--pill btn--green' type='button' onclick='addUser()'>Save Changes</button></div>"
            +"</form>"
           +"</div>"
         +"</div>"
       +"</div>"
    +"</div>");

 out.println(displayBlock);
%>
</body>
</html>
