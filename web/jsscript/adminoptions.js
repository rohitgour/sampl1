function redirectadministratorpage()
{
    swal("Admin!", "Redirecting to Admin Page!", "success");
    setTimeout(function(){window.location="adminactions.jsp";},3000);    
}
function managecandidate()
{
     swal("Admin!", "Redirecting to Candidate Management!", "success");
     setTimeout(function(){window.location="managecandidate.jsp";}, 3000);
}
function manageuser()
{
     swal("Admin!", "Redirecting to Voter Management!", "success");
     setTimeout(function(){window.location="manageuser.jsp";}, 3000);
}
function redirectvotingpage()
{
     swal("Admin!", "Redirecting to Voting Page!", "success");
     setTimeout(function(){window.location="voteroptions.jsp";}, 3000);
}
function showaddcandidateform()
{
removecandidateForm();
var newdiv=document.createElement("div");
newdiv.setAttribute("id","candidateform");
newdiv.setAttribute("float","left");
newdiv.setAttribute("padding-left","12px");
newdiv.setAttribute("border","solid 2px red");
newdiv.innerHTML="<h3>Add New Candidate</h3>";
newdiv.innerHTML=newdiv.innerHTML+"<form method='POST' enctype='multipart/form-data' id='fileUploadForm'>\n\
<table><tr><th>Candidate Id:</th><td><input type='text' id='cid'></td></tr>\n\
<tr><th>User Id:</th><td><input type='text' id='uid' onkeypress='return getdetails(event)'></td></tr>\n\
<tr><th>Candidate Name:</th><td><input type='text' id='cname'></td></tr>\n\
<tr><th>City:</th><td><select id='city'></select></td></tr>\n\
<tr><th>Party:</th><td><input type='text' id='party'></td></tr>\n\
<tr><td colspan='2'><input type='file' name='files' id='inpFile' value='Select Image'></td></tr>\n\
<tr><th><input type='button' value='Add Candidate' onclick='addcandidate()' id='addcnd'></th>\n\
<th><input type='reset' value='Clear' onclick='clearText()'></th></tr></table></form>";
newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
var addcand=$("#result")[0];
addcand.appendChild(newdiv);
$("#candidateform").hide();
$("#candidateform").fadeIn("3500");
 data={id:"getid"};    
 $.post("AddCandidateControllerServlet",data,function(responseText)
 {
     $("#cid").val(responseText);
     $('#cid').prop("disabled",true);
 });
}

function addcandidate()
{
    
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var cid=$("#cid").val();
    var cname=$("#cname").val();
    var city=$("#city").val();
    var party=$("#party").val();
    var uid=$("#uid").val();
    var img=document.getElementById("inpFile").value;
    //document.write(img.value);
     if(validateAddCandidate(cid,cname,party,uid,img)===false)
    {
        return;
    }
    data.append("cid",cid);
    data.append("uid",uid);
    data.append("cname",cname);
    data.append("party",party);
    data.append("city",city);
    $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "AddNewCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
           success:function(data){
          
       str=data+"....";
       swal("Admin!",str,"success");
       setTimeout(function(){showaddcandidateform();},3000);
       },
    error:function(e){
       swal("Admin!",e,"error");
       }
     });
}
function getdetails(e)
{
    if (e.keyCode === 13) {
       data={uid:$("#uid").val()};
       $.post("AddCandidateControllerServlet",data,function(responseText){
         responseText=responseText.trim();
          var i=responseText.lastIndexOf(",");
	  $('#city').empty();
          $('#city').append(responseText.substring(0,i)); 
          var uname= responseText.substring(i+1,responseText.length);
	  if(uname==="wrong")
		swal("Wrong Adhar!","Adhar no not found in DB","error");
	else{
         $('#cname').val(uname);
         $("#cname").prop("disabled",true);
	    }	
         
            });
    }
} 

