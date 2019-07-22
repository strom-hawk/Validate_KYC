<%@include file="HeadSection.jsp" %>
<h4 class="text-center">Login Page</h4>
<div style="height:50px"></div>

<div class="col-md-4 mx-auto login-form">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" method="post" class="form-horizontal">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid username and password.</p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>
        <div class="form-group">
            <label class="input-group-addon" for="username"><i class="fa fa-user"></i>UserName</label>
            <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
        </div>
        <div class="form-group">
            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i>Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

        <div class="form-actions form-group">
            <input type="submit"
                   class="btn btn-block btn-primary btn-default" value="Log in">
        </div>
    </form>
</div>

</body>
</html>


