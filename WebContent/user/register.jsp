<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<jsp:include page="./design/login_navigation.jsp"></jsp:include>
	<div class="container-fluid" style="width: 600px;">
		<form action="" method="post">
			<div class="form-group">
				<label>User Name</label> <input type="text" class="form-control" required />
			</div>
			<div class="form-group">
				<label>Email</label> <input type="text" class="form-control" required />
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" class="form-control"	required />
			</div>
			<div class="form-group">
				<label>Confirm Password</label> 
				<input type="password" class="form-control" required />
			</div>		
			<div>
				<label>Gender</label> 
				<label class="radio-inline"><input type="radio" name="optradio">Male</label>
				<label class="radio-inline"><input type="radio" name="optradio">Female</label>				
			</div>
			<div class="form-group">
				<label>Birth Date</label> 
				<input type="text" id="datepicker" class="form-control" size="8" required />			
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success">Submit</button>
				<button type="reset" class="btn">Clear</button>
			</div>
		</form>
	</div>	
<jsp:include page="./design/footer.jsp"></jsp:include>

</body>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://bootstrap/css/bootstrap.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

 <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="http://resources/demos/style.css">
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</html>