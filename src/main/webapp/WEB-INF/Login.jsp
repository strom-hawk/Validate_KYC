<%@include file="HeadSection.jsp" %>
<h4 class="text-center">Login Page<h4>
<div style="height:50px"></div>

<div class="col-md-4 mx-auto">
<form action="add" method="post">
    <div class="form-group">
        <label for="inputEmail" style="font-weight:normal;font-size:15px;">User Name</label>
        <input type="text" class="form-control" name="txtname" placeholder="User Name">
    </div>
    <div class="form-group">
        <label for="inputPassword" style="font-weight:normal;font-size:15px;">Password</label>
        <input type="password" class="form-control" name="txtpaswd" placeholder="Password">
    </div>
    <div class="form-group">
        <label class="form-check-label" style="font-weight:normal;font-size:15px;"><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" class="btn btn-primary">Sign in</button>
</form>
</div>
</body>
</html>
