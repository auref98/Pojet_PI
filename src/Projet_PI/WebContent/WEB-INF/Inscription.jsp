 <!-- 
 	NamingException(Aurélien, Killian, Robin, Louis, Christophe)
  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Inscription</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<header class="navbar navbar-dark bg-secondary">
    		<img class="img-fluid text-center mx-auto" src="assets\img\logoHERS1.png" width="400" height="100" alt="logoHERS0">
		</header>
		
		<form class="offset-4" method="post" action="DemmandeMDP">
			<div class="form-group row">
		    	<label for="exampleInputEmail1" class="col-2">Adresse Email:</label>
		    	<input type="email" class="form-control col-3 col-xs-12" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
		  	</div>
		  	<button type="submit" class="btn btn-primary row col-3 offset-1">Demande de mot de passe</button>
		</form>
	</body>
</html>