<%@include file="HeadSection.jsp" %>
<script type="text/javascript">
	var id;
	var idtype;
	 function getValue(){
		 id = document.getElementById("sel1");
		 idtype = id.options[id.selectedIndex].value;
		
		 if(idtype == "Aadhar"){
			 document.getElementById("entrval").innerHTML = "Enter 12 digit aadhar no";
		 }
		 else if(idtype == "VoterID"){
			 document.getElementById("entrval").innerHTML = "Enter 10 digit voter Id no";
		 }
		 if(idtype == "PassPort"){
			 document.getElementById("entrval").innerHTML = "Enter 9 digit passport no";
		 }
	 }
 
  	function checkValue(){
  		var val = document.getElementById("txtval").value;
  		alert(document.getElementById("inputGroupFile01").value);
  		 if(idtype == "Aadhar"){
		
			 if(!isNaN(idtype) || val.length != 12)
				 alert("Enter correct aadhar no");
		 }
		 else if(idtype == "VoterID")
		{
			 if(val.length != 10)
				 alert("Enter correct voter Id no");
		 }
		 else if(idtype == "PassPort"){
	
			 if(val.length != 9)
				 alert("Enter correct Passport no");
		 } 
 	} 
  	
  	$('#inputGroupFile01').change( function(event) {
  		var tmppath = URL.createObjectURL(event.target.files[0]);
  		    $("img").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));

  		    $("#disp_tmp_path").html("Temporary Path(Copy it and try pasting it in browser address bar) --> <strong>["+tmppath+"]</strong>");
  		});
  	
</script>

<h4 class="text-center">Upload Your KYC</h4>
<div style="height:50px"></div>
<div class="col-md-4 mx-auto">
<form action="uploaded" method="post">
   <div class="form-group">
        <label for="sel1">Select Type:</label>
  		<select class="form-control" id="sel1" name="txttype" onchange="getValue()" required>
  		<option disabled selected value> ----select ID Type------</option>
   			<option>Aadhar</option>
		    <option>VoterID</option>
		    <option>PassPort</option>
  		</select>
    </div>
    <div class="form-group" >
        <label for="text" style="font-weight:normal;font-size:15px;" id="entrval">Enter Value</label>
        <input type="text" class="form-control" name="val" id="txtval" placeholder="Enter Value" required>
    </div>
    
    <div class="form-group">	
    <label for="text" style="font-weight:normal;font-size:15px;">Upload Image</label>
    <div class="input-group">
  		 
 	 	<div class="custom-file">
    		<input type="file" class="custom-file-input" id="inputGroupFile01"
      		aria-describedby="inputGroupFileAddon01" name="img">
    		<label class="custom-file-label" for="inputGroupFile01" required>Choose file</label>
 		</div>
	</div>
	</div>
    
    
    <button type="submit" class="btn btn-primary" onclick="checkValue()">Upload</button> 
</form>
</div>

</body>
</html>