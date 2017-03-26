<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>New Project page</h1>
<form:form method="POST" commandName="project" action="${pageContext.request.contextPath}/admin/project/create.html" >
<table cellspacing="5">
	<tr>
		<td>Title</td>
		<td><form:input path="title" class="inputf" /></td>
		<td><form:errors path="title" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:input path="description" class="inputf"/></td>
		<td><form:errors path="description" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>Start Date</td>
		<td><form:input path="dateBegin" id="penis" class="inputf"/></td>
		<td><form:errors path="dateBegin" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>End Date</td>
		<td><form:input path="dateEnd" id="penis2" class="inputf"/></td>
		<td><form:errors path="dateEnd" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>Iteration Length (Days)</td>
		<td><form:input path="iterationCycle" class="inputf"/></td>
		<td><form:errors path="iterationCycle" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Team</td>
		<td>
			<form:select path="team" >
				<form:option value="-" label="-- Select team --" /> 
                <form:options items="${teamList}" itemValue="idTeam" itemLabel="title"/>             
            </form:select>  
		</td>
		<td><form:errors path="team" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Create" class="btn" /></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form>
