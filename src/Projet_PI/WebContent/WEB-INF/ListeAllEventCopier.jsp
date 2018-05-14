<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Accueil</title>
		<link rel="icon" href="assets/img/favicon.ico">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-secondary" >
			<img src="assets/img/logo.png" alt="logo" style="width:100px;padding-right:15px" >
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navb">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="ListEvenSuivPrec">Liste des évènements</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="ListEvenInscrit">Mes inscriptions</a>
					</li>
					<c:if test="${relais == true }">
						<li class="nav-item">
							<a class="nav-link" href="CreeEvenement">Créé un événement</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="CopierEvenement">Copier un événement</a>
						</li>
					</c:if>
				</ul>
				<a href="Profil"><img src="assets/img/profil.png" alt="profil" style="width:40px;"></a>
				<a href="Deconnection"><img src="assets/img/door.png" alt="door" style="width:40px;"></a>
				<form method="post" action="RechercherEvent" class="form-inline my-2 my-lg-0">
					<input name="recherche" class="form-control mr-sm-2" type="text" placeholder="Recherche">
				</form>
			</div>
		</nav>
	 
		 <!-- container principal liste evenements -->
		 <div>
		 	<c:forEach items="${ events }" var="even">
		 		<div name="${even.nom }">
			 		<div>
			 			<img alt="Evenement" src="${even.image}">
			 		</div>
			 		<div class="col-md-8 bg-light" >
			 			<h4>${even.nom}</h4>
			 			<p>Description : <br>${even.description}</p>
				 		<form method="post" action="CopierCreeEvent">
				 			<input type="submit" class="btn btn-info" value="Copier" name="event-${even.id }">
				 		</form>
				 	</div>
				 </div>
			</c:forEach>
		</div>
	</body>
</html>