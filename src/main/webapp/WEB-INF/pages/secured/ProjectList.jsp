 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Project List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/secured/project/create.html">New Project link</a></p>

<div class="tables" align="center" >
<table>
<tr>
	<td>Project-ID</td>
	<td>Team</td>
	<td>Title</td>
	<td>Description</td>
	<td>Start Date</td>
	<td>End Date</td>
	<td>Iteration Length (Days)</td>
	<td>Organize</td>
	<td>Lists</td>
</tr>
<c:forEach var="project" items="${ProjectList}">
	<tr>
		<td>${project.idProject}</td>
		<td>${project.team.title}</td>
		<td>${project.title}</td>
		<td>${project.description}</td>
		<td><fmt:formatDate value="${project.dateBegin}" type="date" pattern="dd.MM.yyyy" /></td>
		<td><fmt:formatDate value="${project.dateEnd}" type="date" pattern="dd.MM.yyyy" /></td>
		<td>${project.iterationCycle}</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/project/edit/${project.idProject}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/project/delete/${project.idProject}.html">Delete</a><br/>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/workitem/list/project=${project.idProject}.html">Workitemlist</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iteration/list/${project.idProject}.html">Iterationlist</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/projectplan/${project.idProject}.html">Projectplan</a><br/>  
		</td>
	</tr>		
</c:forEach>
</table>
</div>