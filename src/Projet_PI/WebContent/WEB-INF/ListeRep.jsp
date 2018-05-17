<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Profil</title>
		<link rel="icon" href="assets/img/favicon.ico">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<!-- NavBar -->
		<nav class="navbar navbar-expand-md navbar-dark bg-secondary fixed-top">
			<img src="assets/img/logo.png" alt="logo" style="width:100px;padding-right:15px" >
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navb">
				<ul class="navbar-nav mr-auto order-first">
					<li class="nav-item">
						<a class="nav-link" href="ListEvenSuivPrec">Liste des évènements</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="ListEvenInscrit">Mes inscriptions</a>
					</li>
					<c:if test="${relais == true }">
						<li class="nav-item">
							<a class="nav-link" href="CreeEvenement">Créer un événement</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="CopierEvenement">archives</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="ListeRep">Liste des utilisateurs</a>
						</li>
					</c:if>
				</ul>
				
				<a href="Profil" class="order-first order-md-last"><img src="assets/img/profil.png" alt="profil" style="width:40px;"></a>
				<a href="Deconnection" class="order-first order-md-last"><img src="assets/img/door.png" alt="door" style="width:40px;"></a>
				
				<form method="post" action="RechercherEvent" class="form-inline my-2 my-md-0 order-md-first">
					<input name="recherche" class="form-control mr-sm-2" type="text" placeholder="Recherche">
				</form>
			</div>
		</nav>
		<div style="margin-top:80px;"></div>
		
		<div>
			<label>Tri:</label>
			<select name="optionTri" id="optionTri" class="inputClass">
				<option value="Nom">Nom</option>
				<option value="Prenom">Prenom</option>
				<option value="Participation">Participation</option>
			</select>
		</div>
		<div>
    		<input type="radio" id="etudiant" name="representant" value="etudiant">
    		<label for="etudiant">Etudiant</label>
    		<input type="radio" id="professeur" name="representant" value="professeur">
    		<label for="professeur">Professeur</label>
  		</div>
  		<div id="listeRep">
			<c:set var="i" value="1"></c:set>
			<c:forEach items="${event.listePlage }" var="plage">
				<div id="plage-${i }">
					<input value="${plage.date }" type="date" id="date-${i }" name="date-${i }">
					<label for="debut-${i }">début</label><input value="${plage.heureDebut }" name="debut-${i }" type="time" id="debut-${i }">
					<label for="fin-${i }">fin</label><input value="${plage.heureFin }" name="fin-${i }" type="time" id="fin-${i }">
					<input type="button" value="X" name="sup-${i }" onclick="supprimerPlage('plage-${i }')">
				</div>
			</c:forEach>
		</div>
	</body>
</html>