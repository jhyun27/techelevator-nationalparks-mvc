<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file="common/header.jsp"%>

<c:url value="/planTrip" var="getTripResultsURL"/>
<span id="top"/>

<div>
<div id="plan-trip">
	<div id="trip-planner">
		<form:form action="${ getTripResultsURL }" method="GET" modelAttribute="search">
	 		<h3>Plan Your Trip</h3>
	 		
	 		<div>
	 			<form:label path="state"><h4>State</h4></form:label>
				<form:select path="state" cssClass="survey-input">
					<form:option value="all">All states</form:option>
					<form:options items="${ states }"/>
				</form:select>
	 		</div>
			
			<div>
				<label><h4>Elevation (feet)</h4></label>
				<form:errors path="elevationString"/>
				<form:label path="minElevationFeetString">Min</form:label>	
				<form:input path="minElevationFeetString" cssClass="survey-input" />
				<form:label path="maxElevationFeetString">Max</form:label>	
				<form:input path="maxElevationFeetString" cssClass="survey-input"/>
			</div>
			
			<div>
				<label><h4>Miles of Trails</h4></label>
				<form:select path="minMilesOfTrail" cssClass="survey-input">
					<form:option value="0">All Trails</form:option>
					<form:option value="100">100+</form:option>
					<form:option value="500">500+</form:option>
					<form:option value="1000">1000+</form:option>
				</form:select>
			</div>
			
			<div>
				<label><h4>Need Campsites</h4></label>
				<div class="flex-row">
					<form:checkbox path="needsCampsites" cssClass="checkbox"/><span> Yes!</span>
				</div>
			</div>
			
			<div>
				<label><h4>Climate</h4></label>
				<form:select path="climate" cssClass="survey-input">
					<form:option value="all">All climates</form:option>
					<form:options items="${ climates }"/>
				</form:select>
			</div>
			
			<div>
				<input class="submit-button" type="submit" value="Submit"/>
			</div>
			
			
			
			
		</form:form>
	</div>
	
	<div id="trip-results">
		<c:forEach items="${ searchResults }" var="park">
			<c:url value="/detail" var="parkDetailURL">
				<c:param value="${ park.parkCode }" name="parkCode"/>
			</c:url>
			<c:url value="/img/parks/${ fn:toLowerCase(park.parkCode) }.jpg" var="imgURL"/>
			
			<div class="trip-result">
				<a href="${ parkDetailURL }">
					<img class="trip-result-image" src="${ imgURL }" alt="${ park.parkName }" />
				</a>
				<div class="result-park-info">
					<h2><a href="${ parkDetailURL }">
						<c:out value="${ park.parkName }"/>
					</a></h2>
					
					<table>
						<tr>
							<th class="align-left">
								State
							</th>
							<td>
								<c:out value="${ park.state }"/>
							</td>
						</tr>
						
						<tr>
							<th class="align-left">
								Elevation (feet)
							</th>
							<td>
								<c:out value="${ park.elevationFt }"/>
							</td>
						</tr>
						
						<tr>
							<th class="align-left">
								Miles of Trails
							</th>
							<td>
								<c:out value="${ park.milesOfTrail }"/>
							</td>
						</tr>
						
						<tr>
							<th class="align-left">
								Number of Campsites
							</th>
							<td>
								<c:out value="${ park.numberOfCampsites }"/>
							</td>
						</tr>
						
						<tr>
							<th class="align-left">
								Climate
							</th>
							<td>
								<c:out value="${ park.climate }"/>
							</td>
						</tr>
					
					</table>
				</div>
			</div>
			<hr class="line-gray-center">
		</c:forEach>
		<c:if test="${ searchResults.size() == 0 }">
			<div class="no-results"><h2>No results found.</h2></div>
		</c:if>
	</div>
</div>
<a class="back-to-top" href="#top">Back to top.</a>
</div>
	

<%@include file="common/footer.jsp"%>