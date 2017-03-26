<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>New Team page</h1>
<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/admin/team/create.html" >
<table cellspacing="5">
	<tr>
		<td>Title</td>
		<td><form:input path="title" class="inputf"/></td>
		<td><form:errors path="title" cssStyle="color: red;"/></td>
	</tr>
		<tr>
		<td>Description</td>
		<td><form:input path="description" class="inputf"/></td>
		<td><form:errors path="description" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Create" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form> 
