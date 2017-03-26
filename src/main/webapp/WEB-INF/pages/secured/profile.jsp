<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<h1>Profile Page</h1>
<i>${message}</i>

<br/><a href="${pageContext.request.contextPath}/secured/profile/password.html">Change password</a>
<br/>
<br/>

<table>
	<tr>
		<td>Current preferred:</td>
		<td>${pTitle}</td>
	<tr>
<form:form method="POST" commandName="projectForm" action="${pageContext.request.contextPath}/secured/profile/project.html" >	
	<tr>
		<td>Preferred Project</td>
		<td>
			<form:select path="project" >
				<form:option value="-" label="-- Select Project --" /> 
                <form:options items="${projectList}" itemValue="idProject" itemLabel="title"/>             
            </form:select>  
		</td>
		<td><form:errors path="project" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Save" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td></td>
	</tr>	
</form:form>
</table>
<br/>
<br/>

<h2>Your system roles</h2>
<div class="tables" align="center">
<table>
<tr>
	<td>Role</td>
	<td>Title</td>
</tr>
<c:forEach var="role" items="${roleList}">
	<tr>		
		<td>${role.idRole}</td>
		<td>${role.title}</td>
	</tr>
</c:forEach>
</table>
</div>
<br/>
<br/>

<h2>Your project roles</h2>
<div class="tables" align="center">
<table>
<tr>
	<td>Project ID</td>
	<td>Title</td>
	<td>Description</td>
	<td>Date Begin</td>
	<td>Date End</td>
	<td>Iterationcyle</td>
	<td>Team ID</td>
</tr>
<c:forEach var="project" items="${projectList}">
	<tr>
		<td>${project.idProject}</td>
		<td>${project.title}</td>
		<td>${project.description}</td>
		<td><fmt:formatDate value="${project.dateBegin}" type="date" pattern="dd.MM.yyyy" /></td>
		<td><fmt:formatDate value="${project.dateEnd}" type="date" pattern="dd.MM.yyyy" /></td>
		<td>${project.iterationCycle}</td>
		<td>${project.team.idTeam}</td>
	</tr>
	<c:forEach var="userProjectRole" items="${project.userProjectRoles}">
		<c:if test="${userProjectRole.user.idUser == test}">
			<tr>
				<td>ROLES:</td>
				<td>${userProjectRole.openuprole.idOpenUpRole}</td>
				<td>${userProjectRole.openuprole.title}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:if>	
	</c:forEach>
</c:forEach>
</table>
</div>