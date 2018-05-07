<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Accueil</title>
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
	        <a class="nav-link" href="javascript:void(0)">Mes inscriptions</a>
	      </li>
	    </ul>
		<a href="Profil"><img src="assets/img/profil.png" alt="profil" style="width:40px;"></a>
		<a href="Deconnection"><img src="assets/img/door.png" alt="door" style="width:40px;"></a>
	    <form class="form-inline my-2 my-lg-0">
	      <input class="form-control mr-sm-2" type="text" placeholder="Recherche">
	    </form>
	  </div>
	</nav>
	
	 <style>
	 	
	 </style>
	 <h1>Page <c:out value="${debut+cpt}"></c:out></h1>
	 <c:set var="i" value="0"/>
	 <c:forEach items="${ evens }" var="even">
	 	<c:if test="${i%2==0 }">
		 	<div>
		 		<img alt="enements" src="${even.image }">
		 	</div>
	 	</c:if>
	 	<div>
	 		<h2>${even.nom}</h2>
	 		<c:forEach items="${even.listPlage }" var="date">
	 			<p>${date.date }</p>
	 		</c:forEach>
	 		<p>Description : <br>${even.description}</p>
	 		<form method="post" action="inscriptionEvenement" name="${even.id }">
	 			<input type="submit" value="Inscription">
	 		</form>
	 		<form method="post" action="DetailEvenement">
	 			<input type="submit" value="Detail">
	 		</form>
	 	</div>
	 	<c:if test="${i%2==1 }">
		 	<div>
		 		<img alt="enements" src="${even.image }">
		 	</div>
	 	</c:if>
	 	<c:set var="i" value="${i+1}"/>
	 </c:forEach>
	 <c:if test="${debut > 0}">
	 	<form method="get" action="ListEvenSuivPrec">
		 	<input type="submit" value="Page ${debut}" name="Precedent">
		 </form>
	 </c:if>
	 <c:if test="${suiv == true }">
		<form method="get" action="ListEvenSuivPrec">
	 		<input type="submit" value="Page ${debut+cpt+1}" name="Suivant">
	 	</form>
	 </c:if>
	</body>
</html>