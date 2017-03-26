 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Iteration List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/secured/iteration/create/${idProject}">New Iteration</a></p>

<div class="tables" align="center" >
<table>
<tr>
	<td>Iteration ID</td>
	<td>Start of Iteration</td>
	<td>End of Iteration</td>
	<td>Project</td>
	<td>Evaluation</td>
	<td>Organize</td>
	<td>Lists</td>
</tr>
<c:forEach var="iteration" items="${iterationList}">
	<tr>
		<td>${iteration.idIteration}</td>
		<td><fmt:formatDate value="${iteration.startIteration}" type="date" pattern="dd.MM.yyyy" /></td>
		<td><fmt:formatDate value="${iteration.endIteration}" type="date" pattern="dd.MM.yyyy" /></td>
		<td>${iteration.project.idProject}</td>
		<td>${iteration.evaluation}</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/iteration/edit/project=${idProject}&iteration=${iteration.idIteration}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iteration/delete/project=${idProject}&iteration=${iteration.idIteration}.html">Delete</a><br/>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/list/project=${idProject}&iteration=${iteration.idIteration}.html">Iteration Plan</a><br/>
		</td>
	</tr>
</c:forEach>
</table>
</div>

