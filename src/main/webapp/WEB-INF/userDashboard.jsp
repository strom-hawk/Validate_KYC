<%@include file="HeadSection.jsp" %>
<script type="text/javascript">
	var id;
	var idtype;
 function getValue(){
	 id = document.getElementById("sel1");
	 idtype = id.options[id.selectedIndex].value;
	alert(idtype); 
	 if(idtype == "Aadhar"){
		 document.getElementById("txtval").value = "Enter 12 digit aadhar no";
	 }
 }
 
 /* function showValue(){
	 if(idtype == "Aadhar"){
		 document.getElementById("txtval").value = "Enter 12 digit aadhar no";
		 if(!isNaN(idtype) && idtype.length != 12)
			 alert("--Enter correct aadhar no--");
	 }
	 else if(id == "VoterID")
	{
		 document.getElementById("txtval").innerHTML = "Enter 10 digit voter Id no";
		 if(id.length != 10)
			 alert("--Enter correct voter Id no--");
	 }
	 else if(id == "PassPort"){
		 document.getElementById("txtval").innerHTML = "Enter 9 digit passport no";
		 if(id.length != 9)
			 alert("--Enter correct Passport  no--");
		 
	 }
 } */
</script>
<h4 class="text-center">Upload Your KYC</h4>
<div style="height:50px"></div>
<div class="col-md-4 mx-auto">
<form action="login" method="post">
   <div class="form-group">
        <label for="sel1">Select Type:</label>
  		<select class="form-control" id="sel1" name="txttype" onchange="getValue()">
  		<option disabled selected value> -- select ID Type -- </option>
   			<option>Aadhar</option>
		    <option>VoterID</option>
		    <option>PassPort</option>
  		</select>
    </div>
    <div class="form-group" >
        <label for="text" style="font-weight:normal;font-size:15px;" id="txtval">Enter Value</label>
        <input type="text" class="form-control" name="txtvalue" placeholder="Enter Value"  >
    </div>
    
    <div class="form-group">	
    <label for="text" style="font-weight:normal;font-size:15px;">Upload Image</label>
    <div class="input-group">
  		 
 	 	<div class="custom-file">
    		<input type="file" class="custom-file-input" id="inputGroupFile01"
      		aria-describedby="inputGroupFileAddon01">
    		<label class="custom-file-label" for="inputGroupFile01">Choose file</label>
 		</div>
	</div>
	</div>
    
    
    <button type="submit" class="btn btn-primary">Upload</button> 
</form>
</div>

</body>
</html>