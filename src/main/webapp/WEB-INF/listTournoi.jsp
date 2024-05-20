<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!doctype html>
<html lang="fr">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="starter-template.css">
<link rel="stylesheet" href="myCss.css">
<title>Tournois</title>





</head>
<body>


	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<div id="navBlockLeft">
				<p class="navbar-brand" id="LabMenu">Menu</p>
				<ul class="navbar-nav mr-auto">


					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown01"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Tennis</a>
						<div class="dropdown-menu" aria-labelledby="dropdown01">

							<form method="Post" action="ListJoueur">
								<button type="submit" name="menuJoueurs"
									class="btn btn-outline-light" style="color: #212529;">Joueurs</button>
								<button type="submit" name="menuTournois"
									class="btn btn-outline-light" style="color: #212529;">Tournois</button>
								<button type="submit" name="menuMatchs"
									class="btn btn-outline-light" style="color: #212529;">Matchs</button>
								<button type="submit" name="menuEpreuves"
									class="btn btn-outline-light" style="color: #212529;">Epreuves</button>
							</form>
						</div></li>

				</ul>

				<c:if
					test="${ !empty sessionScope.nomUser && !empty sessionScope.prenomUser }">
					<p id="affichageBienvenue">Bienvenue ${ sessionScope.nomUser }
						${ sessionScope.prenomUser } !</p>
				</c:if>
			</div>

			<form method="Post" action="Login">
				<button type="submit" name="deconnexion"
					class="btn btn-outline-light" id="btnDeconnexion">Deconnexion</button>
			</form>


		</div>
	</nav>

	<main role="main" class="container">

		<div class="starter-template">
			<p class="lead">
				<c:if test="${!empty erreur }">
					<p style="color: red;">
						<c:out value="${erreur}" />
					</p>
				</c:if>
			</p>
			<br />
			<h1>Tournois</h1>
			<p class="lead">Liste des tournois de tennis.</p>
		</div>

	</main>


	<%-- ///////////////////////////////Champ tournoi selectionné //////////////////////////////// --%>

	<form class="needs-validation " method="Post" action="ListTournoi"
		novalidate>

		<c:if test="${ sessionScope.profilUser == 1}">
			<div id="champTxt">

				<label for="validationCustom00">Id</label> <input type="text"
					class="form-control" style="width: 80px;" id="validationCustom00"
					name="txtId" value="${lastSelectedTournoi.getId()}" required>
				<div class="valid-feedback">Très bien!</div>


				<label for="validationCustom01">Nom</label> <input type="text"
					class="form-control" style="width: 250px;" id="validationCustom01"
					name="txtNom" value="${lastSelectedTournoi.getNom()}" required>
				<div class="valid-feedback">Très bien!</div>



				<label for="validationCustom02">Code</label> <input type="text"
					class="form-control" style="width: 250px;" id="validationCustom02"
					name="txtCode" value="${lastSelectedTournoi.getCode()}" required>
				<div class="valid-feedback">Très bien!</div>


			</div>
		</c:if>
		<%-- ///////////////////////////////Bouton Crud //////////////////////////////// --%>
		<div id="elementCrud">

			<%-- Recherche --%>
			<label for="validationCustom04" style="width: 100px;">Recherche
				: </label> <select class="custom-select" id="validationCustom04"
				name="txtChampRecherche" style="width: 150px;" required>
				<option selected disabled>Sélectionnez...</option>
				<option value="ID">ID</option>
				<option value="NOM">Nom</option>
				<option value="CODE">Code</option>
			</select>
			<div class="valid-feedback">Très bien!</div>
			<div class="invalid-feedback">Veuillez choisir un champ!</div>

			<input type="text" class="form-control" style="width: 200px;"
				id="validationCustom05" name="txtRecherche">

			<button type="submit" name="rechercherTournoi"
				class="btn btn-outline-primary">Rechercher Tournois</button>


			<c:if test="${ sessionScope.profilUser == 1}">
				<%-- Ajout --%>


				<button type="submit" id="addTournoi" name="addTournoi"
					class="btn btn-primary">Ajouter tournoi</button>


				<%-- Editer --%>

				<button type="submit" name="editTournoi" class="btn btn-warning">Editer
					tournoi</button>


				<%-- Supprimer --%>

				<button type="submit" name="deleteTournoi" class="btn btn-danger">Supprimer
					tournoi</button>
			</c:if>
		</div>

	</form>

	<br />
	<br />
	<br />
	<br />




	<%-- ///////////////////////////////Tableau //////////////////////////////// --%>
	<div style="padding: 2rem; margin-right: 200px; margin-left: 200px;">
		<table class="table">
			<thead>
				<tr>
					<th scope="col" style="width: 10%">ID</th>
					<th scope="col" style="width: 25%">Nom</th>
					<th scope="col" style="width: 20%">Code</th>
					<th scope="col" style="width: 20%"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${dataTournoi}" var="tournoi">
					<tr>
						<th scope="row"><c:out value="${tournoi.getId()}" /></th>
						<td><c:out value="${tournoi.getNom()}" /></td>
						<td><c:out value="${tournoi.getCode()}" /></td>
						<td><form action="ListTournoi" method="post">
								<input type="hidden" name="tournoiId" value="${tournoi.getId()}" />
								<button type="submit" name="selectedTournoi"
									class="btn btn-light">Select</button>
							</form></td>
					</tr>
				</c:forEach>



			</tbody>
		</table>
	</div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>

