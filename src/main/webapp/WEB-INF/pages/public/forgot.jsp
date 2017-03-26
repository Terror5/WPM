<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1>Forgotten Password Page</h1>
<form:form method="POST" commandName="forgot" action="${pageContext.request.contextPath}/public/forgot.html" >
<table cellspacing="5">
	<tr>
		<td>Your login</td>
		<td><form:input path="idUser" class="inputf"/></td>
		<td><form:errors path="idUser" cssStyle="color: red;"/></td>
	</tr>

	<tr>
		<td><input type="submit" value="Send" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
	</tr>
</table>
</form:form>
