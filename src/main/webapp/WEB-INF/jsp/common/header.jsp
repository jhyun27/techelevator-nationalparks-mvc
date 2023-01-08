<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><c:out value="National Parks" /></title>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link href="<c:url value="/css/site.css"/>" rel="stylesheet"
	type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"
	integrity="sha256-0rguYS0qgS6L4qVzANq4kjxPLtvnp5nn2nB5G1lWRv4="
	crossorigin="anonymous"></script>
	
<!-- FONTS -->	
<link href="https://fonts.googleapis.com/css?family=Bebas+Neue&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Ewert&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Calistoga&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Odibee+Sans&display=swap" rel="stylesheet">

</head>


<body>
	<c:url var="homeUrl" value="/" />
	<div class="navbar navbar-inverse navbar-fixed-top">
		<a href="${ homeUrl }">
			<img id="logo" src="<c:url value="/img/logo.png" />" />
		</a>
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse">
				<c:url var="homeUrl" value="/" />
				<c:url var="surveyUrl" value="/survey" />
				<c:url var="planTripUrl" value="/planTrip" />
				<c:url var="favoriteParksUrl" value="/favoriteParks" />
				<ul class="nav navbar-nav header-links">
					<li><a href="${homeUrl}">Home</a></li>
					<li><a href="${surveyUrl}">Survey</a></li>
					<li><a href="${favoriteParksUrl}">Favorite Parks</a></li>
					<li><a href="${planTripUrl}">Plan Your Trip</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="content">