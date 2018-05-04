 <!-- 
 	NamingException(Aurélien, Killian, Robin, Louis, Christophe)
  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Connexion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<header class="navbar navbar-dark bg-secondary">
    		<img class="img-fluid text-center mx-auto" src="http://10.0.2.41:8080/Projet_PI/assets/img/logoHERS1.png" width="400" height="100" alt="logoHERS0">
		</header>
		<div class="container">
			<div class="text-center" style="padding-top: 80px;font-family: 'Helvetica', 'Arial', sans-serif; font-size: 1em;">
				<h3>Identification EVENEMENTS HERS</h3>
			</div>
			<form style="padding-top:15px;" class="container-fluid" method="post" action="Connexion">
				<div class="form-group row offset-md-3 offset-1">
					<label style="padding-top:5px;" for="exampleInputEmail1" class="col-md-2 col-10">Adresse Email:</label>
					<input type="email" class="form-control col-md-6 col-10" name="email" placeholder="exemple@student.hers.be">
				</div>
				
				<div  style="padding-bottom:10px;" class="form-group row offset-md-3 offset-1">
					<label style="padding-top:5px;" for="exampleInputPassword1" class="col-md-2 col-10">Mot de passe:</label>
					<input type="password" class="form-control col-md-6 col-10" name="password" placeholder="Mot de passe">
				</div>
				<button type="submit" class="btn btn-primary row offset-md-4 offset-4 col-md-4 col-4">Connexion</button>
			</form>
			<form style="padding-top:10px;" class="container-fluid" method="get" action="Inscription">
				<button type="submit" class="btn btn-primary row offset-md-4 offset-4 col-md-4 col-4">Inscription</button>
			</form>
		</div>
	</body>
</html>