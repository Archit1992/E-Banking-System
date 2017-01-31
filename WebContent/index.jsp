<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body>

<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h4>Admin</h4>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#section1">Add Country</a></li>
        <li><a href="#section2">Search Country</a></li>
        <li><a href="#section3">Add State</a></li>
        <li><a href="#section3">Search State</a></li>
      </ul><br>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search Blog..">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
    </div>

    <div class="col-sm-9">
      <h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>Add Country</h2>
      	<form class="form-inline" action="<%=request.getContextPath()%>/CountryController?flag=add" method="post">
      		<div class="form-group">
      			<label for="country_name">Country Name</label>
      			<input type="text" name="country" class="form-control" id="country_name">      			
      		</div>
      		<button type="submit" class="btn btn-primary">Submit</button>
      	</form>	
      <br><br>
      
      <h4><small>RECENT POSTS</small></h4>
      <hr>
      <h2>Search Country</h2>
      	<form>
      	
      	</form>	
      <br><br>
      
      
      
    </div>
  </div>
</div>

<footer class="container-fluid">
  <p>Footer Text</p>
</footer>

</body>
</html>
