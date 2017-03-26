<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>Edit Iteration</h1>
<form:form method="POST" commandName="iteration" action="${pageContext.request.contextPath}/secured/iteration/edit/project=${idProject}&iteration=${iteration.idIteration}.html" >
<table cellspacing="5">
	<tr>
		<td>Start of Iteration</td>
		<td><form:input path="startIteration" id="penis" class="inputf"/></td>
		<td><form:errors path="startIteration" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>End of Iteration</td>
		<td><form:input path="endIteration" id="penis2" class="inputf"/></td>
		<td><form:errors path="endIteration" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>Evaluation</td>
		<td><form:textarea path="evaluation" id="description" /></td>
		<td><form:errors path="evaluation" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Save" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>	
</table>
<form:hidden path="project"  />
</form:form>
