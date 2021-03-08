<%-- 
    Document   : registrationresponse
    Created on : Dec 21, 2019, 10:54:10 AM
    Author     : Shivin John
--%>

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
    <script src="jsscript/voter.js"></script>
</head>

<body>
<%
    boolean result=(Boolean)request.getAttribute("result");
    System.out.println("Resgister result = "+result);
    boolean userfound=(Boolean)request.getAttribute("userfound");
    System.out.println("Resgister userfound = "+userfound);
   if(userfound==true)
   {
       out.println("uap");
   }
   else if(result==true)
   {
       out.println("success");
   }
   else
   {
       out.println("error");
   }
%> 
</body>
</html>
