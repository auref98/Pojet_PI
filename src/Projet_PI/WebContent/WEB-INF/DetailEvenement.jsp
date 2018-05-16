<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>D�tail</title>
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
						<a class="nav-link" href="ListEvenSuivPrec">Liste des �v�nements</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="ListEvenInscrit">Mes inscriptions</a>
					</li>
					<c:if test="${relais == true }">
						<li class="nav-item">
							<a class="nav-link" href="CreeEvenement">Cr�� un �v�nement</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="CopierEvenement">Copier un �v�nement</a>
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
		
		<script language="JavaScript">
	
		function validation(event){
			if(event.key == "Enter"){
				let input = document.getElementById("posterCommentaire");
				if(input.value.length > 200 | input.value == ""){
					event.preventDefault();
					alert("votre commentaire dois faire moins de 200 carract�re ("+input.value.length+")");
					input.value = "";
				}
			}
		}
	</script>
		
		<div class="container bg-light" style="margin-top:30px">
			<div class="row">
				
				<!-- image -->
				<div class="col-md-5">
					<img style ="max-width:100%;" alt="Evenement" src="${even.image }">
				</div>
				
				<!-- nom + details -->
				<div class="col-md-6 offset-md-1">
					<!--<c:if test="${relais == true }">
						<form action="SupprimerEvenement" method="post">
							<input type="submit" value="supprimer l'�v�nement" name="event-${even.id }">
						</form>
					</c:if>-->
					
					<h1>${even.nom }</h1>
					<c:forEach items="${even.listePlage }" var="plage">
						<div class="row container-fluid">
							<p class="col-8" style="padding-top:5px;">
								${plage.date} - (${plage.heureDebut } - ${plage.heureFin })
							</p>
							<c:if test="${inscri == true }">
								<c:set var="i" value="false"></c:set>
								<c:forEach items="${plage.listeInscription }" var="inscri" >
									<c:set var="i" value="true"></c:set>
								</c:forEach>
								<c:if test="${i == true}">
									<form class="col-2" method="post" action="DesinscriptionEven">
										<input type="submit" class="btn btn-info" value="D�sinscrire" name="${plage.id }-${even.id}">
									</form>
								</c:if>
								<c:if test="${i == false}">
									<form class="col-2" method="post" action="InscriptionEven">
										<input type="submit" class="btn btn-info" value="S'inscrire" name="${plage.id }-${even.id}">
									</form>
								</c:if>
							</c:if>
							<c:if test="${inscri == false }">
								<form method="post" action="InscriptionEven">
									<input type="submit" class="btn btn-info" value="S'inscrire" name="${plage.id }-${even.id}">
								</form>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
			
			<!-- Description -->
			<div>
				<h2>Description</h2>
				<p>${even.description }</p>
			</div>
			<div>
				<p>Nombre de personne requise : ${even.nbParticipantsRequis }</p>
				<p>
					Section requise : <br>
					<c:forEach items="${even.listeSection}" var="section">
						${section.nom} <br>
					</c:forEach>
				</p>
				<p>
					Adresse de l'evenement : ${even.adresseEve.rue } ${even.adresseEve.numero } ${even.adresseEve.boite }, ${even.adresseEve.codePostal } ${even.adresseEve.localite }, ${even.adresseEve.pays }
				</p>
			</div>
			<div style="margin: 0.5em;border: solid 1px black;border-radius: 20px;">
				<div style="margin: 0.5em;padding: 0.5em;height:250px;overflow-y: scroll;">
					<p>
						Commentaire <br>
						<c:forEach items="${even.listeCommentaire }" var="com">
							<div style="border:solid 1px black;margin:0.5em;padding:0.5em;border-radius:20px;">
								<form method="post" action="supprimerCommentaire" style="display: flex;justify-content: space-between;">
									<p>De : ${com.rep.lastName } ${com.rep.firstName }</p>
									<c:if test="${(relais == true) or (rep.id == com.rep.id)}">
										<input type="submit" value="X" name="${com.id }-${even.id }">
									</c:if>
								</form>
								<span>
									${com.contenu }
								</span>
							</div>
						</c:forEach>
						<c:if test="${postercom == true}">
							<form method="post" action="posterCommentaire" style="display: flex;justify-content: center;">
								<input id="posterCommentaire" name="commentaire-${even.id }" type="text" placeholder="Commentaire..." style="width: 100%;margin: 0.5em;" onkeypress="validation(event)">
							</form>
						</c:if>
					</p>
				</div>
			</div>
			<div class="offset-md-5 offset-3">
				<c:if test="${relais == true }">
					<form action="SupprimerEvenement" method="post">
						<input type="submit" class="btn btn-danger" value="supprimer l'�v�nement" name="event-${even.id }" style="margin-bottom:30px">
					</form>
				</c:if>
			</div>
		</div>
	</body>
</html>