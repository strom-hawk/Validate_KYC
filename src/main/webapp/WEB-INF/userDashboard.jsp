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
		//alert(document.getElementById("inputGroupFile01").value);
		if(idtype == "Aadhar"){

			if(!isNaN(val) || val.length != 12){
			<c:if test="${error == 0}">
			<c:set var="error"  value="1"/>
				</c:if>

				document.getElementById("errdiv").style.display = "block";
				document.getElementById("err").innerHTML = "Please enter correct Aadhar no";


				//setTimeout('', 100000000);

			}


		}
		else if(idtype == "VoterID")
		{
			if(val.length != 10)
			{<c:if test="${error == 0}">
				<c:set var="error"  value="1"/>
				</c:if>

				document.getElementById("errdiv").style.display = "block";
				document.getElementById("err").innerHTML = "Please enter correct VoterId no";
			}

		}
		else if(idtype == "PassPort"){

			if(val.length != 9)
			{<c:if test="${error == 0}">
				<c:set var="error"  value="1"/>
				</c:if>

				document.getElementById("errdiv").style.display = "block";
				document.getElementById("err").innerHTML = "Please enter correct Passport no";
			}


		}
	}
	function sleep(milliseconds) {
		var start = new Date().getTime();
		for (var i = 0; i < 1e7; i++) {
			if ((new Date().getTime() - start) > milliseconds){
				break;
			}
		}
	}

	function set(){
		var val = document.getElementById("inputGroupFile01").value;
		document.getElementById("inputGroupFile01").value =val;
	}
</script>


<div style="height:50px"></div>
<div class="col-md-4 mx-auto shadow p-3 mb-5 bg-white rounded">
	<h4 class="text-center default-text py-3"><i class="fa fa-upload"></i> Upload KYC</h4>
	<div class="alert alert-danger" style="display: none" id="errdiv">
		<p id="err" ></p>

	</div>
	<c:if test="${error == 0}">
		<c:set var="userACK"  value="user_ACK"/>

	</c:if>
	<c:if test="${error == 1}">
		<c:set var="userACK"  value="/home"/>
		<script>
			sleep(90000);
		</script>
	</c:if>
	<form action="${userACK}" method="post" enctype="multipart/form-data">

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
			<label style="font-weight:normal;font-size:15px;" id="entrval">Enter Value</label>
			<input type="text" class="form-control" name="val" id="txtval" placeholder="Enter Value" required>
		</div>

		<div class="form-group">
			<label style="font-weight:normal;font-size:15px;">Upload Image</label>
			<div class="input-group">

				<div class="custom-file">
					<input type="file" class="custom-file-input" id="inputGroupFile01"
						   aria-describedby="inputGroupFileAddon01" name="img" required>
					<label class="custom-file-label" for="inputGroupFile01" >Choose file</label>

				</div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<div class="form-actions form-group  col-sm-4 mx-auto">
			<button type="submit" class="btn btn-block btn-danger" style="background-color: #CF1F0D;color: white;opacity:.8"
					onclick="checkValue()">Upload</button>
		</div>


	</form>

</div>

</body>
</html>