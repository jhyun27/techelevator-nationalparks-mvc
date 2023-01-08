<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="common/header.jsp"%>

<div id="detail-page-header">
	<c:url value="/img/parks/${ fn:toLowerCase(park.parkCode) }.jpg" var="imgURL"/>
	<img id="detailImage" src="${ imgURL }" alt="${ park.parkName }" />
	
	<h1><c:out value="${ park.parkName }" /></h1>
</div>
<div id="park-information">
	<div id="info">
		<h2>Info</h2>
		<table>
			<tr>
				<th>State:</th>
				<td>
					<c:out value="${ park.state }"/>
				</td>
			</tr>
			<tr>
				<th>Entry Fee:</th>
				<td>
					<c:choose>
						<c:when test="${ park.entryFee == 0 }">
							FREE
						</c:when>
						<c:otherwise>
							$<c:out value="${ park.entryFee }"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>Acreage:</th>
				<td>
					<c:out value="${ park.acreage }"/>
				</td>
			</tr>
			<tr>
				<th>Elevation (feet):</th>
				<td>
					<c:out value="${ park.elevationFt }"/>
				</td>
			</tr>
			<tr>
				<th>Miles of Trail:</th>
				<td>
					<c:out value="${ park.milesOfTrail }"/>
				</td>
			</tr>
			<tr>
				<th>Number of Campsites:</th>
				<td>
					<c:out value="${ park.numberOfCampsites }"/>
				</td>
			</tr>
			<tr>
				<th>Climate:</th>
				<td>
					<c:out value="${ park.climate }"/>
				</td>
			</tr>
			<tr>
				<th>Year Founded:</th>
				<td>
					<c:out value="${ park.yearFounded }"/>
				</td>
			</tr>
			<tr>
				<th>Annual Visitor Count:</th>
				<td>
					<c:out value="${ park.annualVisitorCount }"/>
				</td>
			</tr>
			<tr>
				<th>Number of Animal Species:</th>
				<td>
					<c:out value="${ park.numberOfAnimalSpecies }"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="description-quote">
		<div id="description">
			<h2>Description</h2>
			<p><c:out value="${ park.parkDescription }"/></p>
		</div>
		<div id="quote">
			<blockquote>"<c:out value="${ park.inspirationalQuote }"/>"</blockquote>
			<cite>- <c:out value="${ park.inspirationalQuoteSource }"/></cite>
		</div>
	</div>
</div>


<div id="weather">
	<div id="title-toggle">
		<h2>Weather</h2>
		
		<c:url value="/detail" var="detailPageURL" />
		<form action="${ detailPageURL }" method="GET" id="toggle">
			<input type="hidden" value="${ park.parkCode }" name="parkCode">
			<div id="toggle">
				<c:choose>
					<c:when test="${ tempUnit == null || tempUnit == 'F' }">
						<input onchange="this.form.submit();" type="radio" id="F" name="tempUnit" value="F" checked>
						<label for="F">&#8457;</label>
						<input onchange="this.form.submit();" type="radio" id="C" name="tempUnit" value="C" >
						<label for="C">&#8451;</label>
					</c:when>
					<c:otherwise>
						<input onchange="this.form.submit();" type="radio" id="F" name="tempUnit" value="F">
						<label for="F">&#8457;</label>
						<input onchange="this.form.submit();" type="radio" id="C" name="tempUnit" value="C" checked>
						<label for="C">&#8451;</label>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div>

	<c:choose>
		<c:when test="${ tempUnit == 'C' }">
			<c:set var="degreeUnit" value="&#8451;"/>
		</c:when>
		<c:otherwise>
			<c:set var="degreeUnit" value="&#8457;" />
		</c:otherwise>
	</c:choose>
	
	<div id="five-day-forecast">
		<div id="today">
			<img src="<c:url value="/img/weather/${ weatherForecast[0].forecast }.png" />"/>
			<div id="todays-info">
				<div id="advisory">
					<h4>Advisory Message(s):</h4>
					<ul>
						<c:forEach items="${ weatherForecast[0].advisoryMessages }" var="advisoryMessage">
							<li>
								<c:out value="${ advisoryMessage }" />
							</li>
						</c:forEach>
					</ul>
				</div>
				<table>
					<tr>
						<th>High:</th>
						<td>
							<p><c:out value="${ weatherForecast[0].high }" />${ degreeUnit }</p>
						</td>
					</tr>
					<tr>
						<th>Low:</th>
						<td>
							<p><c:out value="${ weatherForecast[0].low }" />${ degreeUnit }</p>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<c:forEach begin="1" end="4" var="i">
			<div id="week">
				<img src="<c:url value="/img/weather/${ weatherForecast[i].forecast }.png" />"/>
				<table>
					<tr>
						<th>High:</th>
						<td>
							<p><c:out value="${ weatherForecast[i].high }" />${ degreeUnit }</p>
						</td>
					</tr>
					<tr>
						<th>Low:</th>
						<td>
							<p><c:out value="${ weatherForecast[i].low }" />${ degreeUnit }</p>
						</td>
					</tr>
				</table>
			</div>
		</c:forEach>
	</div>
</div>	

<%@include file="common/footer.jsp"%>