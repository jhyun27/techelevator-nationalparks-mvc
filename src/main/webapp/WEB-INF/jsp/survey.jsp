<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@include file="common/header.jsp"%>

<c:url value="/img/parks/${ fn:toLowerCase(park.parkCode) }.jpg"
	var="imgURL" />
<img src="${ imgURL }" alt="${ park.parkName }" />

<div class="test">
	<c:url value="/survey" var="postSurveyURL" />
	<form:form action="${ postSurveyURL }" method="POST" modelAttribute="survey">
		<table>
			<tr>
				<th>
					<form:label path="parkCode">Favorite National Park</form:label>
				</th>

				<td>
					<form:select path="parkCode" cssClass="survey-input">
						<form:options items="${ parks }" itemValue="parkCode"
							itemLabel="parkName" />
					</form:select>
				</td>
			</tr>

			<tr>
				<th><form:label path="emailAddress">Your Email </form:label></th>
				<td>
					<form:input path="emailAddress"  cssClass="survey-input"/> 
					<form:errors path="emailAddress" cssClass="error" />
				</td>
			</tr>

			<tr>
				<th><form:label path="state">State of residence</form:label></th>
				<td>
					<form:select path="state" cssClass="survey-input">
						<form:option value="AL">Alabama</form:option>
						<form:option value="AK">Alaska</form:option>
						<form:option value="AZ">Arizona</form:option>
						<form:option value="AR">Arkansas</form:option>
						<form:option value="CA">California</form:option>
						<form:option value="CO">Colorado</form:option>
						<form:option value="CT">Connecticut</form:option>
						<form:option value="DE">Delaware</form:option>
						<form:option value="DC">District Of Columbia</form:option>
						<form:option value="FL">Florida</form:option>
						<form:option value="GA">Georgia</form:option>
						<form:option value="HI">Hawaii</form:option>
						<form:option value="ID">Idaho</form:option>
						<form:option value="IL">Illinois</form:option>
						<form:option value="IN">Indiana</form:option>
						<form:option value="IA">Iowa</form:option>
						<form:option value="KS">Kansas</form:option>
						<form:option value="KY">Kentucky</form:option>
						<form:option value="LA">Louisiana</form:option>
						<form:option value="ME">Maine</form:option>
						<form:option value="MD">Maryland</form:option>
						<form:option value="MA">Massachusetts</form:option>
						<form:option value="MI">Michigan</form:option>
						<form:option value="MN">Minnesota</form:option>
						<form:option value="MS">Mississippi</form:option>
						<form:option value="MO">Missouri</form:option>
						<form:option value="MT">Montana</form:option>
						<form:option value="NE">Nebraska</form:option>
						<form:option value="NV">Nevada</form:option>
						<form:option value="NH">New Hampshire</form:option>
						<form:option value="NJ">New Jersey</form:option>
						<form:option value="NM">New Mexico</form:option>
						<form:option value="NY">New York</form:option>
						<form:option value="NC">North Carolina</form:option>
						<form:option value="ND">North Dakota</form:option>
						<form:option value="OH">Ohio</form:option>
						<form:option value="OK">Oklahoma</form:option>
						<form:option value="OR">Oregon</form:option>
						<form:option value="PA">Pennsylvania</form:option>
						<form:option value="RI">Rhode Island</form:option>
						<form:option value="SC">South Carolina</form:option>
						<form:option value="SD">South Dakota</form:option>
						<form:option value="TN">Tennessee</form:option>
						<form:option value="TX">Texas</form:option>
						<form:option value="UT">Utah</form:option>
						<form:option value="VT">Vermont</form:option>
						<form:option value="VA">Virginia</form:option>
						<form:option value="WA">Washington</form:option>
						<form:option value="WV">West Virginia</form:option>
						<form:option value="WI">Wisconsin</form:option>
						<form:option value="WY">Wyoming</form:option>
					</form:select>
				</td>
			</tr>

			<tr>
				<th>
					<form:label path="activityLevel">Activity Level</form:label>
				</th>
				<td>
					<form:select path="activityLevel" cssClass="survey-input">
						<form:option value="inactive" label="Inactive" />
						<form:option value="sedentary" label="Sedentary" />
						<form:option value="active" label="Active" />
						<form:option value="extremelyActive" label="Extremely Active" />
					</form:select>
				</td>
			</tr>
			
			<tr><td colspan="2"><button class="submit-button" type="submit">Submit</button></td></tr>
		</table>





		
	</form:form>
</div>



<%@include file="common/footer.jsp"%>