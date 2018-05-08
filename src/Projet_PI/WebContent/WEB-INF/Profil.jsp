<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Profil</title>
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
	        <a class="nav-link" href="/ListEvenSuivPrec">Liste des évènements</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="javascript:void(0)">Mes inscriptions</a>
	      </li>
	    </ul>
		<img src="assets/img/profil.png" alt="profil" style="width:40px;">
		<img src="assets/img/door.png" alt="door" style="width:40px;">
	    <form class="form-inline my-2 my-lg-0">
	      <input class="form-control mr-sm-2" type="text" placeholder="Recherche">
	    </form>
	  </div>
	</nav>
	
	<script language="JavaScript">
	
		function validation(){
			if(document.getElementById("ConfirmerNouveauMotDePasse").value != document.getElementById("NouveauMotDePasse").value){
				document.getElementById("NouveauMotDePasse").focus();
				alert('les mots de passe ne sont pas identiques');
			}
		}
	</script>
	
	
	
	
	 <style>
	 	.center{
	 		margin:auto;
	 		float:none;
	 	}
	 	label{
	 		margin-bottom:2px;
	 		padding-top:15px;
	 		display: block;
	 	}
	 	.inputClass{
	 		width:200px;
	 	}
	 	.texte{
	 		padding-top:15px;
	 	} 
	 </style>
	 <div class="container">
	 <form method="post" onSubmit="return validation(this)" >
	 	<form method="post" action="EnregistrerProfil" class="form">
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Nom">
			 		Nom
			 		</label>
			 		<input class="inputClass" value="${rep.firstName}"  type="text" required="required" placeholder="Nom" name="Nom" id="Nom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Prenom">
	 				Prenom
	 				</label>
	 				<input  class="inputClass" value="${rep.lastName}" type="text" required="required" placeholder="Prenom" name="Prenom" id="Prenom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Mail">
			 		Mail
			 		</label>
			 		<input  class="inputClass" value="${rep.mail}" type="mail" required="required" placeholder="Mail" name="Mail" id="Mail">
	 			</div>
	 		</div>
	 		
		 	<div class="row">
		 		<div class="form-group col-lig-3 center">
			 		<label for="Nouveau Mot De Passe">
				 	Mot de passe
				 	</label>
				 	<input  class="inputClass" type="password" placeholder="Nouveau mot de passe" name="NouveauMotDePasse" id="NouveauMotDePasse">
		 		</div>
		 	</div>
		 	<div class="row">
		 		<div class="form-group col-lig-3 center">
			 		<label for="Confirmer Nouveau Mot De Passe">
				 	Confirmer mot de passe
				 	</label>
				 	<input onchange="validation()" class="inputClass" type="password" placeholder="Confirmer nouveau mot de passe" name="ConfirmerNouveauMotDePasse" id="ConfirmerNouveauMotDePasse">
		 		</div>
		 	</div>
		 		
	 		
	 		<div class="row texte">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Matricule">
		 			Matricule : 
		 			</label>
		 			<c:if test="${rep.matricule != null }">
		 				${rep.matricule}
		 			</c:if>
		 			<c:if test="${rep.matricule == null }">
		 				<input  class="inputClass" value="" type="text" required="required" placeholder="x000000" name="Matricule" id="Matricule">
		 			</c:if>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Phone">
	 				Téléphone
	 				</label>
	 				<input  class="inputClass" value="${rep.phone}" type="text" required="required" placeholder="+32.000.00.00.00" name="Phone" id="Phone">
	 			</div>
	 		</div>
	 		<c:if test="${isEtu == true}">
	 			<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="Pays">
				 		Pays
				 		</label>
				 		<input  class="inputClass" value="${adr.pays}" type="text" required="required" placeholder="Pays" name="Pays" id="Pays">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="codePostal">
				 		Code postal
				 		</label>
				 		<input  class="inputClass" value="${adr.codePostal}" type="text" required="required" placeholder="Code postal" name="CodePostal" id="CodePostal">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="Localite">
				 		Localité
				 		</label>
				 		<input  class="inputClass" value="${adr.localite}" type="text" required="required" placeholder="Localité" name="Localite" id="Localite">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="rue">
				 		Rue
				 		</label>
				 		<input  class="inputClass" value="${adr.rue}" type="text" required="required" placeholder="Rue" name="Rue" id="Rue">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numero">
				 		Numéro
				 		</label>
				 		<input  class="inputClass" value="${adr.numero}" type="text" required="required" placeholder="Numéro" name="Numero" id="Numero">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="boite">
				 		Boite
				 		</label>
				 		<input  class="inputClass" value="${adr.boite}" type="text" placeholder="Boite" name="Boite" id="Boite">
		 			</div>
		 		</div>
	 			<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="DateNaissance">
				 		Date de naissance
				 		</label>
				 		<input  class="inputClass" value="${rep.dateNaissance}" type="date" required="required" placeholder="Date de naissance" name="DateNaissance" id="DateNaissance">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="PaysNaissance">
				 		Pays de naissance
				 		</label>
				 		<input  class="inputClass" value="${rep.paysNaissance}" type="text" required="required" placeholder="Pays de naissance" name="PaysNaissance" id="PaysNaissance">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="lieuNaissance">
				 		lieu de naissance
				 		</label>
				 		<input class="inputClass" value="${rep.lieuNaissance}" type="text" required="required" placeholder="lieu de naissance" name="LieuNaissance" id="LieuNaissance">
		 			</div>
		 		</div>
		 		
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numNational">
				 		Numéro national
				 		</label>
				 		<input class="inputClass" value="${rep.numNational}" type="text" required="required" placeholder="Numéro national" name="NumNational" id="NumNational">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="nationalite">
				 		Nationalitée
				 		</label>
				 		<input class="inputClass" value="${rep.nationalite}" type="text" required="required" placeholder="nationalitée" name="Nationalite" id="Nationalite">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numBanque">
				 		Numéro de banque
				 		</label>
				 		<input class="inputClass" value="${rep.numBanque}" type="text" required="required" placeholder="Numéro de banque" name="NumBanque" id="NumBanque">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="SoutienSocial">
				 		Soutien social
				 		</label>
				 		<input class="inputClass" value="${rep.soutienSocial}" type="checkBox" placeholder="Soutien social" name="SoutienSocial" id="SoutienSocial">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="emplacementEcole">
				 		Emplacement de l'école
				 		</label>
				 		<input class="inputClass" value="${rep.emplacementEcole}" type="text" required="required" placeholder="Emplacement de l'école" name="EmplacementEcole" id="EmplacementEcole">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="role">
				 		Role
				 		</label>
				 		<input class="inputClass" value="${rep.role}" type="text" required="required" placeholder="Role" name="Role" id="Role">
		 			</div>
		 		</div>
	 		</c:if>
	 		<c:if test="${isEtu == false}">
	 			<div class="row texte">
		 			<div class="form-group col-lig-3 center">
			 			Nombre de participation : ${rep.nbParticipations}
			 		</div>
		 		</div>
	 		</c:if>
	 		
	 		<div class="col-lg-12 btn btn-default center-block">
	 			<input type="submit" class="btn btn-primary" value="Enregistrer">
	 		</div>>
	 	
	 	</form>
	 	
	 	<!-- erreur -->
	 	<c:if test="${enregistrementSuccess == false}">
			<div class="alert alert-danger alert-dismissible fade show">
  				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Attention ! </strong>Connection Failed
			</div>
		</c:if>
	 	
	 </div>
	</body>
</html>