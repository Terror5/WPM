<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="POST" commandName="password" action="${pageContext.request.contextPath}/secured/profile/password.html" >
<table cellspacing="5">
	<tr>
		<td>Old Password:</td>
		<td><form:password path="oldPw" class="inputf"/></td>
		<td><form:errors path="oldPw" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td>New Password:</td>
		<td><form:password path="pwString1" class="inputf"/></td>
		<td><form:errors path="pwString1" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>Re-enter new Password:</td>
		<td><form:password path="pwString2" class="inputf"/></td>
		<td><form:errors path="pwString2" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Save" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td><form:hidden path="user.idUser"/></td>
	</tr>	
</table>
</form:form>