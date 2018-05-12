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
	  	<c:if test="${firstConnection == false}">
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
		</c:if>
		<c:if test="${firstConnection == true}">
			<div class="collapse navbar-collapse" id="navb">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item">
		        <a class="nav-link">Liste des évènements</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link">Mes inscriptions</a>
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
			<img src="assets/img/profil.png" alt="profil" style="width:40px;">
			<a href="Deconnection"><img src="assets/img/door.png" alt="door" style="width:40px;"></a>
		    <form class="form-inline my-2 my-lg-0">
		      <input class="form-control mr-sm-2" type="text" placeholder="Recherche">
		    </form>
		  </div>
		</c:if>
	</nav>
	
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
	 	<form method="post" action="EnregistrerProfil" class="form">
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Nom">
			 		Nom
			 		</label>
			 		<input class="inputClass" value="${rep.lastName}"  type="text" required="required" placeholder="Nom" name="Nom" id="Nom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Prenom">
	 				Prenom
	 				</label>
	 				<input  class="inputClass" value="${rep.firstName}" type="text" required="required" placeholder="Prenom" name="Prenom" id="Prenom">
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
		 			<c:if test="${rep.matricule == null || firstConnection == true}">
		 				<input  class="inputClass" value="" type="text" required="required" placeholder="x000000" name="Matricule" id="Matricule">
		 			</c:if>
		 			<c:if test="${rep.matricule != null && firstConnection == false}">
		 				${rep.matricule}
		 			</c:if>
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Phone">
	 				Téléphone
	 				</label>
	 				<input  onchange="validNum()" class="inputClass" value="${rep.phone}" type="text" required="required" placeholder="+32.000.00.00.00" name="Phone" id="Phone">
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
				 		<select name="EmplacementEcole" id="EmplacementEcole" class="inputClass">
				 			<option <c:if test="${rep.emplacementEcole == 'Libramont'}">selected="selected"</c:if> value="Libramont">Libramont</option>
				 			<option <c:if test="${rep.emplacementEcole == 'Virton'}">selected="selected"</c:if> value="Virton">Virton</option>
							<option <c:if test="${rep.emplacementEcole == 'Arlon'}">selected="selected"</c:if> value="Arlon">Arlon</option>
				 		</select>
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="role">
				 		Role
				 		</label>
				 		<select name="Role" id="Role" class="inputClass">
				 			<option <c:if test="${rep.role == 'representant'}">selected="selected"</c:if> value="representant">representant</option>
				 			<option <c:if test="${rep.role == 'ouvrier'}">selected="selected"</c:if> value="ouvrier">ouvrier</option>
				 		</select>
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
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
	 		</c:if>
	 		<c:if test="${isEtu == false}">
	 			<div class="row">
	 				<div class="form-group col-lig-3 center">
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
				 			<input type="checkbox" name="section-${sect.id}" <c:if test="${check}">checked </c:if> >
				 		</c:forEach>
		 			</div>
	 			</div>
	 			<div class="row texte">
		 			<div class="form-group col-lig-3 center">
			 			Nombre de participation : ${rep.nbParticipations}
			 		</div>
		 		</div>
	 		</c:if>
	 		
	 		<div class="col-lg-12 btn btn-default center-block">
	 			<input type="submit" class="btn btn-primary" value="Enregistrer">
	 		</div>
	 	
	 	</form>
	 	
	 	<!-- erreur -->
	 	<c:if test="${enregistrementSuccess == false}">
			<div class="alert alert-danger alert-dismissible fade show">
  				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>Attention ! </strong>La connection a échoué
			</div>
		</c:if>
		
		<c:if test="${enregistrementSuccess == true}">
			<div class="alert alert-success alert-dismissible fade show">
  				<button type="button" class="close" data-dismiss="alert">&times;</button>
  				<strong>OK ! </strong>connection réussie
			</div>
		</c:if>
	 	
	 </div>
	</body>
</html>