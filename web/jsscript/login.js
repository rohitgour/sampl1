var userid,password;
function connectUser()
{
    userid=$("#username").val();
    password=$("#password").val();
    if(validateUser()===false)
    {
        swal("Access Denied!","Please enter userid/password!","error");
        return;
    }
    var data={
        userid:userid,
        password:password
    };
    $.post("LoginControllerServlet",data,processResponse);
}
function processResponse(responseText)
{
    
    responseText=responseText.trim();
    window.alert(responseText);
   // document.write(responseText);
    if(responseText==='error')
    {
       swal("Access Denied!","Please enter  valid userid/password!","error"); 
    }
    else if(responseText.indexOf("jsessionid")!==-1)
    {
        swal("success","Login Accepted","success");
        setTimeout(function(){
                
                window.location=responseText;
            },3000);
    }
    else if(responseText==='not active')
    {
        swal("error","Currently you are not a active user to get login.","error");
        setTimeout(3000);
    }
    else
    {
      swal("Access Denied!","some problem occured please try again later","error");   
    }
}
function validateUser()
{
    if(userid==="" || password==="")
        return false;
    return true;
}

