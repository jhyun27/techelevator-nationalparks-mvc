<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@include file="common/header.jsp"%>


<div id="favorite-parks">
	<h1>Favorite Parks</h1>
	<p>America's favorite parks! Make sure to vote for yours!</p>
	<c:forEach items="${ favoriteParks }" var="park">
		<div id="favorite-parks-park">
			<div id="votes">
				<h2>Votes</h2>
				<h3>${ park.numberOfVotes }</h3>
			</div>

			<div id="favorite-parks-image">
				<c:url value="/detail" var="parkDetailURL">
					<c:param value="${ park.parkCode }" name="parkCode" />
				</c:url>
				<c:url value="/img/parks/${ fn:toLowerCase(park.parkCode) }.jpg"
					var="imgURL" />
				<a href="${ parkDetailURL }"> <img src="${ imgURL }"
					alt="${ park.parkName }" />
				</a>
			</div>

			<div id="favorite-parks-info">
				<h4>${ park.parkName }</h4>
				<table>
					<tr>
						<th>State:</th>
						<td><c:out value="${ park.state }" /></td>
					</tr>
				</table>
				<p>${ park.parkDescription }
			</div>
		</div>
	</c:forEach>
</div>

<%@include file="common/footer.jsp"%>