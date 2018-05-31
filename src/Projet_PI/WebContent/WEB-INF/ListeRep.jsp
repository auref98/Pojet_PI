<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Liste des utilisateurs</title>
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
		
	  	<div class="container">
	  		<style>
	  			.nav-tabs{
					border-bottom: none;
				}
				.nav-tabs .nav-link {
					border: 0px solid transparent;
					border-radius: 0.25rem;
				}
				.nav-tabs .nav-link.active{
					background-color: rgba(0,123,255,0.5);
				}
	  		</style>
	  		<!-- Nav tabs -->
	  		<ul class="nav nav-tabs col-12">
				<li class="nav-item">
					<a class="nav-link active" data-toggle="tab" href="#etu">Étudiants</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" data-toggle="tab" href="#prof">Professeurs</a>
				</li>
			</ul>
	  		
	  		<!-- Panes -->
	  		<div class="tab-content col-12">
	  			<div id="etu" class="container tab-pane active"><br>
					<h3>Étudiants</h3>
					<table class="col-12 table table-striped  table-borderless">
						<thead class="thead-light">
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${etu}" var="e">
							<tr>
								<td>${e.lastName}</td>
								<td>${e.firstName}</td>
								<c:if test="${charge == true}">
									<td class="float-right">
										<form action="ModifierProfil">
											<input type="hidden" value="${e.id}" name="id">
											<input class="btn btn-info" type="submit" value="Modifier le profil" name="modifierprofil">
										</form>
									</td>
								</c:if>
							</tr>
							<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="prof" class="container tab-pane fade"><br>
					<h3>Professeurs</h3>
					<table class="col-12 table table-striped table-borderless">
						<thead class="thead-light">
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Participations</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="i" value="1"></c:set>
							<c:forEach items="${prof}" var="p">
												
							<!-- <div id="prof-${p.id}" class="prof" style="margin-bottom:10px;"> -->
							<tr>
								<td>${p.lastName}</td>
								<td>${p.firstName}</td>
								<td>${p.nbParticipations}</td>
								
								<c:if test="${charge == true}">
									<td class="float-right">
										<form action="ModifierProfil">
											<input type="hidden" value="${p.id}" name="id">
											<input class="btn btn-info" type="submit" value="Modifier le profil" name="modifierprofil">
										</form>
									</td>
								</c:if>
							</tr>
							<!-- </div> -->

							<c:set var="i" value="${i+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
	  		</div>
	  	</div>
	</body>
</html>