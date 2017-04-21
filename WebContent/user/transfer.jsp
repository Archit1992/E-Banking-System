<%@page import="java.util.List"%>
<%@page import="org.bson.types.ObjectId"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBObject"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet"	href="./css/sidebar.css">
	<title>EBanking : Transfer</title>
	<script>
		function check(){
			var a = parseInt(document.getElementById("num").value);
			var check_bal = parseInt(document.getElementById("check_bal").value);
			var saving_bal = parseInt(document.getElementById("saving_bal").value);
			console.log("transfer money : "+a+" checking : "+check_bal+" savings : "+saving_bal);
			alert("transfer money : "+a+" checking : "+check_bal+" savings : "+saving_bal);
			if( a > check_bal || a > saving_bal){
				console.log("a value is greater than checking Balance : "+ (a > check_bal));
				alert("Please enter valid amount!");
				return false;
			}else{
				return true;
			}
		}
	</script>
</head>
<body>
	<%
		List list = (List)session.getAttribute("account");
		long checking_bal = Long.parseLong(list.get(1).toString());
		long  saving_bal = Long.parseLong(list.get(3).toString());
		String flag="transfer";
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
					<center><p style="padding-top: 10px; font-size: 14px;">Transfer Information</p></center>
				</div>
				<div class="col-sm-4">
					<form action="<%=request.getContextPath()%>/AccountController" method="post">
						<div class="form-group">
							<label>Person Name</label>
							<input type="text" name="p_name" class="form-control" requireded />
						</div>
						<div class="form-group">
							<label>Person Email</label>
							<input type="text" name="email" class="form-control" requireded />
						</div>
						<div class="form-group">
							<label>Amount</label>
							<input type="text" id="num" name="amount" class="form-control" requireded /> 
						</div>
						<div>
							<label>From</label>
							<label class="radio-inline"><input type="radio" name="bal" value="Checking">Checking : <%=checking_bal %></label>
							<label class="radio-inline"><input type="radio" name="bal" value="Savings">Savings : <%=saving_bal %></label>
						</div>
						
						<div class="form-group">
							<input type="submit" onclick="return check()" class="btn btn-success" value="Submit" />
							<input type="reset" class="btn" value="Clear" />
						</div>
						<input type="hidden" id="check_bal" value="<%=checking_bal%>" />
						<input type="hidden" id="saving_bal" value="<%=saving_bal%>" />
						<input type="hidden" name="flag" value="<%=flag%>" />
					</form>
				</div>
			</div>
	</div>
	<jsp:include page="./design/footer.jsp"></jsp:include>	
</body>
</html>