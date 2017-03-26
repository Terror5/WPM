<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>New User page</h1>
<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/admin/user/create.html" >
<table cellspacing="5">
	<tr>
		<td>Windows Login</td>
		<td><form:input path="idUser" class="inputf"/></td>
		<td><form:errors path="idUser" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>E-Mail</td>
		<td><form:input path="eMail" class="inputf" /></td>
		<td><form:errors path="eMail" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><form:input path="pwHash" class="inputf"/></td>
		<td><form:errors path="pwHash" cssStyle="color: red;" /></td>
	</tr>
		<tr>
		<td>First Name</td>
		<td><form:input path="firstName" class="inputf"/></td>
		<td><form:errors path="firstName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Last name</td>
		<td><form:input path="lastName" class="inputf"/></td>
		<td><form:errors path="LastName" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Active</td>
		<td><form:checkbox path="active" /></td>
		<td><form:errors path="active" cssStyle="color: red;"/></td>
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
		<td><input type="submit" value="Create" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>
</table>
</form:form>
