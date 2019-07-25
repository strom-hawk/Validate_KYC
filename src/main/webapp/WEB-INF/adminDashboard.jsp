<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Insert title here</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
        .row.content {height: 1500px}

        /* Set gray background color and 100% height */
        .sidenav {
            background-color:#f5f5f0;
            height: 100%;
            margin-left:15px;

        }
        a{

            padding: 8px 8px;
            text-decoration: none;
            font-size: 20px;
        }
        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height: auto;}
        }

        .bs-example{
            margin: 20px;
        }
        tr {display: block; }
        th, td { width: 200px; }
        tbody { display: block; height: 500px; overflow: auto;}

    </style>
    <script>
        function showDash(){
            document.getElementById("viewkyc").style.display="none";
            document.getElementById("viewdash").style.display="block";


        }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="height:30px"></div>
    <div class="row">
        <div class="col-md-3" ><h3>Mobiquity Money</h3></div>
        <div class="col-md-6" ></div>
        <div class="col-md-3">
            <div align="right"><img src="/images/comviva_logo.png"/></div>
        </div>
    </div>
    <div class="row" style="height:10px"></div>
    <div class="row-md-12">
        <div style="height:35px;background-color:lightgrey"></div>
    </div>
</div>

<div class="container-fluid">

    <div class="row content">

        <div class="col-sm-2 sidenav">
            <div class="row" style="height:10px"></div>
            <c:if test="${uname.length() > 0}">
                <p style="font-weight:normal;font-size:18px;">Welcome ${uname}</p>
            </c:if>
            <div class="bs-example">
                <nav class="nav nav-pills flex-column">
                    <a href="#dash" class="nav-item nav-link" onclick="showDash()">
                        <i class="fa fa-home" ></i> Dashboard
                    </a>
                    <a href="viewkyc" class="nav-item nav-link">
                        <i class="fa fa-table"></i> View Kyc
                    </a>

                    <a href="/logout" class="nav-item nav-link" tabindex="-1">
                        <i class="fa fa-sign-out"></i> Logout
                    </a>
                </nav>
            </div>

        </div>


        <div class="row-md-10 mx-auto">
            <div class="row" style="height:20px"></div>
            <div class="row" style="height:20px;display:none" id="viewdash">
                <h2>Welcome ${uname}</h2>
            </div>
            <c:if test="${showtable == 1}">

                <div class="row" id="viewkyc">

                    <h5 align="center">Kyc Details</h5>
                    <br/>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID Type</th>
                            <th>ID Number</th>
                            <th>Image</th>
                            <th>User ID</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="list" items="${maplist}">
                        <tr>
                            <td>${list.type}</td>
                            <td>${list.idno}</td>
                            <td><img src="${list.img}" style="width:100px;height:100px;"/></td>
                            <td>${list.uid}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>

    </div>
</div>

</body>
