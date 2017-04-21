<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBanking : Registration</title>
</head>
<body>
<jsp:include page="./design/login_navigation.jsp"></jsp:include>
	<div class="container-fluid" style="width: 600px;">
		<form action="<%=request.getContextPath()%>/RegisterController?flag=insert" method="post">
			<div class="form-group">
				<label>User Name</label> <input type="text" name="userName" class="form-control" required />
			</div>
			<div class="form-group">
				<label>Email</label> <input type="text" name="email" class="form-control" required />
			</div>
			<div class="form-group">
				<label>Password</label> <input type="password" name="password" class="form-control"	required />
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
	</div>	
<jsp:include page="./design/footer.jsp"></jsp:include>

</body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


 <!--   
 <script>
   $( function() {
    $( "#datepicker" ).datepicker();
  } );
 </script>
 -->


  
</html>