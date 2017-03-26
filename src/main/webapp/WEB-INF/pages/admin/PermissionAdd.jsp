<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Add UserProjectRole</h1>
<div class="tables" align="center" >
<table>
<tr>
	<td>ID</td>
	<td>Description</td>
	<td>Actions</td>
</tr>
	<c:forEach var="openuprole" items="${openuproleList}">
		<tr>
			<td>${openuprole.idOpenUpRole}</td>
			<td>${openuprole.title}</td>
			<td><a href="${pageContext.request.contextPath}/admin/permission/addRole/openuprole=${openuprole.idOpenUpRole}
						 &project=${idProject}&user=${idUser}.html">Add Permission</a></td>
		</tr>
	</c:forEach>
</table>
</div>