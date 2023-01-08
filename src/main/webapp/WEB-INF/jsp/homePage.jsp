<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="common/header.jsp"%>
<span id="home-top"></span>
<div id="home-page">
	<c:forEach items="${ parks }" var="park">
		<c:url value="/detail" var="parkDetailURL">
			<c:param value="${ park.parkCode }" name="parkCode"/>
		</c:url>
		<c:url value="/img/parks/${ fn:toLowerCase(park.parkCode) }.jpg" var="imgURL"/>
		<div class="park-info">
			<h2><a href="${ parkDetailURL }"><c:out value="${ park.parkName }"/></a></h2>
			<div class="park">
				<div id="park-picture">
						<a href="${ parkDetailURL }">
							<img src="${ imgURL }" alt="${ park.parkName }" />
						</a>
				</div>
				<div id="park-details">
					<table>
						<tr>
							<th>State:</th>
							<td><c:out value="${ park.state }" /></td>
						</tr>
					</table>
					<p><c:out value="${ park.parkDescription }" /></p>
				</div>
			</div>
		</div>
		
			<hr id="line" class="line-gray-center">
	</c:forEach>
	
	<a class="back-to-top" href="#home-top">Back to top.</a>
</div>
<%@include file="common/footer.jsp"%>