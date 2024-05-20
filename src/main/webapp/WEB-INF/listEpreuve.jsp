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
<title>Epreuve</title>





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

							<form method="Post" action="ListEpreuve">
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
			<h1>Epreuves</h1>
			<p class="lead">Liste des epreuves de tennis.</p>
		</div>

	</main>


	<%-- ///////////////////////////////Champ epreuve//////////////////////////////// --%>

	<form class="needs-validation " method="Post" action="ListEpreuve"
		novalidate>



		<%-- ///////////////////////////////Filtre//////////////////////////////// --%>
		<div id="elementFiltre">

			<div class="groupLabListBtn">
				<%-- Filtre annee --%>
				<label for="validationCustom01" style="width: 100px;">Toutes
					les epreuves : </label>

				<button type="submit" name="listerToutesEpreuves"
					class="btn btn-outline-primary">Lister</button>


			</div>

			<div class="groupLabListBtn">


				<label for="validationCustom04" style="width: 100px;">Année
					: </label> <select class="custom-select" id="validationCustom04"
					name="txtChampAnnee" style="width: 200px;" required>

					<option value="2010">2010</option>
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
				</select>
				<div class="valid-feedback">Très bien!</div>
				<div class="invalid-feedback">Veuillez choisir un champ!</div>

			</div>

			<%-- Filtre Type --%>

			<div class="groupLabListBtn">
				<label for="validationCustom05" style="width: 100px;">Type :
				</label> <select class="custom-select" id="validationCustom05"
					name="txtChampType" style="width: 150px;" required>

					<option value="H">Homme</option>
					<option value="F">Femme</option>

				</select>
				<div class="valid-feedback">Très bien!</div>
				<div class="invalid-feedback">Veuillez choisir un champ!</div>

			</div>
			<button type="submit" id="btnRechercherEpreuve"
				name="rechercherEpreuve" class="btn btn-outline-primary">Rechercher</button>




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
					<th scope="col" style="width: 10%">IdEpreuve</th>
					<th scope="col" style="width: 10%">Année</th>
					<th scope="col" style="width: 10%">Type</th>
					<th scope="col" style="width: 10%">IdMatch</th>
					<th scope="col" style="width: 12%">NomVainqueur</th>
					<th scope="col" style="width: 12%">PrenomVainqueur</th>
					<th scope="col" style="width: 12%">NomFinaliste</th>
					<th scope="col" style="width: 12%">PrenomFinaliste</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${dataEpreuve}" var="epreuve">
					<tr>
						<th scope="row"><c:out
								value="${epreuve.getEpreuve().getId()}" /></th>
						<td><c:out value="${epreuve.getEpreuve().getAnnee()}" /></td>
						<td><c:out value="${epreuve.getEpreuve().getType()}" /></td>
						<td><c:out value="${epreuve.getMatch().getIdMatch()}" /></td>
						<td><c:out value="${epreuve.getVainqueur().getNom()}" /></td>
						<td><c:out value="${epreuve.getVainqueur().getPrenom()}" /></td>
						<td><c:out value="${epreuve.getFinaliste().getNom()}" /></td>
						<td><c:out value="${epreuve.getFinaliste().getPrenom()}" /></td>

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