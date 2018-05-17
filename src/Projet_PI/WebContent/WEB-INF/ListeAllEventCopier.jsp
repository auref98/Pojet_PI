<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Copier évènement</title>
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
	 
		 <!-- container principal liste evenements -->
	 		
	 		<script language="JavaScript">
	 			function rechercher(){
		 			let rech = document.getElementById("recherche").value;
		 			rech = rech.toLowerCase();
		 			if(rech != null){
			 			let i = 0;
			 			let div = document.getElementsByClassName("even");
			 			while(i < div.length){
			 				if(div[i].id.toLowerCase().indexOf(rech) < 0){
			 			    	div[i].style = "display:none;";
							}else{
								div[i].style = "";
							}
			 				i++;
			 			}
		 			}
	 			}
	 		</script>
	 		
	 		<div class="offset-md-5 offset-3">
	 			<input class="border-secondary" type="text" id="recherche" placeholder="Chercher event a copier" onchange="rechercher()">
	 		</div>
	 		
		 	<!-- container pour un event -->
	 		<div class="container" style="margin-top:30px">
	 			<c:forEach items="${ events }" var="even">
	 				<div class="row container border border-top-0 border-left-0 border-right-0 border-secondary even" style="padding-bottom:10px;padding-top:10px;" id="${even.nom}">
	 					
	 					<!-- container image -->
	 					
		 				<div class="col-md-4">
		 					<img width="auto" height="200" alt="Evenement" src="${even.image}">
		 				</div>
		 				
		 				<!-- container texte  -->
		 				
		 				<div class="col-md-8 bg-light" >
		 					<h4>${even.nom}</h4>
		 					<p>Description : <br>${even.description}</p>
			 				<form method="post" action="CopierCreeEvent">
			 					<input type="submit" class="btn btn-info offset-5" value="Copier" name="event-${even.id }">
			 				</form>
			 			</div>
			 		</div>
				</c:forEach>
	 		
	 	</div>
	</body>
</html>