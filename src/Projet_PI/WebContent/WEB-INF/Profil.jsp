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
	<!--
	INSERER LA NAV BAR 
	 -->
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
	 	<form method="post" action="" class="form">
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Nom">
			 		Nom
			 		</label>
			 		<input class="inputClass" value="${rep.firstName}"  type="text" placeholder="Nom" name="Nom" id="Nom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Prenom">
	 				Prenom
	 				</label>
	 				<input  class="inputClass" value="${rep.lastName}" type="text" placeholder="Prenom" name="Prenom" id="Prenom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Mail">
			 		Mail
			 		</label>
			 		<input  class="inputClass" value="${rep.mail}" type="mail" placeholder="Mail" name="Mail" id="Mail">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="MotDePasse">
			 		Mot de passe
			 		</label>
			 		<input  class="inputClass" type="password" placeholder="Mot de passe" name="MotDePasse" id="MotDePasse">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="ConfirmerMotDePasse">
			 		Confirmer mot de passe
			 		</label>
			 		<input class="inputClass" type="password" placeholder="Confirmer mot de passe" name="ConfirmerMotDePasse" id="ConfirmerMotDePasse">
	 			</div>
	 		</div>
	 		<div class="row texte">
	 			<div class="form-group col-lig-3 center">
		 			Matricule : ${rep.matricule}		 		
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Phone">
	 				Téléphone
	 				</label>
	 				<input  class="inputClass" value="${rep.phone}" type="text" placeholder="+32.000.00.00.00" name="Phone" id="Phone">
	 			</div>
	 		</div>
	 		<c:if test="${isEtu == true}">
	 			<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="Pays">
				 		Pays
				 		</label>
				 		<input  class="inputClass" value="${adr.pays}" type="text" placeholder="Pays" name="Pays" id="Pays">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="codePostal">
				 		Code postal
				 		</label>
				 		<input  class="inputClass" value="${adr.codePostal}" type="text" placeholder="Code postal" name="codePostal" id="codePostal">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="Localite">
				 		Localité
				 		</label>
				 		<input  class="inputClass" value="${adr.localite}" type="text" placeholder="Localité" name="Localite" id="Localite">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="rue">
				 		Rue
				 		</label>
				 		<input  class="inputClass" value="${adr.rue}" type="text" placeholder="Rue" name="rue" id="rue">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numero">
				 		Numéro
				 		</label>
				 		<input  class="inputClass" value="${adr.numero}" type="text" placeholder="Numéro" name="numero" id="numero">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="boite">
				 		Boite
				 		</label>
				 		<input  class="inputClass" value="${adr.boite}" type="text" placeholder="Boite" name="boite" id="boite">
		 			</div>
		 		</div>
	 			<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="DateNaissance">
				 		Date de naissance
				 		</label>
				 		<input  class="inputClass" value="${rep.dateNaissance}" type="text" placeholder="Date de naissance" name="DateNaissance" id="DateNaissance">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="PaysNaissance">
				 		Pays de naissance
				 		</label>
				 		<input  class="inputClass" value="${rep.paysNaissance}" type="text" placeholder="Pays de naissance" name="PaysNaissance" id="PaysNaissance">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="lieuNaissance">
				 		lieu de naissance
				 		</label>
				 		<input class="inputClass" value="${rep.lieuNaissance}" type="text" placeholder="lieu de naissance" name="lieuNaissance" id="lieuNaissance">
		 			</div>
		 		</div>
		 		
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numNational">
				 		Numéro national
				 		</label>
				 		<input class="inputClass" value="${rep.numNational}" type="text" placeholder="Numéro national" name="numNational" id="numNational">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="nationalite">
				 		Nationalitée
				 		</label>
				 		<input class="inputClass" value="${rep.nationalite}" type="text" placeholder="nationalitée" name="nationalite" id="nationalite">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="numBanque">
				 		Numéro de banque
				 		</label>
				 		<input class="inputClass" value="${rep.numBanque}" type="text" placeholder="Numéro de banque" name="numBanque" id="numBanque">
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
				 		<input class="inputClass" value="${rep.emplacementEcole}" type="text" placeholder="Emplacement de l'école" name="emplacementEcole" id="emplacementEcole">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="role">
				 		Role
				 		</label>
				 		<input class="inputClass" value="${rep.role}" type="text" placeholder="Role" name="role" id="role">
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
	 	</form>
	 </div>
	</body>
</html>