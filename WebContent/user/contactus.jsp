<%@page import="java.util.List" %>
<%@page import="org.bson.types.ObjectId" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Date" %>
<%@page import="com.mongodb.BasicDBObject" %>
<%@page import="com.mongodb.DBObject" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet"	href="./css/sidebar.css">
	<title>EBanking: Contact Us</title>
	<script>
		
	</script>
</head>
<body>
	<jsp:include page="./design/user_navigation.jsp"></jsp:include>
	<div class="container">
			<div class="col-sm-3" style="background-color: #f1f1f1; height: 100%">
				<ul style="padding-top: 100px; padding-left: -70px; " >
					<li style="padding-top: 30px;" class="active"><a href="home.jsp" >Account History</a></li>
					<li style="padding-top: 30px;"><a href="<%=request.getContextPath()%>/user/transfer.jsp">Transfer Money</a></li>
					<li style="padding-top: 30px;"><a href="#">Pay Bills</a></li>
					<li style="padding-top: 30px;"><a href="<%=request.getContextPath()%>/StatementController">Statements</a></li>
				</ul>
			</div>
			<div class="col-sm-9" style="padding-top: 80px;">
				<div>
					<p style="font-size:medium; font-weight:600;">CONTACT US</p>
				</div>
				<div class="col-sm-7">
					<form action="" method="post">
						<div class="form-group">
						  <label for="name">Name:</label>
						  <input type="text" class="form-control" id="name" name="name">
						</div>
						<div class="form-group">
						  <label for="email">Email:</label>
						  <input type="text" class="form-control" id="email" name="email">
						</div>
						<div class="form-group">
						  <label for="comment">Comment:</label>
						  <textarea class="form-control" rows="7" id="comment" name="comment"></textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-success">Submit</button>
							<button type="Clear" class="btn">Clear</button>
						</div>
						
					</form>
				</div>
			</div>	
	</div>
	<jsp:include page="./design/footer.jsp"></jsp:include>	
</body>
</html>