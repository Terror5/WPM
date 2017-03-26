<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<h1>New workitem page</h1>
<form:form method="POST" commandName="workitem" action="${pageContext.request.contextPath}/secured/workitem/create/project=${idProject}
				&iteration=${idIteration}&ref=${ref}.html" >
<table cellspacing="5">
	<tr>
		<td>Title</td>
		<td><form:input path="title" class="inputf"/></td>
		<td><form:errors path="title" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:input path="description" class="inputf"/></td>
		<td><form:errors path="description" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>Priority</td>
		<td><form:input path="priority" class="inputf"/></td>
		<td><form:errors path="priority" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>Size Estimate</td>
		<td><form:input path="sizeEstimate" class="inputf"/></td>
		<td><form:errors path="sizeEstimate" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>Type</td>
		<td>
			<form:select path="type" >
				<form:option value="0" label="High-Level" /> 
				<form:option value="1" label="Use Case" />
				<form:option value="2" label="Systemwide Requirement" />
				<form:option value="3" label="Test Case" />           
        	</form:select>  
		</td>
		<td><form:errors path="type" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td><input type="submit" value="Create" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
<form:hidden path="project"  />
</form:form>
