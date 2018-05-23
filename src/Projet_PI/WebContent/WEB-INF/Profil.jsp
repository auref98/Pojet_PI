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
	<body class="bg-light">
	
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
				let regExp = /^\+\d{2}(\.)\d{3}(\.)\d{2}(\.)\d{2}(\.)\d{2}$/;
				if(!regExp.test(phone)){
					alert("Le numero de téléphone ne correspond pas\nchanger: 0475.00.00.00 -> +32.475.00.00.00\nExemple : +32.000.00.00.00");
					document.getElementById("Phone").value = "";
				}
			}
		</script>
	
	 
	 	<form method="post" action="EnregistrerProfil" class="row container-fluid">
	 		<!-- erreur -->
		 	<c:if test="${enregistrementSuccess == false}">
				<div class="alert alert-danger alert-dismissible fade show text-center col-md-4 col-11 offset-md-4 offset-1">
		 				<button type="button" class="close" data-dismiss="alert">&times;</button>
		 				<strong>Attention ! </strong>La mise a jour du profil a échoué
				</div>
			</c:if>
			
			<c:if test="${enregistrementSuccess == true}">
				<div class="alert alert-success alert-dismissible fade show text-center col-md-4 col-11 offset-md-4 offset-1">
		 				<button type="button" class="close" data-dismiss="alert">&times;</button>
		 				<span>Votre profil a bien été mis à jour</span>
				</div>
			</c:if>
		 	<!-- Infos Basiques -->
		 	<div class="row container col-md-6"> 
		 		<h2 class="col-12 offset-1">Infos Basiques</h2>
		 		<div class="row form-group col-md-5 col-12 offset-1">
			 			<label for="Nom">
			 				Nom
			 			</label>
				 		<input class="form-control" value="${rep.lastName}"  type="text" required="required" placeholder="Nom" name="Nom" id="Nom">
		 		</div>
		 		
		 		<div class="row form-group col-md-5 col-12 offset-1">
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
		 		
			 	<div class="row form-group col-md-5 col-12 offset-1">
				 		<label for="Nouveau Mot De Passe">
				 			Nouveau mot de passe
				 		</label>
					 	<input  class="form-control mr-sm-2" type="password" placeholder="Mot de passe" name="NouveauMotDePasse" id="NouveauMotDePasse">
			 	</div>
			 	
			 	<div class="row form-group col-md-5 col-12 offset-1">
				 		<label for="Confirmer Nouveau Mot De Passe">
					 		Confirmer le mot de passe
					 	</label>
					 	<input onchange="validation()" class="form-control mr-sm-2" type="password" placeholder="Mot de passe" name="ConfirmerNouveauMotDePasse" id="ConfirmerNouveauMotDePasse">
			 	</div>
		 	
		 		<div class="row texte form-group col-md-5 col-12 offset-1">
		 				<label for="Matricule" class="col-12 pl-0">
			 				Matricule
			 			</label>
			 			<c:if test="${rep.matricule == null || firstConnection == true}">
			 				<input  class="form-control mr-sm-2 col-12" value="" type="text" required="required" placeholder="x000000" name="Matricule" id="Matricule">
			 			</c:if>
			 			<c:if test="${rep.matricule != null && firstConnection == false}">
			 				<label class="col-12">
			 					${rep.matricule}
			 				</label>
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
	 				<div class="row form-group col-12 offset-1 offset-md-0">
				 			<label for="rue">
					 			Rue
					 		</label>
					 		<input  class="form-control mr-sm-2" value="${adr.rue}" type="text" required="required" placeholder="Rue" name="Rue" id="Rue">
			 		</div>
			 		<div class="row form-group col-md-6 col-6 offset-1 offset-md-0">
				 			<label for="numero">
					 			Numéro
					 		</label>
					 		<input  class="form-control mr-sm-2" value="${adr.numero}" type="number" required="required" placeholder="Numéro" name="Numero" id="Numero">
			 		</div>
			 		<div class="row form-group col-md-6 col-5 ml-md-auto offset-1 offset-md-0">
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
			 		
			 		<div class="row form-group col-12 col-md-6 ml-md-auto offset-1 offset-md-0">
				 			<label for="codePostal">
					 			Code postal
					 		</label>
					 		<input  class="form-control mr-sm-2" value="${adr.codePostal}" type="number" required="required" placeholder="Code postal" name="CodePostal" id="CodePostal">
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
			 		<div class="row form-group col-12 col-md-5 offset-1">
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
			 		
			 		<div class="row form-group col-md-11 col-12 offset-1">
				 			<label for="numNational">
					 			Numéro national
					 		</label>
					 		<input class="form-control mr-sm-2" value="${rep.numNational}" type="number" required="required" placeholder="Numéro national" name="NumNational" id="NumNational">
			 		</div>
			 		
			 		<div class="row form-group col-md-11 col-12 offset-1">
				 			<label for="numBanque">
					 			Numéro de banque
					 		</label>
					 		<input class="form-control mr-sm-2" value="${rep.numBanque}" type="text" required="required" placeholder="Numéro de banque" name="NumBanque" id="NumBanque">
			 		</div>
			 	</div>
			 	<!-- Infos Scolaires -->
	 			<div class="row container-fluid col-md-6">
	 				<h2 class="col-12 offset-1">Infos Scolaires</h2>
			 		<div class="row form-group col-12 offset-1 offset-md-0">
			 			<label for="SoutienSocial" class="col-8 mt-md-1 mt-0 ml-0 pl-0">
							Bénéficiez-vous des aides sociales ?
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
									width: 58px;
									height: 33px;
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
				 		<div class="col-2 ml-auto">
				 			<input value="${rep.soutienSocial}" type="checkBox" placeholder="Soutien social" name="SoutienSocial" id="SoutienSocial">
		 					<label for="SoutienSocial"></label>
			 			</div>
			 		</div>
			 		<div class="row form-group col-12 offset-1 offset-md-0">
			 			<label for="emplacementEcole" class="mt-md-1 col-12 col-md-6 pl-0">
				 			Emplacement de l'école
				 		</label>
				 		<select name="EmplacementEcole" id="EmplacementEcole" class="col-md-5 col-12 ml-md-auto form-control">
				 			<option <c:if test="${rep.emplacementEcole == 'Libramont'}">selected="selected"</c:if> value="Libramont">Libramont</option>
				 			<option <c:if test="${rep.emplacementEcole == 'Virton'}">selected="selected"</c:if> value="Virton">Virton</option>
							<option <c:if test="${rep.emplacementEcole == 'Arlon'}">selected="selected"</c:if> value="Arlon">Arlon</option>
				 		</select>
			 		</div>
			 		<div class="row form-group col-12 offset-1 offset-md-0">
				 			<label for="role" class="mt-md-1 col-12 col-md-6 pl-0">
					 		Role
					 		</label>
					 		<select name="Role" id="Role" class="col-md-5 col-12 ml-md-auto form-control">
					 			<option <c:if test="${rep.role == 'representant'}">selected="selected"</c:if> value="representant">representant</option>
					 			<option <c:if test="${rep.role == 'ouvrier'}">selected="selected"</c:if> value="ouvrier">ouvrier</option>
					 		</select>
		 			</div>
			 		<div class="row form-group col-12 offset-1 offset-md-0">
				 			<label for="Section" class="mt-md-1 col-12 col-md-6 pl-0">
					 		Section
					 		</label>
					 		<select id="Section" name="Section" class="col-md-5 col-12 ml-md-auto form-control" style="overflow-y: scroll">
					 			<c:forEach items="${sects}" var="sect">
					 				<option <c:if test="${sect.nom == rep.sec.nom}">selected="selected"</c:if> value="${sect.id }" > ${sect.nom }</option>
					 			</c:forEach>
					 		</select>
		 			</div>
			 	</div>
		 	</c:if>
		 	
	 		<c:if test="${isEtu == false}">
	 		<!-- Infos Scolaires -->
	 			<div class="row container-fluid col-md-6">
	 				<h2 class="col-12 offset-1">Infos Scolaires</h2>
		 			<div class="row form-group col-12 offset-1 offset-md-0">
	 					<label for="Sections" class="mt-md-1 col-12 col-md-6 pl-0">
	 						Sélectionnez les sections dans lesquelles vous enseignez
	 					</label>
	 					<div class="dropdown col-12 col-md-6">
	 						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								Sections
							</button>
	 						<div class="dropdown-menu">
	 							<c:forEach items="${sects}" var="sect">
						 			<div class="form-check dropdown-item">
							 			<label class="form-check-label">
							 				<input class="form-check-input" type="checkbox" name="section-${sect.id}" <c:if test="${check}">checked </c:if> >
							 				${sect.nom }
							 			</label>
							 			<c:set var="check" value="false"/>
							 			<c:forEach items="${sect.listeProf }" var="prof">
							 				<c:if test="${prof.id == rep.id }">
							 					<c:set var="check" value="true"/>
							 				</c:if>
							 			</c:forEach>
				 					</div>
				 				</c:forEach>
	 						</div>
	 					</div>
			 		</div>
			 	
		 			<div class="row texte form-group col-12 offset-1 offset-md-0">
				 			Nombre de participations : ${rep.nbParticipations}
				 	</div>
			 	</div>
	 		</c:if>
	 		
	 		<!-- Modal button-->
	 			<div class="row col-12 mb-3">
	 				<button	type="button" class="btn btn-primary col-md-2 col-10 offset-md-5 offset-2" data-toggle="modal" data-target="#modalMDP">
	 					Enregistrer
	 				</button>
	 		
	 			</div>
	 		<!-- /Modal button -->
	 		<!-- Modal -->
	 			<div class="modal fade" id="modalMDP">
					<div class="modal-dialog">
						<div class="modal-content">
						  
							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Entrez votre mot de passe actuel</h4>
								<button type="button" class="close" data-dismiss="modal">×</button>
							</div>
							
							<!-- Modal body -->
							<div class="modal-body">
								<h6 class="mb-2">Veuillez confirmer les modifications en entrant votre mot de passe actuel</h6>
								<input type="password" placeholder="mot de passe" class="form-control mt-2" required="required" name="AncienMotDePasse" id="AncienMotDePasse">
							</div>
							
							<!-- Modal footer -->
							<div class="modal-footer">
							
								<input type="submit" class="btn btn-primary" value="Enregistrer"></button>
								<button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
							</div>
						</div>
			    	</div>
				</div>
	 		<!-- /Modal -->
	 		<!-- Ancien bouton
	 		<div class="row col-12 mb-3">
	 			<input type="submit" class="btn btn-primary col-md-2 col-10 offset-md-5 offset-2" value="Enregistrer">
	 		</div>
	 		-->
	 	</form>
	</body>
</html>