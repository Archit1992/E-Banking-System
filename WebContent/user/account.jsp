<%@page import="java.util.List"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.mongodb.DBObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet"	href="./css/sidebar.css">
	<title>Ebanking:Home</title>
</head>
<body onload="getTotal()">
	<%
		// get user informaiton : id and user name.
		List<DBObject> list = (List<DBObject>) session.getAttribute("user");			
		BasicDBObject obj = (BasicDBObject)list.get(0); 
		
		// get user information session: 
		List<DBObject> ls = (List<DBObject>) session.getAttribute("get_user");			
		Iterator<DBObject> itr = ls.iterator();
		
	%>
	<jsp:include page="./design/user_navigation.jsp"></jsp:include>
	<div class="container">
			<div class="col-sm-3" style="background-color: #f1f1f1; height: 100%">
				<ul style="padding-top: 100px; padding-left: -70px; " >
					<li style="padding-top: 30px;" class="active"><a href="#" >Account History</a></li>
					<li style="padding-top: 30px;"><a href="<%=request.getContextPath()%>/user/transfer.jsp">Transfer Money</a></li>
					<li style="padding-top: 30px;"><a href="#">Pay Bills</a></li>
					<li style="padding-top: 30px;"><a href="<%=request.getContextPath()%>/StatementController">Statements</a></li>
				</ul>
			</div>
			<div class="col-sm-9" style="padding-top: 80px;">
				<div class="well well-sm">
					<p><center>EBanking : Account Details : <% out.println(obj.get("user").toString()); %>
					</center></p>
				</div>
				<% 
					while(itr.hasNext()){
						DBObject doc = (DBObject)itr.next();  					
				%>
				<form action="<%=request.getContextPath()%>/RegisterController?flag=update" method="post">
					<div class="form-group">
						<label>User Name</label> 
						<input type="text" name="userName" class="form-control" value="<%=doc.get("user").toString() %>" required />
					</div>
					<div class="form-group">
						<label>Email</label> 
						<input type="text" name="email" value="<%=doc.get("email").toString() %>" class="form-control" required />
					</div>
					<div class="form-group">
						<label>Password</label> 
						<input type="password" name="password" value="<%=doc.get("password").toString() %>" class="form-control"	required />
					</div>
					<div class="form-group">
						<label>Confirm Password</label> 
						<input type="password" class="form-control" name="confirm" required />
					</div>		
					<div>
						<label>Gender</label> 
						<label class="radio-inline"><input type="radio" name="gender" value="male">Male</label>
						<label class="radio-inline"><input type="radio" name="gender" value="female">Female</label>				
					</div>
					<div class="form-group">
						<label>Birth Date</label> 
						<input type="date" class="form-control" name="dob" size="8" required />
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success">Submit</button>
						<button type="reset" class="btn">Clear</button>
					</div>
					
				</form>
				<% } %>
			</div>		
		</div>
	<jsp:include page="./design/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	// javascript code.
</script>
</html>	