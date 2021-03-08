function castvote()
{
    swal("Voter!", "Redirecting to Voting Page!", "success");
    setTimeout(function(){window.location="castvote.jsp";},3000); 
}
function addvote()
{
    var id=$('input[type=radio][name=flat]:checked').attr('id');
    data={candidate:id};
    $.post("AddVoteControllerServlet",data,processresponse);
}
function processresponse(responseText)
{
    responseText=responseText.trim();
    if(responseText==="success")
        window.location="votingresponse.jsp";
    else
        window.location="accessdenied.jsp";
}