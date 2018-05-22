<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cree evenement</title>
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
			function ajouterHoraire(event){
				event.preventDefault();
				
				let d = "date-";
				let i = 1;
				while(document.getElementById(d+i) != null){
				    i++;
				}
				let label_Date = document.createElement("label");
				label_Date.innerText = "date";
				label_Date.style.marginTop ="5px";
				label_Date.className = "col-2";
				
				
				let input_date = document.createElement("input");
				input_date.type = "date";
				input_date.name = "date-"+i;
				input_date.id = "date-"+i;
				input_date.className = "form-control";
				input_date.attributes["required"] = "required";
				
				//Création du div Date
				let divDate = document.createElement("div");
				divDate.className = "row container-fluid col-md-3 offset-md-1 col-10";
				divDate.id = "date";
				divDate.appendChild(label_Date);
				divDate.appendChild(input_date);
				
				
				let label_Debut = document.createElement("label");
				label_Debut.innerText = "début";
				label_Debut.style.marginTop ="5px";
				label_Debut.className = "col-2";
				
				let input_debut = document.createElement("input");
				input_debut.type = "time";
				input_debut.name = "debut-"+i;
				input_debut.id = "debut-"+i;
				input_debut.className = "form-control";
				input_debut.attributes["required"] = "required";
								
				//Création du div Debut
				let divDebut = document.createElement("div");
				divDebut.className = " row container-fluid col-md-3 offset-md-1 col-10";
				divDebut.id = "debut";
				divDebut.appendChild(label_Debut);
				divDebut.appendChild(input_debut);
				
				let label_Fin = document.createElement("label");
				label_Fin.innerText = "fin";
				label_Fin.style.marginTop ="5px";
				label_Fin.className = "col-2";
				
				let input_fin = document.createElement("input");
				input_fin.type = "time";
				input_fin.name = "fin-"+i;
				input_fin.id = "fin-"+i;
				input_fin.className = "form-control";
				input_fin.attributes["required"] = "required";
				

				//Création du div Fin
				let divFin = document.createElement("div");
				divFin.className = "row container-fluid col-md-3 offset-md-1 col-10";
				divFin.id = "fin";
				divFin.appendChild(label_Fin);
				divFin.appendChild(input_fin);
				
				//Création du input Button
				let input_button = document.createElement("input");
				input_button.style.padingLeft ="5px";
				input_button.style.padingLeft ="5px";
				input_button.className = "btn btn-danger col-12";
				input_button.type = "button";
				input_button.value = "X";
				input_button.attributes["required"] = "required";
				input_button.name = "sup-"+i;
				input_button.addEventListener("click",() => {supprimerPlage("plage-" + i)});
				
				//Création du div Button
				let divButton = document.createElement("div");
				divButton.className = "container col-md-1 col-2 ml-auto";
				divButton.appendChild(input_button);
				
				//Création du div qui reprend le div_Dabut, div_Debut, div_Fin
				let divDDF = document.createElement("div");
				divDDF.className = "row container-fluid col-md-10";
				divDDF.id = "ddf";
				divDDF.appendChild(divDate);
				divDDF.appendChild(divDebut);
				divDDF.appendChild(divFin);
				
				let divPlage = document.createElement("div");
				divPlage.id = "plage-"+i;
				divPlage.className = "row container-fluid";
				divPlage.appendChild(divDDF);
				divPlage.appendChild(divButton);
				
				document.getElementById("plageHoraire").appendChild(divPlage);
			}
		function changerImage(){
			image = document.getElementById("image");
			input = document.getElementById("input-image");
			image.src = input.value;
		}
		function supprimerPlage(id){
			let div = document.getElementById(id);
			let par = document.getElementById("plageHoraire");
			par.removeChild(div);
		}
		function verifierLenght(id,length){
			let input = document.getElementById(id);
			if(input != null && input.value.length > length){
				input.value = "";
				document.getElementById(id).focus();
			}
		}
		</script>
		
		<!-- Formmulaire création évenement -->
		<div>
			<form class="container-fluid bg-light text-center" style="margin-top:30px;margin-bottom:30px;" action="EnregistrerEvenement" method="post">
				<div class="row col-12">
					<!-- image -->
					<div class="col-md-4 order-first">
						<img id="image" alt="Inserer une image" src="${event.image }" width="auto" height="200">
						<input type="text" name="image" id="input-image" value="${event.image }" onchange="changerImage()">
					</div>
				
				<!-- nom -->
				<div class="col-md-8 container-fluid" style="margin-top:30px;">
					<h1>Nom</h1>
					<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.nom }" name="nomEvenement" required="required" id="nomEvenement" type="text" placeholder="Nom de l'evenement">
					<div style="margin-top:30px;">
						<h2>Entrer une plage horaire </h2>
						
						<!-- div toutes les plages -->
						<div id="plageHoraire">
						
						<!-- div 1 plage -->
						<c:set var="i" value="1"></c:set>
						<c:forEach items="${event.listePlage }" var="plage">
							<div id="plage-${i }" class="row container-fluid">
							
								<!-- div date+debut+fin -->
								<div class="row container-fluid col-md-10" id="ddf">
								
									<!-- div date -->
									<div class="row container-fluid col-md-3 offset-md-1 col-10" id="date">
										<label  class="col-2" style="margin-top:5px;"> date </label>
										<input class="form-control" value="${plage.date }" type="date" required="required" id="date-${i }" name="date-${i }">
									</div>
									
									<!-- div debut -->
		 							<div class=" row container-fluid col-md-3 offset-md-1 col-10" id="debut">
										<label class="col-2" for="debut-${i }" style="margin-top:5px;">début</label>
										<input class="form-control" value="${plage.heureDebut }" name="debut-${i }" type="time" required="required" id="debut-${i }">
									</div>
									
									<!-- div fin -->
									<div class="row container-fluid col-md-3 offset-md-1 col-10" id="fin">
										<label class="col-2" for="fin-${i }" style="margin-top:5px;">fin</label>
										<input class="form-control" value="${plage.heureFin }" name="fin-${i }" type="time" required="required" id="fin-${i }">
									</div>
								</div>
								<div class="container col-md-1 col-2 ml-auto">
									<span class="col-12"> </span>
									<input style="padding-left:5px;padding-right:5px;" class="btn btn-danger col-12" type="button" required="required" value="X" name="sup-${i }" onclick="supprimerPlage('plage-${i }')">
								</div>
							</div>
						</c:forEach>
						</div>
							<input type="button" value="Ajouter une plage horaire" class="btn btn-info" onclick="ajouterHoraire(event)">
						</div>
					</div>
				</div>
				
				<div class="row col-12">
					<div class="col-md-4" style="margin-top:30px;">
						<div>
							<h2>Description</h2>
							<input value="${event.description }" id="input-description" type="text" name="input-description" placeholder="Entrer une description">
						</div>
				
						<div>
							<div style="margin-top:30px;">
								<h2>Nombre de personne requise</h2> 
								<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.nbParticipantsRequis}" name="input-personnerequise" required="required" id="input-personnerequise" type="number" onchange="if(document.getElementById('input-personnerequise').value < 0 )document.getElementById('input-personnerequise').value = 0"></p>
							</div>
								<h2>Section requise </h2>
								<br>
								<c:forEach items="${sects}" var="sect">
				 					<label for="Sections">
				 						${sect.nom }
				 					</label>
				 					<input type="checkbox" name="section-${sect.id}"> <br>
				 				</c:forEach>
							</div>
						</div>
					
					<div class="col-md-8" style="margin-top:15px;">
						<p>
							<h2>Adresse de l'evenement : </h2>
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.rue }" style="margin-bottom: 10px;" type="text" name="input-rue" id="input-rue" placeholder="Entrer la rue"> 
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.numero }" style="margin-bottom: 10px;" type="number" name="input-numero" id="input-numero" placeholder="Entrer le numero" onchange="if(document.getElementById('input-numero').value < 0 )document.getElementById('input-numero').value = 0">
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.boite }" style="margin-bottom: 10px;" type="text" name="input-boite" id="input-boite" placeholder="Entrer la boite">
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.codePostal }" style="margin-bottom: 10px;" type="number" name="input-codePostal" id="input-codePostal" placeholder="Entrer le Code postal" onchange="if(document.getElementById('input-codePostal').value < 0 )document.getElementById('input-codePostal').value = 0">
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.localite }" style="margin-bottom: 10px;" type="text" name="input-localite" id="input-localite" placeholder="Entrer la localité"> 
							<br>
							<input class="form-control offset-md-3 col-md-6 offset-1 col-10" value="${event.adresseEve.pays }" style="margin-bottom: 10px;" type="text" name="input-pays" id="input-pays" placeholder="Entrer le pays">
						</p>
					</div>
					
				</div>
					<input class="btn btn-info" type="submit" value="Enregistrer" style="margin-bottom:30px;">
				<div>
			</form>
		</div>
	</body>
</html>