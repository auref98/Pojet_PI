<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sections</title>
		<link rel="icon" href="assets/img/favicon.ico">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
	</head>
	<body class="container">
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
					<c:if test="${relais == false }">
						<li class="nav-item">
							<a class="nav-link" href="ListEvenInscrit">Mes inscriptions</a>
						</li>
					</c:if>
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
					<c:if test="${charge == true}">
						<li class="nav-item">
							<a class="nav-link" href="ListeSection">Liste de sections</a>
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
		
		<form action="EnregistrerSection" method="post" class="mb-3 row">
			<div class="col-12">
				<h2 class="text-center">Liste des sections</h2>
			</div>
			<table class="table table-borderless col-12">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Relais</th>
						<th></th>
					<tr>
				</thead>
				<tbody>
					<c:forEach items="${section}" var="sec">
						<tr>
							<td>
								<input class="form-control" type="text" value="${sec.nom}" id="sec-${sec.id}" name="sec-${sec.id}">
							</td>
							<td>
								<select class="form-control" id="prof-${sec.id}" name="prof-${sec.id}-${sec.relais.id}">
									<c:forEach items="${profs}" var="prof">
										<option value="${prof.id}" <c:if test="${sec.relais.id == prof.id}">selected="selected"</c:if> >
											${prof.lastName} - ${prof.firstName}
										</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="button" class="btn btn-danger" value="X" onclick="document.location.href='SupprimerSection?supp-${sec.id}'" name="supp-${sec.id}">
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="offset-4 col-4 offset-md-5 col-md-2">
				<input class="btn btn-primary" type="submit" value="Enregistrer" name="enregistrerSection">
			</div>
		</form>
		<button type="button" class="btn btn-secondary" data-toggle="collapse" data-target="#addSect">Ajouter une section</button>
		<div id="addSect" class="collapse mt-3">
			<form action="NouvelleSection" method="post" id="NouvelleSection">
				<h2 class="text-center">Ajouter une section</h2>
				<div class="row col-12">
					<div class="col-5">
						<label for="NewSec">Nom</label>
						<input class="form-control" type="text" id="NewSec" name="NewSec" required="required" placeholder="Nom de la section">
					</div>
					<div class="col-5 ml-1">
						<label for="profes">Relais</label>
						<select class="form-control" id="profes" name="prof">
							<c:forEach items="${profs}" var="prof">
								<option value="${prof.id}">
									${prof.lastName} - ${prof.firstName}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-1">
						<label class="invisible">créer</label>
						<input class="btn btn-primary" type="submit" value="Créer">
					</div>
				</div>
			</form>
		</div>
		<div class="mt-5"></div>
	</body>
</html>