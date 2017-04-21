<%@page import="java.util.List"%>
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
		List<String> list = (List<String>)session.getAttribute("account");
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
			<div class="col-sm-9" style="padding-top: 170px;">
				<div class="well well-sm">
					<p>Checking Account Information</p>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Account Name</th>
							<th>Account Number</th>
							<th>Today's Beginning Balance</th>
							<th>Pending Transaction</th>
							<th>Available Balance</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>E-Banking Checking</td>
							<td><% out.print(list.get(0)); %></td>
							<td></td>
							<td></td>
							<td id="first"><% out.println(list.get(1)); %></td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td>E-Banking Savings</td>
							<td><%out.println(list.get(2)); %></td>
							<td></td>
							<td></td>
							<td id="second"><%out.println(list.get(3)); %></td>
						</tr>
					</tbody>
				</table>
				<div class="well well-sm">
					<p>Total Available Balance of Deposit Account
						<span style="padding-left: 615px;" id="total" ></span>
					</p>												
				</div>
			</div>
	</div>
	<jsp:include page="./design/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	function getTotal(){
		var value_a = document.getElementById("first").innerText;
		var value_b = document.getElementById("second").innerText;
		var total= ( Number(value_a) + Number(value_b) );
		document.getElementById("total").innerHTML = "$" + total; 
	}
</script>
</html>	