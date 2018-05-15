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
		
		<script language="JavaScript">
			function ajouterHoraire(event){
				event.preventDefault();
				
				let d = "date-";
				let i = 1;
				while(document.getElementById(d+i) != null){
				    i++;
				}
				let input_date = document.createElement("input");
				input_date.type = "date";
				input_date.name = "date-"+i
				input_date.id = d+i;
				
				let label_Debut = document.createElement("label");
				label_Debut.innerText = "début";
				label_Debut.name = "debut-"+i;
				label_Debut.id = "debut-"+i;
				
				let input_debut = document.createElement("input");
				input_debut.type = "time";
				input_debut.name = "debut-"+i;
				input_debut.id = "debut-"+i;
				
				let label_Fin = document.createElement("label");
				label_Fin.innerText = "fin";
				label_Fin.name = "fin-"+i;
				label_Fin.id = "fin-"+i; 
				
				let input_fin = document.createElement("input");
				input_fin.type = "time";
				input_fin.name = "fin-"+i;
				input_fin.id = "fin-"+i;
				
				let input_button = document.createElement("input");
				input_button.type = "button";
				input_button.value = "X";
				input_button.name = "sup-"+i;
				input_button.addEventListener("click",() => {supprimerPlage("plage-" + i)});
				
				let div = document.createElement("div");
				div.id = "plage-"+i;
				div.appendChild(input_date);
				div.appendChild(label_Debut);
				div.appendChild(input_debut);
				div.appendChild(label_Fin);
				div.appendChild(input_fin);
				div.appendChild(input_button);
				
				document.getElementById("plageHoraire").appendChild(div);
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
		</script>
		
		<form class="container-fluid bg-light text-center" style="margin-top:30px;margin-bottom:30px;" action="EnregistrerEvenement" method="post">
			<div class="row col-12">
				<!-- image -->
				<div class="col-md-4 order-first">
					<img id="image" alt="Inserer une image" src="http://www.paanpaan.com/wp-content/uploads/2017/02/no-image.png" width="auto" height="200">
					<input type="text" required="required" name="image" id="input-image" value="http://www.paanpaan.com/wp-content/uploads/2017/02/no-image.png" onchange="changerImage()">
				</div>
				
				<!-- nom -->
				<div class="col-md-8" style="margin-top:30px;">
					<h1>Nom</h1>
					<input name="nomEvenement" required="required" id="nomEvenement" type="text" placeholder="Nom de l'evenement">
					<div style="margin-top:30px;">
						<h2>Entrer une plage horaire </h2>
						<div id="plageHoraire">
							<div id="plage-1"><!-- 
							 --><input type="date" required="required" id="date-1" name="date-1"><!-- 
							--><label for="debut-1">début</label><input name="debut-1" required="required" type="time" id="debut-1"><!-- 
							 --><label for="fin-1">fin</label><input name="fin-1" required="required" type="time" id="fin-1"><!-- 
							--><input type="button" required="required" value="X" name="sup-1" onclick="supprimerPlage('plage-1')"><!-- 
						--></div>
						</div>
						<input type="button" value="Ajouter une plage horaire" class="btn btn-info" onclick="ajouterHoraire(event)">
					</div>
				</div>
			</div>
			
			<div class="row col-12">
				<div class="col-md-4" style="margin-top:30px;">
					<div>
						<h2>Description</h2>
						<input id="input-description" required="required" type="text" name="input-description" style="margin-top: 25px;" placeholder="Entrer une description">
					</div>
					
					<div>
						<diV style="margin-top:30px;">
							<h2>Nombre de personne requise</h2>
							<input name="input-personnerequise" required="required" id="input-personnerequise" style="margin-top: 10px;" placeholder="Nombre" type="number" onchange="if(document.getElementById('input-personnerequise').value < 0 )document.getElementById('input-personnerequise').value = 0"></p>
						</diV>
							<h2>Section requise</h2>
							<br>
							<c:forEach items="${sects}" var="sect">
					 			<label for="Sections">
					 				${sect.nom }
					 			</label>
					 			<input type="checkbox" name="section-${sect.id}"> <br>
					 		</c:forEach>
						</p>
					</div>
				</div>
				
				<div class="col-md-8" style="margin-top:15px;">
					<p>
						<h2>Adresse de l'evenement : </h2>
						<br>
						<input style="margin-bottom: 10px;" required="required" type="text" name="input-rue" id="input-rue" placeholder="Entrer la rue"> 
						<br>
						<input style="margin-bottom: 10px;" required="required" type="number" name="input-numero" id="input-numero" placeholder="Entrer le numero" onchange="if(document.getElementById('input-numero').value < 0 )document.getElementById('input-numero').value = 0">
						<br>
						<input style="margin-bottom: 10px;" required="required" type="text" name="input-boite" id="input-boite" placeholder="Entrer la boite">
						<br>
						<input style="margin-bottom: 10px;" required="required" type="number" name="input-codePostal" id="input-codePostal" placeholder="Entrer le Code postal" onchange="if(document.getElementById('input-codePostal').value < 0 )document.getElementById('input-codePostal').value = 0">
						<br>
						<input style="margin-bottom: 10px;" required="required" type="text" name="input-localite" id="input-localite" placeholder="Entrer la localité"> 
						<br>
						<input style="margin-bottom: 10px;" required="required" type="text" name="input-pays" id="input-pays" placeholder="Entrer le pays">
					</p>
				</div>
				
			</div>		
			<!-- description -->
			
			<div>
				<input class="btn btn-info" type="submit" value="Enregistrer" style="margin-bottom:30px;">
			</div>
			
		</form>
	</body>
</html>
