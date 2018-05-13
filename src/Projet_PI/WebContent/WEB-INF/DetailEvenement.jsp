<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Détail</title>
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
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="text" placeholder="Recherche">
				</form>
			</div>
		</nav>
		<div>
			<img alt="Evenement" src="${even.image }">
		</div>
		<c:if test="${relais == true }">
			<form action="SupprimerEvenement" method="post">
				<input type="submit" value="supprimer l'événement" name="event-${even.id }">
			</form>
		</c:if>
		<h1>${even.nom }</h1>
		<c:forEach items="${even.listePlage }" var="plage">
			<div>
				<p>
					${plage.date} - (${plage.heureDebut } - ${plage.heureFin })
				</p>
				<c:if test="${inscri == true }">
					<c:set var="i" value="false"></c:set>
					<c:forEach items="${plage.listeInscription }" var="inscri" >
						Vous-etes inscris.
						<c:set var="i" value="true"></c:set>
					</c:forEach>
					<c:if test="${i == true}">
						<form method="post" action="DesinscriptionEven">
							<input type="submit" value="Désinscrire" name="${plage.id }-${even.id}">
						</form>
					</c:if>
					<c:if test="${i == false}">
						<form method="post" action="InscriptionEven">
							<input type="submit" value="S'inscrire" name="${plage.id }-${even.id}">
						</form>
					</c:if>
				</c:if>
				<c:if test="${inscri == false }">
					<form method="post" action="InscriptionEven">
						<input type="submit" value="S'inscrire" name="${plage.id }-${even.id}">
					</form>
				</c:if>
			</div>
		</c:forEach>
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
		<div>
			<p>
				Commentaire <br>
				<c:forEach items="${even.listeCommentaire }" var="com">
					<p>De : ${com.rep.lastName } ${com.rep.firstName }</p>
					<span>
						${com.contenu }
					</span>
				</c:forEach>
			</p>
		</div>
	</body>
</html>