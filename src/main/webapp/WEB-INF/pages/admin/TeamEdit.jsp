<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Edit Team page</h1>
<form:form method="POST" commandName="team" action="${pageContext.request.contextPath}/admin/team/edit/${team.idTeam}.html" >
<table cellspacing="5">
	<tr>
		<td>Title</td>
		<td><form:input path="title" class="inputf"/></td>
		<td><form:errors path="title" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:textarea path="description" class="inputf"/></td>
		<td><form:errors path="description" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Edit" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form>
