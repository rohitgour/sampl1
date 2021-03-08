            var inpFile=document.getElementById("inpFile");
            var previewContainer=document.getElementById("imagePreview");
	    var previewImage=previewContainer.querySelector(".image-preview__image");
	    var previewDefaultText= previewContainer.querySelector(".image-preview__default-text");
	    var done=true;
            inpFile.addEventListener("change",function(){
                var file=this.files[0];
             
              if(file){
                document.getElementById('symbol').style.display='none';
              	var reader=new FileReader();
              	
              	previewDefaultText.style.display="none";
              	previewImage.style.display="block";

              	reader.addEventListener("load",function(){
              		previewImage.setAttribute("src",this.result);
              	});
              	reader.readAsDataURL(file);
                done=false;
              }else{
                  document.getElementById('symbol').style.display='block';
              	  previewDefaultText.style.display=null;
              	  previewImage.style.display=null;
              	  previewImage.setAttribute("src","");
              }
	    });
//        function check(){
//         if(done===true)
//         {
//              var file=this.files[0];
//           alert("Hello");
//           var setimage=document.getElementById("candidateimage");
//           var youtubeimgsrc = document.getElementById("candidatesymbol").getAttribute('src');
//           //document.write(youtubeimgsrc);
////           document.getElementById('symbol').style.display='none';
//              	var reader=new FileReader();
//              	
//              	//previewDefaultText.style.display="none";
//              	//previewImage.style.display="block";
//
//              	reader.addEventListener("load",function(){
//              		previewImage.setAttribute("src",youtubeimgsrc );
//              	});
//              	reader.readAsDataURL(file);
//                document.getElementById("setimage").src=youtubeimgsrc;
//         }
//     }
//            
