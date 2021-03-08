function redirectseecandidate()
{
    swal("Voter!", "Redirecting to Show Candidate Page!", "success");
    setTimeout(function(){window.location="seecandidate.jsp";},3000);     
}
function redirectcastvote()
{
    swal("Voter!", "Redirecting to Voting Page!", "success");
    setTimeout(function(){window.location="addvote.jsp";},3000); 
}
function redirectseevote()
{
    swal("Voter!", "Redirecting to See Vote Page!", "success");
    setTimeout(function(){window.location="seevote.jsp";},3000); 
}
function redirectupdateprofile()
{
    swal("Voter!", "Redirecting to Update Profile Page!", "success");
    setTimeout(function(){window.location="update.jsp";},3000); 
}
function clearText()
{
    $('#result').html("");
}
function removecandidateForm()
{
    var contdiv=document.getElementById("result");
    var formdiv=document.getElementById("candidateform");
    if(formdiv!==null)
    {
        $('#candidateform').fadeOut("3500");
        contdiv.removeChild(formdiv);
    }
}
function seecandidate()
{   
       $.post("SeeCandidateControllerServlet",function(responseText){
          $("#result").append(responseText);
       });
}
function castvote()
{
    var data={vote:"vote"};
    $.post("SeeCandidateControllerServlet",data,function(responseText){
         $("#result").append(responseText);
    });
}
function addvote(a,b)
{
    
    var data={uid:a,
              cid:b};
    $.post("AddVoteControllerServlet",data,function(responseText){
        responseText=responseText.trim();
         if(responseText==='success')
         {     
              swal("Thank you for Voting!!","Your Vote Added Successfully!!", "success");
              setTimeout(function(){window.location="voteroptions.jsp";},3000);
          }
          else if(responseText==='failed')
          {
               swal("Failed!", "Something went wrong while voting!", "voting denied");
          }
    });
}
function seevote()
{
    var data={data:"result",
              admin:"admin"};
    $.post("ElectionResultControllerServlet",data,function(responseText){
        $("#result").append(responseText);
    });
    
}
function updateprofile()
{
    var data={data:"getform"};
    $.post("UpdateProfileControllerServlet",data,function(responseText){
       //responseText=responseText.trim();
        $('#result').append(responseText);
    });
}
var username,password,cpassword,city,address,adhar,email,mobile;
function addUser() 
{
    username=$("#username").val();
    password=$("#password").val();
    cpassword=$("#cpassword").val();
    city=$("#city").val();
    address=$("#address").val();
    adhar=$("#adhar").val();
    email=$("#email").val();
    mobile=$("#mobile").val();
    if(validateUser())
    {
        if(password !== cpassword)
        {
            swal("Error!", "password does not match!", "error");
            return;
        }
        else
        {
            if(checkEmail(email)===false)
                return;
            var data={ username: username,
            password: password,
            city: city,
            address: address,
            userid: adhar,
            email: email,
            mobile: mobile,
            data:"data"};
            $.post("UpdateProfileControllerServlet",data,function(responseText){
                responseText=responseText.trim();
    if(responseText==="success")
    {      
       swal("success!","Changes Saved Successfully!!", "success");
       //clearText();
       setTimeout(function(){window.location="voteroptions.jsp";},4000);
    }
    else if(responseText==="failed")
    {
        swal("Error","Updation Failed ! Try again after sometimes","error");
        setTimeout(function(){window.location="voteroptions.jsp";},3000);
    }
            });
        }
    }
}
function validateUser()
{
    if (username === "" || password === "" || cpassword === "" || city === "" || address === "" || adhar === "" || email === "" || mobile === "")
    {
        swal("Error!", "All fields are mandatory!", "error");
        return false;
    }
    else 
    {
        return true;
    }
}
function checkEmail(email)
{
var atposition=email.indexOf("@");  
var dotposition=email.lastIndexOf(".");  
if (atposition<1 || dotposition<atposition+2 || dotposition+2>=email.length)
{ 
    swal("Error!", "please enter valid email!", "error");
    return false;  
} 
  return true;
}