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
		<c:if test="${firstConnection == false }">
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
		</c:if>
		<c:if test="${firstConnection == true}">
			<nav class="navbar navbar-expand-md navbar-dark bg-secondary fixed-top">
				<img src="assets/img/logo.png" alt="logo" style="width:100px;padding-right:15px" >
				<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
					<span class="navbar-toggler-icon"></span>
				</button>
	
				<div class="collapse navbar-collapse" id="navb">
					<ul class="navbar-nav mr-auto order-first">
						<li class="nav-item">
							<a class="nav-link">Liste des évènements</a>
						</li>
						<li class="nav-item">
							<a class="nav-link">Mes inscriptions</a>
						</li>
						<c:if test="${relais == true }">
							<li class="nav-item">
								<a class="nav-link">Créer un événement</a>
							</li>
							<li class="nav-item">
								<a class="nav-link">archives</a>
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
		</c:if>
		<div style="margin-top:80px;"></div>
	
	<script language="JavaScript">
	
		function validation(){
			if(document.getElementById("ConfirmerNouveauMotDePasse").value != document.getElementById("NouveauMotDePasse").value){
				document.getElementById("ConfirmerNouveauMotDePasse").value = "";
				document.getElementById("NouveauMotDePasse").value = "";
				document.getElementById("NouveauMotDePasse").focus();
				alert('les mots de passe ne sont pas identiques');
			}
		}
		function validNum(){
			let phone = document.getElementById("Phone").value;
			let regExp = /^\+(32|33)(\.)\d{3}(\.)\d{2}(\.)\d{2}(\.)\d{2}$/;
			if(!regExp.test(phone)){
				alert("Le numero de téléphone ne correspond pas\nchanger: 0475.00.00.00 -> +32.475.00.00.00\nExemple : +32.000.00.00.00");
				document.getElementById("Phone").value = "";
			}
		}
	</script>
	
	 
 	<form method="post" action="EnregistrerProfil" class="row container-fluid">
	 	<!-- Infos Basiques -->
	 	<div class="row container col-md-6"> 
	 		<h2 class="col-12 offset-1">Infos Basiques</h2>
	 		<div class="row bg-light form-group col-md-5 col-12 offset-1">
		 			<label for="Nom">
		 				Nom
		 			</label>
			 		<input class="form-control" value="${rep.lastName}"  type="text" required="required" placeholder="Nom" name="Nom" id="Nom">
	 		</div>
	 		
	 		<div class="row bg-light form-group col-md-5 col-12 offset-1">
	 				<label for="Prenom">
	 					Prénom
	 				</label>
	 				<input  class="form-control mr-sm-2" value="${rep.firstName}" type="text" required="required" placeholder="Prenom" name="Prenom" id="Prenom">
	 		</div>
	 		
	 		<div class="row form-group col-12 container-fluid offset-1">
		 			<label for="Mail" class="col-12" style="padding-left: 0px;">Mail</label>
		 			<div class="row container-fluid col-12">
				 		<input class="form-control mr-sm-2 col-7" value="${mail}" type="text" required="required" placeholder="Mail" name="Mail" id="Mail">
				 		<span class="col-4" style="margin-top:5px;padding-left:0px;">
							${hers}
				 		</span>
				 	</div>
	 		</div>
	 		
		 	<div class="row form-group col-md-5 col-12 offset-1  bg-light">
			 		<label for="Nouveau Mot De Passe">
			 			Nouveau mot de passe
			 		</label>
				 	<input  class="form-control mr-sm-2" type="password" placeholder="Mot de passe" name="NouveauMotDePasse" id="NouveauMotDePasse">
		 	</div>
		 	
		 	<div class="row bg-light form-group col-md-5 col-12 offset-1">
			 		<label for="Confirmer Nouveau Mot De Passe">
				 		Confirmer le mot de passe
				 	</label>
				 	<input onchange="validation()" class="form-control mr-sm-2" type="password" placeholder="Mot de passe" name="ConfirmerNouveauMotDePasse" id="ConfirmerNouveauMotDePasse">
		 	</div>
	 	
	 		<div class="row texte form-group col-md-5 col-12 offset-1">
	 				<label for="Matricule">
		 				Matricule
		 			</label>
		 			<c:if test="${rep.matricule == null || firstConnection == true}">
		 				<input  class="form-control mr-sm-2" value="" type="text" required="required" placeholder="x000000" name="Matricule" id="Matricule">
		 			</c:if>
		 			<c:if test="${rep.matricule != null && firstConnection == false}">
		 				${rep.matricule}
		 			</c:if>
	 		</div>
	 		
	 		<div class="row form-group col-md-5 col-12 offset-1">
	 				<label for="Phone">
	 				Téléphone
	 				</label>
	 				<input  onchange="validNum()" class="form-control mr-sm-2" value="${rep.phone}" type="text" required="required" placeholder="+32.000.00.00.00" name="Phone" id="Phone">
	 		</div>
	 	</div>
	 	
 		<c:if test="${isEtu == true}">
 			<!-- Adresse -->
 			<div class="row container-fluid col-md-6">
 				<h2 class="col-12 offset-1">Adresse</h2>
 				<div class="row bg-light form-group col-12 offset-1 offset-md-0">
			 			<label for="rue">
				 			Rue
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.rue}" type="text" required="required" placeholder="Rue" name="Rue" id="Rue">
		 		</div>
		 		<div class="row form-group col-md-6 col-6 offset-1 offset-md-0">
			 			<label for="numero">
				 			Numéro
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.numero}" type="text" required="required" placeholder="Numéro" name="Numero" id="Numero">
		 		</div>
		 		<div class="row bg-light form-group col-md-6 col-5 ml-md-auto offset-1 offset-md-0">
			 			<label for="boite">
				 			Boite
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.boite}" type="text" placeholder="Boite" name="Boite" id="Boite">
		 		</div>
		 		<div class="row form-group col-md-6 col-12 offset-1 offset-md-0">
			 			<label for="Localite">
				 			Localité
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.localite}" type="text" required="required" placeholder="Localité" name="Localite" id="Localite">
		 		</div>
		 		
		 		<div class="row bg-light form-group col-12 col-md-6 ml-md-auto offset-1 offset-md-0">
			 			<label for="codePostal">
				 			Code postal
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.codePostal}" type="text" required="required" placeholder="Code postal" name="CodePostal" id="CodePostal">
		 		</div>
		 		
	 			<div class="row form-group col-12 offset-1 offset-md-0">
			 			<label for="Pays">
				 			Pays
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${adr.pays}" type="text" required="required" placeholder="Pays" name="Pays" id="Pays">
		 		</div>
		 	</div>
		 	
		 	<!-- Infos Complémentaires -->
 			<div class="row container-fluid col-md-6">
 				<h2 class="col-12 offset-1">Infos Complémentaires</h2>
		 	
	 			<div class="row form-group col-12 col-md-5 offset-1">
			 			<label for="DateNaissance">
				 			Date de naissance
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${rep.dateNaissance}" type="date" required="required" placeholder="Date de naissance" name="DateNaissance" id="DateNaissance">
		 		</div>
		 		<div class="row bg-light form-group col-12 col-md-5 offset-1">
			 			<label for="PaysNaissance">
				 			Pays de naissance
				 		</label>
				 		<input  class="form-control mr-sm-2" value="${rep.paysNaissance}" type="text" required="required" placeholder="Pays de naissance" name="PaysNaissance" id="PaysNaissance">
		 		</div>
		 		<div class="row form-group col-12 col-md-5 offset-1">
			 			<label for="lieuNaissance">
				 			lieu de naissance
				 		</label>
				 		<input class="form-control mr-sm-2" value="${rep.lieuNaissance}" type="text" required="required" placeholder="lieu de naissance" name="LieuNaissance" id="LieuNaissance">
		 		</div>
		 		
		 		<div class="row form-group col-12 col-md-5 offset-1">
			 			<label for="nationalite">
				 			Nationalité
				 		</label>
				 		<input class="form-control mr-sm-2" value="${rep.nationalite}" type="text" required="required" placeholder="nationalité" name="Nationalite" id="Nationalite">
		 		</div>
		 		
		 		<div class="row bg-light form-group col-md-11 col-12 offset-1">
			 			<label for="numNational">
				 			Numéro national
				 		</label>
				 		<input class="form-control mr-sm-2" value="${rep.numNational}" type="text" required="required" placeholder="Numéro national" name="NumNational" id="NumNational">
		 		</div>
		 		
		 		<div class="row bg-light form-group col-md-11 col-12 offset-1">
			 			<label for="numBanque">
				 		Numéro de banque
				 		</label>
				 		<input class="form-control mr-sm-2" value="${rep.numBanque}" type="text" required="required" placeholder="Numéro de banque" name="NumBanque" id="NumBanque">
		 		</div>
		 	</div>
		 	<!-- Infos Scolaires -->
 			<div class="row container-fluid col-md-6">
 				<h2 class="col-12 offset-1">Infos Scolaires</h2>
		 		<div class="row form-group col-md-6 col-12">
		 			<label for="SoutienSocial" class="col-8">
			 			Soutien social
			 		</label>
			 			<style>
			 				input[type=checkbox] {
								display: none
							}
				
							input[type=checkbox]+label {
								cursor: pointer;
								font-size: 1em;
							}
							[id^=SoutienSocial]+label {
								background-color: #FFF;
								padding: 9px;
								border-radius: 50px;
								display: inline-block;
								position: relative;
								margin-right: 30px;
								width: 40px;
								height: 15px;
							}
				
							[id^=SoutienSocial]+label:after {
								content: ' ';
								position: absolute;
								background: #E6332C;
								top: 0;
								left: 0;
								width: 100%;
								height: 100%;
								border-radius: 100px;
								box-shadow: inset 0 0 20px rgba(0,0,0,.2);
							}
				
							[id^=SoutienSocial]+label:before {
								content: ' ';
								position: absolute;
								background: #fff;
								top: 2px;
								left: 2px;
								z-index: 99999;
								width: 29px;
								height: 29px;
								border-radius: 100px;
								box-shadow: 0 0 2px rgba(0,0,0,.5),inset 0 -18px 15px -10px rgba(0,0,0,.05);
							}
				
							[id^=SoutienSocial]+label:active {
								box-shadow: 0 1px 2px rgba(0,0,0,.05),inset 0 1px 3px rgba(0,0,0,.1);
							}
				
							[id^=SoutienSocial]:checked+label:before {
								content: ' ';
								position: absolute;
								left: 27px;
								border-radius: 100px;
							}
							#SoutienSocial+label:after,#SoutienSocial+label:before,#SoutienSocial label {
								-webkit-transition: all .1s ease-in;
								transition: all .1s ease-in;
							}
				
							[id^=SoutienSocial]:checked+label:after {
								content: ' ';
								font-size: 1.5em;
								position: absolute;
								background: #63C99C;
							}
			 			</style>
			 		<input class="form-control mr-sm-2 col-2" value="${rep.soutienSocial}" type="checkBox" placeholder="Soutien social" name="SoutienSocial" id="SoutienSocial">
	 				<label for="SoutienSocial" class="col-1"></label>
		 		</div>
		 		<div class="row bg-light form-group col-md-6">
		 			<label for="emplacementEcole">
			 		Emplacement de l'école
			 		</label>
			 		<select name="EmplacementEcole" id="EmplacementEcole" class="inputClass">
			 			<option <c:if test="${rep.emplacementEcole == 'Libramont'}">selected="selected"</c:if> value="Libramont">Libramont</option>
			 			<option <c:if test="${rep.emplacementEcole == 'Virton'}">selected="selected"</c:if> value="Virton">Virton</option>
						<option <c:if test="${rep.emplacementEcole == 'Arlon'}">selected="selected"</c:if> value="Arlon">Arlon</option>
			 		</select>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3">
			 			<label for="role">
				 		Role
				 		</label>
				 		<select name="Role" id="Role" class="inputClass">
				 			<option <c:if test="${rep.role == 'representant'}">selected="selected"</c:if> value="representant">representant</option>
				 			<option <c:if test="${rep.role == 'ouvrier'}">selected="selected"</c:if> value="ouvrier">ouvrier</option>
				 		</select>
		 			</div>
		 		</div>
		 		<div class="row bg-light">
		 			<div class="form-group col-lig-3">
			 			<label for="Section">
				 		Section
				 		</label>
				 		<select id="Section" name="Section" class="inputClass">
				 			<c:forEach items="${sects}" var="sect">
				 				<option <c:if test="${sect.nom == rep.sec.nom}">selected="selected"</c:if> value="${sect.id }" > ${sect.nom }</option>
				 			</c:forEach>
				 		</select>
		 			</div>
		 		</div>
		 	</div>
	 	</c:if>
	 	
 		<c:if test="${isEtu == false}">
 			<div class="row">
 				<div class="form-group col-lig-3">
 					<p>
 						Sections
 					</p>
			 		<c:forEach items="${sects}" var="sect">
			 			<label for="Sections">
			 				${sect.nom }
			 			</label>
			 			<c:set var="check" value="false"/>
			 			<c:forEach items="${sect.listeProf }" var="prof">
			 				<c:if test="${prof.id == rep.id }">
			 					<c:set var="check" value="true"/>
			 				</c:if>
			 			</c:forEach>
			 			<input type="checkbox" name="section-${sect.id}" <c:if test="${check}">checked </c:if> ><br>
			 		</c:forEach>
	 			</div>
 			</div>
 			<div class="row texte bg-light">
	 			<div class="form-group col-lig-3">
		 			Nombre de participation : ${rep.nbParticipations}
		 		</div>
	 		</div>
 		</c:if>
 		
 		<div style="margin-bottom:30px;display:block;margin:0 auto;width:20%">
 			<input type="submit" class="btn btn-primary" value="Enregistrer">
 		</div>
 	
 	</form>
	 	
	 
	 <!-- erreur -->
	 	<c:if test="${enregistrementSuccess == false}">
			<div class="alert alert-danger alert-dismissible fade show text-center">
  				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Attention ! </strong>La connection a échoué
			</div>
		</c:if>
		
		<c:if test="${enregistrementSuccess == true}">
			<div class="alert alert-success alert-dismissible fade show text-center">
  				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>OK ! </strong>connection réussie
			</div>
		</c:if>
	 
	</body>
</html>