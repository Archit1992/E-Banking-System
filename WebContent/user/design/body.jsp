<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body onload="getIP()">
<%
	String invalid = (String)session.getAttribute("invalid");
%>
<div class="container-fluid" style="width:100%; height: 546px;">
	<div class="col-sm-8 text-left" style="width: 970px">
		<h1>Welcome</h1>
		<p class="double"><blink>EBanking is Master's project of Archit Gajjar in University of New Haven under the
		guidance of Dr.Barun Chandra.</blink><br/>
		</p>
		<h3>Your Identity</h3>
		<p>
			<ul>
				<li>Your ip address <strong><span id="ip"></span></strong></li>
				<li>Your Location <strong><span id="location"></span></strong></li>						
			</ul>
		</p>		
	</div>
	<div class="col-sm-3">
		<h1>Login</h1>
		<form action="<%=request.getContextPath()%>/RegisterController">
			<div class="form-group">
				<label>Email</label>
				<input type="text" name="email" class="form-control" required/>
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="password" name="password" class="form-control" required/>
				<input type="hidden" name="flag" value="login">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button><br/>
			<span style="color: red"><% if(invalid != null )out.println(invalid); %></span>
		</form>
	</div>
</div>

</body>
<script type="text/javascript">
function getIP(){
	jQuery.getJSON(
		"http://www.geoplugin.net/json.gp?jsoncallback=?",function (data) {
			document.getElementById("ip").innerHTML= data.geoplugin_request;
			document.getElementById("location").innerHTML= data.geoplugin_city +", "+ data.geoplugin_region +", "+ data.geoplugin_countryName;		
		}
	);	
}

</script>
</html>