<%
	if (session.getAttribute("Valide") == null){
		response.sendRedirect("/inscription.jsp");
	}

	if (request.getAttribute("Nom") == null && session.getAttribute("Valide") != null) {
		response.sendRedirect("/affichageProfil");
	}
	System.out.println("Le nom est "+request.getAttribute("Nom"));
	System.out.println("Le prenom est "+request.getAttribute("Prenom"));
	System.out.println("Le slogan est "+request.getAttribute("Slogan"));
%>
	<%@page isELIgnored ="false" %>
	<%@ page contentType="text/html; charset=UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@page import="java.util.List" %>
	<%@page import="social.network.Personne" %>
	<%@page import="social.network.Interet" %>
	<%@page import="social.network.Publication" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Agora</title>
<link rel="icon" href="favicon.ico" />

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/moncss.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="/affichageProfil"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Agora</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/about.jsp">A propos</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left" role="search" method="post" action="recherche">
						<div class="form-group">
							<input type="text" class="form-control" name="recherche" placeholder="Rechercher">
						</div>
						<button type="submit" class="btn btn-primary">Envoyer</button>
					</form>
					<li><a href="/profil"><img src="images/minions.jpg" alt="Moi" class="image_post size32">&nbsp;&nbsp;Mon profil</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Options <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Paramètres</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="/deconnexion">Se deconnecter</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
		 	<!-- Colonne de gauche affichant le profile et les tendances -->
			<div class="col-md-3">
				<!-- Thumbnail affichant le profil du user -->
				<div class="thumbnail border shadow">
					<div class="full_size">
						<img class=" images_test" src="images/aigle.jpg" alt="Image">
					</div>
					<div>
						<img src="images/minions.jpg" alt="Photo de profile" class="img-circle image_profile">
					</div>
					<div class="caption text-center">
						<h3><%= request.getAttribute("Prenom")%> <%= request.getAttribute("Nom")%></h3>
						<p><%= request.getAttribute("Slogan")%></p>
						<hr>
					</div>
					<div class="row">
						<div class="col-md-offset-3 col-md-2 text-center">
							<p>#Tags</p>
							<p><span class="badge"><%= request.getAttribute("NbTags")%></span></p>
						</div>
						<div class="col-md-offset-1 col-md-2  text-center divider-vertical">
							<p>Contacts</p>
							<p><span class="badge"><%= request.getAttribute("NbAmis")%></span></p>
						</div>
					</div>
					<% if (request.getAttribute("Statut") != null) {
						//Si l'attribut status est != de null alors on regarde un autre profil.
						//Et on doit afficher quelque chose
							if (request.getAttribute("Statut").equals("NonSuivi")) {%>
							<div class="row">
								</br>
								<form method="post" action="affichageProfil">
									<input type="hidden" name="id" value=<%= request.getAttribute("Id")%>>
									<input type="hidden" name="suivre" value="true">
									<div class="col-md-12 text-center">
										<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Suivre</button>
									</div>
								</form>
							</div>
							<%	//On ajoute le bouton "+ Suivre" car on ne suit pas la personne que l'on regarde.
							} else if (request.getAttribute("Statut").equals("Suivi")) {
								//On ajoute le bouton "- Ne plus suivre".
								%><div class="row">
									</br>
									<form method="post" action="affichageProfil">
										<input type="hidden" name="id" value=<%= request.getAttribute("Id")%>>
										<input type="hidden" name="suivre" value="false">
										<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>&nbsp;Ne plus suivre</button>
										</div>
									</form>
								</div>
							<%}
						}%>
				</div>
				<div class="thumbnail border shadow">
					<div class="tendance">
						<h3 align="center">Tendances</h3>
						 <div class="list-group">
							<a href="#" class="list-group-item active">#First item</a>
							<a href="#" class="list-group-item" style="color:#337AB7">#Second item</a>
							<a href="#" class="list-group-item" style="color:#337AB7">#Third item</a>
						</div>
					</div>
				</div>
				<!-- Fin thumbnail profil -->
			</div>
			<!-- Fin colonne de gauche -->
			<!-- Colonne du milieu affichant une zone de texte et le flux des autres personnes -->
			<div class="col-md-6">
			 	<!-- Zone de text -->
				<div class="thumbnail border shadow padding_top">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#message">Message</a></li>
						<li><a href="#image">Image</a></li>
						<li><a href="#video">Vidéo</a></li>
					</ul>
					<div class="tab-content">
					  <!-- Tab message -->
					  <div id="message" class="tab-pane fade in active">
					  	<div>
						    <br/>
							<form id="formNewPub" class="form-horizontal" role="form" method="post" action="/">
							  <div class="form-group">
							    <label class="control-label col-sm-2" for="email"><img src="images/minions.jpg" alt="Moi" class="image_post size32"></label>
							    <div class="col-sm-10">
									<% if (request.getParameter("id") != null) {%>
										<textarea onfocus="showFull()" onblur="showReduce()" style="height:35px;width:420px;resize:vertical" class="form-control" id="pub_aera" placeholder="Laissez un message pour <%= request.getAttribute("Prenom")%> ..."></textarea>
									<%} else {%>
										<textarea onfocus="showFull()" onblur="showReduce()" style="height:35px;width:420px;resize:vertical" class="form-control" id="pub_aera" placeholder="Quoi de neuf ?"></textarea>
									<%}%>
							    </div>
							  </div>
							  <div class="form-group" id="btn_pub" hidden>
							      <div class="col-sm-offset-2 col-sm-10">
							        <button type="submit" id="submit_pub" class="btn btn-primary">Publie !</button>
							      </div>
							   </div>
							</form>
						</div>
					  </div>
					  <!-- fin Tab message -->
					  <!-- Tab image -->
					  <div id="image" class="tab-pane fade">
					  <br/>
						<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label class="control-label col-sm-2" ><img src="images/minions.jpg" alt="Moi" class="image_post size32"></label>
						    <div class="col-sm-10">
						      <textarea  onfocus="cacheMoi()" class="form-control vertical-resize" rows="4" id="comment" placeholder="Quoi de neuf ?"></textarea>
						    </div>
						  </div>
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-primary">Publie !</button>
						    </div>
						  </div>
						</form>
					  </div>
					   <!-- Fin Tab image -->
					   <!-- Tab video -->
					  <div id="video" class="tab-pane fade">
				  		<h3>Menu 2</h3>
				    	<p>Some content in menu 2.</p>
					  </div>
					   <!-- Fin Tab video -->
					</div>
				</div>
				<!-- Fin zone de text -->

				<!-- Navbar affichage des publications (3 catégories) -->
				<nav class="navbar navbar-inverse">
				  <div class="container-fluid">
				    <!-- Brand and toggle get grouped for better mobile display -->
				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="collapse navbar-collapse">
				      <ul class="nav navbar-nav">
						<% if (request.getAttribute("mesPublications") != null) {%>
							<li>				<a href=<%= "/afficherFlux?id="+request.getAttribute("Id")%>>Flux</a></li>
							<li>				<a href=<%= "/afficherMessagesReçus?id="+request.getAttribute("Id")%>>Messages reçus</a></li>
							<li class="active">	<a href=<%= "/afficherMesPublications?id="+request.getAttribute("Id")%>>Publications</a></li>
						<%} else if (request.getAttribute("mesMessagesReçus") != null){%>
							<li>				<a href=<%= "/afficherFlux?id="+request.getAttribute("Id")%>>Flux</a></li>
							<li class="active">	<a href=<%= "/afficherMessagesReçus?id="+request.getAttribute("Id")%>>Messages reçus</a></li>
							<li>				<a href=<%= "/afficherMesPublications?id="+request.getAttribute("Id")%>>Publications</a></li>
						<%} else { %>
							<li class="active">	<a href=<%= "/afficherFlux?id="+request.getAttribute("Id")%>>Flux</a></li>
							<li>				<a href=<%= "/afficherMessagesReçus?id="+request.getAttribute("Id")%>>Messages reçus</a></li>
							<li>				<a href=<%= "/afficherMesPublications?id="+request.getAttribute("Id")%>>Publications</a></li>
						<%}%>
				      </ul>
				    </div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>

				<!-- Zone d'affichage des publications -->

				<% if (request.getAttribute("mesPublications") != null) {
						//affichage des posts publiés
						List<Publication> listPublication = (List<Publication>) request.getAttribute("mesPublications");
						if (listPublication != null && listPublication.size() != 0) {%>
							<c:forEach items="${mesPublications}" var="v">
								<div class="thumbnail border shadow padding_top">
									<div class="row">
										<div class="col-md-3">
											<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
										</div>
										<div class="col-md-9">
											<p class="float-left"><%= request.getAttribute("Prenom")%> <%= request.getAttribute("Nom")%></p>
											<%-- <p class="float-right"><%<c:out value="${v.date}"%> min</p> --%>
										</div>
										<div class=" col-md-9">
											<span><c:out value="${v.contenu}"/></span>
										</div>
									</div>
								</div>
							</c:forEach>
					<%	} else {%>
							<h5 align="center">Aucune publication actuellement.</h5>
					<%	}
				   } else if (request.getAttribute("mesMessagesReçus") != null) {
						//affichage des messages reçus
						List<Publication> listMessagesRecus = (List<Publication>) request.getAttribute("mesMessagesReçus");
						if (listMessagesRecus != null && listMessagesRecus.size() != 0) {%>
							<c:forEach items="${mesMessagesReçus}" var="v">
								<div class="thumbnail border shadow padding_top">
									<div class="row">
										<div class="col-md-3">
											<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
										</div>
										<div class="col-md-9">
											<p class="float-left"><%= request.getAttribute("Prenom")%> <%= request.getAttribute("Nom")%></p>
											<%-- <p class="float-right"><%<c:out value="${v.date}"%> min</p> --%>
										</div>
										<div class=" col-md-9">
											<span><c:out value="${v.contenu}"/></span>
										</div>
									</div>
								</div>
							</c:forEach>
					<%	} else {%>
							<h5 align="center">Aucune publication actuellement.</h5>
					<%	}
				   } else {
						//affichage par défaut : flux (=posts contenant interets)
						List<Publication> listFlux = (List<Publication>) request.getAttribute("mesFlux");
						if (listFlux != null && listFlux.size() != 0) {%>
							<c:forEach items="${mesFlux}" var="v">
								<div class="thumbnail border shadow padding_top">
									<div class="row">
										<div class="col-md-3">
											<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
										</div>
										<div class="col-md-9">
											<p class="float-left"><%= request.getAttribute("Prenom")%> <%= request.getAttribute("Nom")%></p>
											<%-- <p class="float-right"><%<c:out value="${v.date}"%> min</p> --%>
										</div>
										<div class=" col-md-9">
											<span><c:out value="${v.contenu}"/></span>
										</div>
									</div>
								</div>
							</c:forEach>
					<%	} else {%>
							<h5 align="center">Aucune publication actuellement.</h5>
					<%	}

				   }%>
			</div>
			
			<!-- Zone d'affichage des intérêts & abonnements -->
			
			<div class="col-md-3">
				<div class="thumbnail border shadow">
					<div class="interets">
						<% if (request.getAttribute("Statut") != null) {%>
							<h3 align="center">Les intérêts de <%= request.getAttribute("Prenom")%></h3>
						<%} else {%>
							<h3 align="center">Mes intérêts</h3>
						<%}%>
						<div class="list-group" id="list-interet">
							<%  List<Interet> listInteret = (List<Interet>)request.getAttribute("resultatInterets");
								if(listInteret != null && listInteret.size() != 0) { %>
									<c:forEach items="${resultatInterets}" var="v">
										<a href="#" class="list-group-item">
											<c:out value="${v.nom}"/>
											<% if (request.getParameter("id") != null) {
												//Ne pas afficher les croix de suppression d'interet.
											} else {%>
												<c:forTokens var="token" items="${v.nom}" delims="#">
														<form class="display" method="post" action="/removeInteret?interet=<c:out value="${token}"/>">
												</c:forTokens>
													<button type="submit" class="btn btn-default btn-xs display"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
										<%	}%>
											</form>
										</a>

									</c:forEach>
							<% 	} else {%>
									<h5 align="center">Aucun intérêt actuellement.</h5>
							<%	}%>
						</div>
						<% if (request.getParameter("id") != null) {
							//Ne pas afficher le formulaire d'ajout d'interets.
						} else {%>
							<form id="formNewTag" class="form-horizontal" role="interets" method="post" action="/">
								<div class="form-group">
									<div class="col-sm-7">
										<input type="text" class="form-control" id="tagInput" placeholder="#Exemple">
									</div>
									<div class="col-sm-5">
										<button type="submit" class="btn btn-primary">Ajouter</button>
									</div>
								</div>
							</form>
					<%	}%>
					</div>
				</div>
				<div class="thumbnail border shadow">
					<div class="interets">
						<% 	if (request.getAttribute("Statut") != null) {%>
								<h3 align="center">Les abonnements de <%= request.getAttribute("Prenom")%></h3>
						<%	} else {%>
								<h3 align="center">Mes abonnements</h3>
						<%	}%>
						<div class="list-group">
							<%  List<Personne> listAmis = (List<Personne>)request.getAttribute("resultatAmis");
								if(listAmis != null && listAmis.size() != 0){ %>
									<c:forEach items="${resultatAmis}" var="v">
										<a href="affichageProfil?id=<c:out value="${v.id}"/>" class="list-group-item"> <c:out value="${v.prenom}"/> <c:out value="${v.nom}"/></a>
									</c:forEach>
							<%	} else {%>
									<h5 align="center">Aucun abonnement actuellement.</h5>
							<%	}%>
						</div>
					</div>
				</div>
				<div class="thumbnail border shadow">
					<div class="interets">
						<% if (request.getAttribute("Statut") != null) {%>
								<h3 align="center">Les abonnements avec intérêts en commun de <%= request.getAttribute("Prenom")%></h3>
						<%} else {%>
								<h3 align="center">Mes abonnements avec intérêts en commun</h3>
						<%}%>
						<div class="list-group" id="list-interet">
							<%  List<Personne> listAmisInterets = (List<Personne>)request.getAttribute("resultatCommuns");
								if(listAmisInterets != null && listAmisInterets.size() != 0) { %>
									<c:forEach items="${resultatCommuns}" var="v">
										<a href="affichageProfil?id=<c:out value="${v.id}"/>" class="list-group-item"> <c:out value="${v.prenom}"/> <c:out value="${v.nom}"/></a>
									</c:forEach>
							<% 	} else {%>
									<h5 align="center">Aucun abonnement avec intérêt commun.</h5>
							<%	}%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/monjs.js"></script>
</body>
</html>
