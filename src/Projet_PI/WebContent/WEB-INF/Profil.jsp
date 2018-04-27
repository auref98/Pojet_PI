<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	 </style>
	 <div class="container">
	 	<form method="post" action="" class="form">
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Nom">
			 		Nom
			 		</label>
			 		<input class="inputClass"  type="text" placeholder="Nom" name="Nom" id="Nom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Prenom">
	 				Prenom
	 				</label>
	 				<input  class="inputClass" type="text" placeholder="Prenom" name="Prenom" id="Prenom">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Mail">
			 		Mail
			 		</label>
			 		<input  class="inputClass" type="mail" placeholder="Mail" name="Mail" id="Mail">
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
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
		 			<label for="Matricule">
			 		Matricule
			 		</label>
			 		<input class="inputClass"  type="text" placeholder="Matricule" name="Matricule" id="Matricule">
	 			</div>
	 		</div>
	 		<div class="row">
	 			<div class="form-group col-lig-3 center">
	 				<label for="Phone">
	 				Téléphone
	 				</label>
	 				<input  class="inputClass" type="text" placeholder="+32.000.00.00.00" name="Phone" id="Phone">
	 			</div>
	 		</div>
	 		
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="Mail">
				 		Mail
				 		</label>
				 		<input  class="inputClass" type="text" placeholder="Mail" name="Mail" id="Mail">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="MotDePasse">
				 		Mot de passe
				 		</label>
				 		<input  class="inputClass" type="text" placeholder="Mot de passe" name="MotDePasse" id="MotDePasse">
		 			</div>
		 		</div>
		 		<div class="row">
		 			<div class="form-group col-lig-3 center">
			 			<label for="ConfirmerMotDePasse">
				 		Confirmer mot de passe
				 		</label>
				 		<input class="inputClass" type="text" placeholder="Confirmer mot de passe" name="ConfirmerMotDePasse" id="ConfirmerMotDePasse">
		 			</div>
		 		</div>
	 	</form>
	 </div>
	</body>
</html>