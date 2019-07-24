<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>


	* {box-sizing: border-box;}

	/* Style the navbar */
	.topnav {
		overflow: hidden;
		background-color: #dbdbdb;
		height: 40px;

	}

	/* Navbar links */
	.topnav a {
		float: right;
		display: block;
		color: black;
		text-align: center;
		padding: 8px 8px;
		text-decoration: none;
		font-size: 16px;
	}


	/* Navbar links on mouse-over */
	.topnav a:hover {
		background-color: #ddd;
		color: black;
	}

	/* Active/current link */
	.topnav a.active {
		background-color: #AEAEAE;
		color: white;
	}



</style>

</head>

<body>
<div class = "container-fluid">
	<div class="row" style="height:30px"></div>
	<div class="row">
		<div class="col" ><h4>Mobiquity Money</h4></div>
		<div class="col">
			<div align="right"><img src="/images/comviva_logo.png" /></div>
		</div>
	</div>
	<div class="row" style="height:10px"></div>

	<div class="topnav rounded">
		<c:if test="${user.length() > 0}">
		<a class="active" href="/logout">Logout</a>
		<a href="">Welcome ${user}</a>
		</c:if>
	</div>


<%--<div class="row-md-12" style="height:50px;background-color:lightgrey">

		<div class="col-sm-6"></div>

		<c:if test="${user.length() > 0}">
		<div class="col-sm-3">
				<p style="">Welcome ${user}</p>
		</div>
		<div class="col-sm-3">
			<a href="/logout" class="btn btn-default" id="logout_button" style="background-color: grey;">Logout</a>
		</div>

</div>--%>
</div>




</body>
</html>



