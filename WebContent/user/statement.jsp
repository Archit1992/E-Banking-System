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
	<title>Ebanking:Statement</title>
	<script>
		
	</script>
</head>
<body>
	<%
		List<DBObject> trans = (List<DBObject>)session.getAttribute("transfer");
		List<DBObject> rec = (List<DBObject>)session.getAttribute("receiver");
	
	%>
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
				<div class="well well-sm">
					<center><p style="padding-top: 10px; font-size: 14px;">Transfer Information</p></center>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Sent To</th>
							<th>Email</th>
							<th>Amount</th>
							<th>From</th>
							<th>Date</th>
							<th>Time</th>
						</tr>
					</thead>
				<%	if(trans != null){
					for(int i = ( trans.size() -1 ) ; i >= 0  ; i--){
						String sentTo = ((DBObject)trans.get(i)).get("sent_to").toString();
						String email = ((DBObject)trans.get(i)).get("email").toString();
						String amount = ((DBObject)trans.get(i)).get("Amount").toString();
						
						String account = ((DBObject)trans.get(i)).get("Account").toString();
						String date = ((DBObject)trans.get(i)).get("Date").toString();
						Date d = new Date(date);
					
				%>
					<tbody>
						<tr>
							<td><% if(sentTo != null) out.println(sentTo); %></td>
							<td><% if(email != null) out.println(email); %></td>
							<td><% if(amount != null) out.println(amount); %></td>
							<td><% if(account != null) out.println(account); %></td>
							<td><% if(d != null) out.println(d.getMonth()+"/"+d.getDate()+"/"+d.getYear()); %></td>
							<td><% if(d != null) out.println(d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()); %></td>		
						</tr>
					</tbody>					
				<% } }%>
				</table>
				<br/><br/><br/><br/>
				
				<!--  ============================== Receiver Side =========================== -->	
				<div class="well well-sm">
					<center><p style="padding-top: 10px; font-size: 14px;">Receive Information</p></center>
				</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>From</th>
								<th>Amount</th>
								<th>Date</th>
								<th>Time</th>
							</tr>
						</thead>
					<%
					if(rec != null){
						for(int i = ( rec.size() -1 ) ; i >= 0  ; i--){
							String from = ((DBObject)rec.get(i)).get("From").toString();
							String amount = ((DBObject)rec.get(i)).get("Amount").toString();
							String rec_date = ((DBObject)rec.get(i)).get("Date").toString();
							Date d = new Date(rec_date);
					%>
						<tbody>
							<tr>
								<td><% if(from != null) out.println(from); %></td>
								<td><% if(amount != null) out.println(amount);%></td>
								<td><% if(d.toString() != null) out.println(d.getMonth()+"/"+d.getDate()+"/"+d.getYear()); %></td>
								<td><% if(d.toString() != null) out.println(d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()); %></td>		
							</tr>
						</tbody>					
					<% } } %>
					</table>	
			</div>	
	</div>
	<jsp:include page="./design/footer.jsp"></jsp:include>	
</body>
</html>