function showcandidate()
{
    removecandidateForm();   
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","left");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Show Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Select Your Adhar Card Number:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addPrd=document.getElementById("result");
    addPrd.appendChild(newdiv);
    data={data:"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
       $('#cid').append(responseText);
    });
    $('#cid').on('change',function(){
       var cid=$(this).children("option:selected").val();
       data={data:cid};
       $.post("ShowCandidateControllerServlet",data,function(responseText){
          clearText();
          $('#addresp').append(responseText);
       });
    });
   
}
function clearText()
{ 
    $('#addresp').html("");
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
function showupdatecandidateform()
{
    removecandidateForm();
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","left");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Update Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<h2 style='color:white;font-weight:bold;margin-top:30px;'>Select Candidate ID To Update</h2><select id='cid'>Candidate Id</select>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    $('#result').append(newdiv);
    data={data:"cid"};
    $.post("ShowCandidateControllerServlet",data,function(responseText){
       $('#cid').append(responseText);
    });
    
    $('#cid').on('change',function(){
       var cid=$(this).children("option:selected").val();
       data={data:cid,
             detail:"getdata"};
       $.post("ShowCandidateControllerServlet",data,function(responseText){
             clearText();
          $('#addresp').append(responseText);
       });
    });
}
var party;
$("#myBtn").click(function(){
    var form = $('#fileUploadForm2')[0];
    var data = new FormData(form);
    var city=$("#scripts option:selected" ).text();
    party=$("#party").val();
    var uid = $("#myInput").val();
    data.append("party",party);
    data.append("city",city);
    data.append("uid",uid);
    //document.write("<br>Userid = "+uid+"cname = "+cname+"<br>party = "+party+"<br>city = "+city);
    
    if(validateUser())
    {
            $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "UpdateCandidateControllerServlet",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
           success:function(data){
          
       str=data+"....";
       swal("Voter!","Record Update Successfully!!","success");
       setTimeout(showupdatecandidateform(),3000);
       },
       error:function(e){
       swal("Voter!",e,"error");
       }

       });
    }   
     });
