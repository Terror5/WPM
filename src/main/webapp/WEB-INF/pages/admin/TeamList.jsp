<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Team List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/team/create.html">New Team</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>Team ID</td>
	<td>Title</td>
	<td>Description</td>
	<td>Organize</td>
</tr>


<c:forEach var="team" items="${TeamList}">
	<tr>
		<td>${team.idTeam}</td>
		<td>${team.title}</td>
		<td>${team.description}</td>	
		<td>
			<a href="${pageContext.request.contextPath}/admin/team/edit/${team.idTeam}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/admin/team/delete/${team.idTeam}.html">Delete</a><br/>
			<a href="${pageContext.request.contextPath}/admin/team/editTeamUser/${team.idTeam}.html">Add User</a><br/>
		</td>
	</tr>
</c:forEach>
</table>
</div>
