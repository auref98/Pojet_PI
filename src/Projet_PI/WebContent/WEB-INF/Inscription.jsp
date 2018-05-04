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
    		<img class="img-fluid text-center mx-auto" src="assets/img/logoHERS1.png" width="400" height="100" alt="logoHERS0">
		</header>
		<div class="container">
			<div class="text-center" style="padding-top: 60px;font-family: 'Helvetica', 'Arial', sans-serif; font-size: 1em;">
				<h3>Inscription EVENEMENTS HERS</h3>
			</div>
			<form style="padding-top:15px;padding-bottom: 30px" class="container-fluid" method="get" action="DemandeMDP">
				<div style="padding-bottom:10px;" class="form-group row offset-md-3 offset-1">
			    	<label for="exampleInputEmail1" style="padding-top:5px;" class="col-md-2 col-10">Adresse Email:</label>
			    	<input type="email" class="form-control col-md-6 col-10" name="email" placeholder="exemple@student.hers.be">
			  	</div>
			  	<button type="submit" class="btn btn-primary row offset-md-4 offset-2 col-md-4 col-8">Demander un mot de passe</button>
			</form>
			<c:if test="${inscriptionSuccess == false}">
				<div class="alert alert-danger alert-dismissible fade show">
  					<button type="button" class="close" data-dismiss="alert">&times;</button>
  					<strong>Attention ! </strong>Email déjà utilisé !
				</div>
			</c:if>
			<c:if test="${inscriptionSuccess == true}">
				<div class="alert alert-success alert-dismissible fade show">
  					<button type="button" class="close" data-dismiss="alert">&times;</button>
  					<strong>Réussi ! </strong>Un mail contenant un mot de passe provisoire vous a été envoyé
  					<a href="/Projet_PI" class="alert-link">Retour à la connection</a>
				</div>
			</c:if>
		</div>
	</body>
</html>