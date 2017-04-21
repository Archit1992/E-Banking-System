<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<% 
	String message = (String)session.getAttribute("message");
%>
<jsp:include page="./design/login_navigation.jsp"></jsp:include>
	<div class="container-fluid" style="width: 600px;">
		<form action="<%=request.getContextPath()%>/PasswordController?flag=forgot" method="post">
			<div class="form-group">
				<label>Email</label> <input type="text" name="email" class="form-control" required />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">Submit</button>
				<button type="reset" class="btn">Clear</button>
			</div>
			<span><% if(message!= null)out.println(message);%></span>
		</form>
	</div>	
<jsp:include page="./design/footer.jsp"></jsp:include>
</body>
</html>