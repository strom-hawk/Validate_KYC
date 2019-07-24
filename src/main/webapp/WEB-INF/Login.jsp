<%@include file="HeadSection.jsp" %>

<div style="height:50px"></div>

<div class="col-md-4 mx-auto login-form">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" method="post" class="form-horizontal shadow p-3 mb-5 bg-white rounded">
        <h4 class="text-center default-text py-3"><i class="fa fa-lock"></i> Login</h4>
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
            <label class="input-group-addon" for="username"><i class="fa fa-user"></i> UserName</label>
            <input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
        </div>
        <div class="form-group">
            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i> Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />


        <div class="form-actions form-group  col-sm-4 mx-auto">
            <input type="submit"
                   class="btn btn-block" value="Login" style="background-color: #CF1F0D;color: white;opacity: .8">
        </div>
    </form>
</div>

</body>
</html>


