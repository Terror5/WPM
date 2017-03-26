<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>User List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/user/create.html">User create link</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>User-ID</td>
	<td>E-Mail</td>
	<td>Name</td>
	<td>Last Name</td>
	<td>Team</td>
	<td>Active</td>
	<td>Action</td>
</tr>
<c:forEach var="user" items="${UserList}">
	<tr>
		<td>${user.idUser}</td>
		<td>${user.eMail}</td>
		<td>${user.firstName}</td>
		<td>${user.lastName}</td>
		<td>${user.team.idTeam}</td>
		<td>${user.active}</td>		
		<td>
			<a href="${pageContext.request.contextPath}/admin/user/edit/${user.idUser}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/admin/user/delete/${user.idUser}.html">Delete</a><br/>
			<!-- <a href="${pageContext.request.contextPath}/admin/permission/${user.idUser}.html">Permission</a><br/>  -->
	
		</td>
	</tr>
</c:forEach>
</table>
</div>