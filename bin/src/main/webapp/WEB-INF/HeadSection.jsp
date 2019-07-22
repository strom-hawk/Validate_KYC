<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<script>
	/*var logout = function() {
		$.post("/logout", function() {
			$("#user").html('');
			$(".unauthenticated").show();
			$(".authenticated").hide();
		})
		return true;
	}*/

	
</script>
<body>
<div class = "container">
	<div class="row" style="height:30px"></div>
	<div class="row">
		<div class="col"><h4>Mobiquity Money</h4></div>
		<div class="col">
			<div align="right"><img src="/images/comviva_logo.png"/></div>
		</div>
	</div>
	<div class="row" style="height:10px"></div>
</div>
<div class="col-md-12">
	<div style="height:50px;background-color:lightgrey">
		<div align="right">
			<div class="row" style="height:10px;">
			<c:if test="${uname.length() > 0}">
				<p style="font-weight:bold;font-size:16px;">Welcome ${uname}</p>
			</c:if>

			<div class="container authenticated">
				<div>
					<button onClick="logout()" class="btn btn-primary">Logout</button>
				</div>
			</div>
			</div>
		</div>
	</div>

</div>


</div>

</body>
</html>