function validateUser()
{
    if(party==="")
    {
      swal("error!","You cannot leave Party field blank!", "error");
        return false;
    }
    else 
        return true;
}
function loadImage(){
   var sel=document.getElementById('scripts');
   var displaytext=sel.querySelector('#opt3').value;
   var opt;
    for (var i = 0;i<sel.options.length;i++) {
        opt = sel.options[i];
        if(opt.value===displaytext){
        	sel.options[i].selected=true;
        	sel.options[sel.options.length-1].style.display='none';
                break;
         }
        }
}
function deletecandidate()
{
    removecandidateForm();   
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","left");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Show Candidate</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Select Candidate Id:</div><div><select id='cid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addPrd=document.getElementById("result");
    addPrd.appendChild(newdiv);
    var data={data:"cid"};
    $.post("ShowCandidateToDeleteControllerServlet",data,function(responseText){
       $('#cid').append(responseText);
    });
    $('#cid').on('change',function(){
       var cid=$(this).children("option:selected").val();
       data={data:cid};
       $.post("ShowCandidateToDeleteControllerServlet",data,function(responseText){
          clearText();
          $('#addresp').append(responseText);
       });
    });
}
function removecandidate()
{
    
       var cid=$('#cid').children("option:selected").val();
       data={cid:cid,
             remove:'remove'};
       $.post("DeleteCandidateControllerServlet",data,function(responseText){
       
       responseText=responseText.trim();
       if(responseText==='error'){
               swal("Error","Deletion Failed ! Try again","error");
            }
            else if(responseText==="failed")   
           {
               swal("Error!", "Sorry! Deletion failed dueto server issue.Try Again!", "error");
           }
            else{
                  swal("success!","Candidate Removed Successfully!", "success");
                  setTimeout(3000);
                  clearText();
                 }
            });
   
    $('#cid').empty();
    var data={data:"cid"};
    $.post("ShowCandidateToDeleteControllerServlet",data,function(responseText){
       $('#cid').append(responseText);
    });
}
function electionresult()
{
    var data={data:"result",
              admin:"admin"};
    $.post("ElectionResultControllerServlet",data,function(responseText){
        $("#result").append(responseText);
    });
    
}
function validateAddCandidate(cid,cname,party,uid,img)
{
    if(party==="" || cid==="" || cname==="" || party==="" || uid==="")
    {
      swal("error!","You cannot leave Any field  blank!", "error");
        return false;
    }
    else if(img===""){
        swal("error!","Symbol is must..Please Choose Any Symbol", "error");
        return false;
    }
    else 
        return true;
}
$('#btnClear').click(function(){
    
     $('#party').val("");
     document.getElementById('symbol').style.display='block';
     previewDefaultText.style.display=null;
     previewImage.style.display=null;
     previewImage.setAttribute("src","");$('#inpFile').val("");
});
function showuser()
{
    $('#result').html(""); 
    data={data:"getAllData"};
       $.post("ShowUserDetailsControllerServlet",data,function(responseText){
          clearText();
          $('#result').append(responseText);
       });
}
function deleteuser()
{
    $('#result').html("");   
    var newdiv=document.createElement("div");
    newdiv.setAttribute("id","candidateform");
    newdiv.setAttribute("float","left");
    newdiv.setAttribute("padding-left","left");
    newdiv.setAttribute("border","solid 2px red");
    newdiv.innerHTML="<h3>Remove User</h3>";
    newdiv.innerHTML=newdiv.innerHTML+"<div style='color:white;font-weight:bold'>Select Adhar Card Number:</div><div><select id='uid'></select></div>";
    newdiv.innerHTML=newdiv.innerHTML+"<br><span id='addresp'></span>";
    var addPrd=document.getElementById("result");
    addPrd.appendChild(newdiv);
    data={data:"uid"};
    $.post("ShowUserToDeleteControllerServlet",data,function(responseText){
      //document.write(responseText);
        $('#uid').append(responseText);
    });
    $('#uid').on('change',function(){
       var uid=$(this).children("option:selected").val();
        data={data:uid};
       $.post("ShowUserToDeleteControllerServlet",data,function(responseText){
          clearText();
          $('#addresp').append(responseText);
       });
    });
    
}
function removeuser()
{
       var uid=$('#uid').children("option:selected").val();
       data={uid:uid,
             remove:'remove'};
       $.post("DeleteUserControllerServlet",data,function(responseText){
       responseText=responseText.trim();
       if(responseText==='error'){
               swal("Error","Deletion Failed ! Try again","error");
            }
            else if(responseText==="failed")   
           {
               swal("Error!", "Sorry! Deletion failed dueto server issue.Try Again!", "error");
           }
            else{
                  swal("success!","User Removed Successfully!", "success");
                  setTimeout(3000);
                  clearText();
                 }
            });
   
    $('#uid').empty();
    var data={data:"uid"};
    $.post("ShowUserToDeleteControllerServlet",data,function(responseText){
       $('#uid').append(responseText);
    });
}
function getdeactiveuserdetails()
{
    $('#result').html(""); 
    data={data:"activate"};
       $.post("ActivateUserControllerServlet",data,function(responseText){
          $('#result').append(responseText);
       });
}
function activateuser(uid)
{
       data={data:uid};
       $.post("ActivateUserControllerServlet",data,function(responseText){
           responseText=responseText.trim();
           if(responseText==='error'){
               swal("Error","User Acivation Failed.Due to some Server issue ! Try again","error");
            }
            else if(responseText==="failed")   
           {
               swal("Error!", "Sorry!Cann't User Acivated.Try Again!", "error");
           }
            else{
                  swal("success!","User Acivated Successfully!", "success");
                  setTimeout(3000);
                 }
            });
       getdeactiveuserdetails();
}