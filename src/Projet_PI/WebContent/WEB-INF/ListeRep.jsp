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
		
		<script language="JavaScript">
	
			function filtreProf(){
				let etus = document.getElementsByClassName("etu");
				for(let i = 0; i < etus.length; i++){
					etus[i].style = "display:none;";
				}
				let profs = document.getElementsByClassName("prof");
				for(let i = 0; i < profs.length; i++){
					profs[i].style = "display:inherit;";
				}
			}
			function filtreEtu(){
				let profs = document.getElementsByClassName("prof");
				for(let i = 0; i < profs.length; i++){
					profs[i].style = "display:none;";
				}
				let etus = document.getElementsByClassName("etu");
				for(let i = 0; i < etus.length; i++){
					etus[i].style = "display:inherit;";
				}
			}
			function triNom(){
				let noms = document.getElementsByClassName("nom");
				let tab = {};
				for(let i = 0; i < noms.length; i++){
					tab[i] = document.getElementById(noms[i].id).cloneNode(true);
				}
				tri(noms);
				console.log(noms);
				console.log(tab);
				
				afficher(noms,tab,"nom");
				
			}
			function afficher(tab,tabSupp){
				for(let i = 0; i < tab.length; i++){
					document.getElementById(tab[i].id).remove();
				}
				for(let i = 0; i < tab.length; i++){
					let id = tabSupp[i].id
					id = id.split("-")[1];
					document.getElementById("etu-"+id).append(tabSupp[i]);
					document.getElementById("prof-"+id).append(tabSupp[i]);
				}
			}
			function create(elem){
				let nom = document.createElement("span");
				nom.innerText = elem.innerText;
				nom.className = "nom";
				
				
			}
			
			function swap(items, firstIndex, secondIndex){
			    let temp = items[firstIndex];
			    items[firstIndex] = items[secondIndex];
			    items[secondIndex] = temp;
			}
			function tri(tab){
				let i = 0;
				while(i < tab.length-1){
					let imin = i;
					let j = i+1;
					while(j < tab.length){
						if(tab[j].innerText < tab[imin].innerText){
							imin = j;
						}
						j ++;
					}
					if (i != imin){
						swap(tab,i,imin);
					}
					i++;
				}
			}
			
		</script>
		
		<div class="col-4 offset-4 text-center bg-light">
			<div class="dropdown" style="margin-bottom:20px;">
				<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Tri</button>
				<div class="dropdown-menu">
					<input class="dropdown-item" type="button" value="Aucun"> 
					<input class="dropdown-item" type="button" value="Nom">
					<input class="dropdown-item" type="button" value="Prenom">
					<input class="dropdown-item" type="button" value="Participation">
				</div>
			</div>
			<div style="margin-bottom:20px;">
	    		<input type="radio" id="etudiant" name="representant" value="etudiant" onclick="filtreEtu()">
	    		<label for="etudiant">Etudiant</label>
	    		<input type="radio" id="professeur" name="representant" value="professeur" onclick="filtreProf()">
	    		<label for="professeur">Professeur</label>
	  		</div>
			<div id="listeRep">
				<c:set var="i" value="1"></c:set>
				<c:forEach items="${prof}" var="p">
					<div id="prof-${p.id}" class="prof">
						<span id="nom-${p.id}" class="nom">${p.lastName}</span>
						<span class="prenom">${p.firstName}</span>
						<span class="nbParticipation">${p.nbParticipations}</span>
					</div>
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
				<c:forEach items="${etu}" var="e">
					<div id="etu-${e.id}" class="etu">
						<span id="nom-${e.id}" class="nom">${e.lastName}</span>
						<span class="prenom">${e.firstName}</span>
					</div>
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
			</div>
		</div>
	</body>
</html>