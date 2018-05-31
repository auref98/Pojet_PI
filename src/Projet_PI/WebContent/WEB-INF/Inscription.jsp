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
		<link rel="icon" href="assets/img/favicon.ico">
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
				<div style="padding-bottom:10px;" class="form-group row offset-md-3 offset-1 col-6">
			    	<label for="email" style="padding-top:5px;" class="col-10 col-md-4">Adresse Email:</label>
			    	<input type="email" class="form-control col-md-8 col-10 ml-auto" name="email" placeholder="exemple@student.hers.be" required="required">
			  	</div>
			  	<div class="form-check row offset-md-3 offset-1 col-6"> 
			  		<label for="check" class="form-check-label col-11 offset-1">
			  			<input id="check" type="checkbox" class="form-check-input" required="required">
			  				j'accepte que mes données soient enregistrées
			  				</br></br>
			  				Le responsable de traitement des données est la Haute École Robert Schuman</br>
			  					Rue Fontaine Aux Mûres 13b</br>
			  					6800 Libramont</br>
			  					+32(0)61/23.01.20</br></br>
			  				Vos données ne seront transmises à aucun tiers et ne seront uniquement utilisées que pour le fonctionnement de l'application</br>
			  				Vos données à caractère personnel, en particulier le fait de bénéficier ou non d'une aide du service social, ne seront accessible que par le chargé de communication actuel 
			  		</label>
			  	</div>
			  	<button type="submit" class="mt-5 btn btn-primary row offset-md-4 offset-2 col-md-4 col-8">Demander un mot de passe</button>
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