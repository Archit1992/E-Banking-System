<%@page import="org.bson.types.ObjectId"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="java.util.List"%>
<%@page import="com.mongodb.DBObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
 		List<DBObject> list = (List<DBObject>) session.getAttribute("user");			
		Iterator<DBObject> i = list.iterator();
		while(i.hasNext()){
		BasicDBObject dbobj = (BasicDBObject)i.next();
%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="">EBanking</a>			
		</div>
		<ul class="nav navbar-nav">
			<li><a href="#">Home</a></li>			
		</ul>
		<ul class="nav navbar-nav">
			<li><a href="<%=request.getContextPath()%>/user/aboutus.jsp">About Us</a></li>			
		</ul>
		<ul class="nav navbar-nav">
			<li><a href="<%=request.getContextPath()%>/user/contactus.jsp">Contact Us</a></li>			
		</ul>
		
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=request.getContextPath()%>/RegisterController?flag=get"><span class="glyphicon glyphicon-cog"></span> &nbsp Settings</a></li>
			<li><a href="./logout.jsp"><span class="glyphicon glyphicon-log-out"></span> &nbsp Logout</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-user"></span> &nbsp <%out.println(dbobj.getString("user")); %></a></li>
		</ul>						
	</div>
</nav>
<% } %>