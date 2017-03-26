 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>Workitem List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/secured/workitem/create/project=${idProject}&iteration=${idIteration}&ref=${ref}">New workitem link</a></p>
<c:if test="${ref == 1}">
	<p><a href="${pageContext.request.contextPath}/secured/iterationplan/list/project=${idProject}&iteration=${idIteration}.html">Back to Iterationplan</a></p>
</c:if>
<c:if test="${ref == 3}">
	<p><a href="${pageContext.request.contextPath}/secured/iterationplan/projectplan/${idProject}.html">Back to Projectplan</a></p>
</c:if>
<div class="tables" align="center" >
<table>
<tr>
	<td>Workitem ID</td>
	<td>Title</td>
	<td>Description</td>
	<td>Priority</td>
	<td>Size Estimate</td>
	<td>Type</td>
	<td>Iteration</td>
	<td>Organize</td>
</tr>
<c:forEach var="workitem" items="${workitemList}">
	<tr>
		<td>${workitem.idWorkitem}</td>
		<td>${workitem.title}</td>
		<td>${workitem.description}</td>
		<td>${workitem.priority}</td>
		<td>${workitem.sizeEstimate}</td>
		<td>
			<c:choose>
				<c:when test="${workitem.type == 0}">High-Level</c:when>
				<c:when test="${workitem.type == 1}">Use Case</c:when>
				<c:when test="${workitem.type == 2}">Systemwide Requirement</c:when>
				<c:when test="${workitem.type == 3}">Test Case</c:when>	
				<c:otherwise>${workitem.type}</c:otherwise>
			</c:choose>
		</td>	
		<td>${workitem.iteration.idIteration}</td>
		<td>
			<c:if test="${ref == 1 || ref == 3}">
				<a href="${pageContext.request.contextPath}/secured/iterationplan/updateWorkitem/project=${idProject}&iteration=${idIteration}
					&workitem=${workitem.idWorkitem}&remove=0&ref=${ref}.html">Add Workitem</a><br/>
			</c:if>
			<a href="${pageContext.request.contextPath}/secured/workitem/edit/project=${idProject}&workitem=${workitem.idWorkitem}
							&iteration=${idIteration}&ref=${ref}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/workitem/delete/project=${idProject}&workitem=${workitem.idWorkitem}
							&iteration=${idIteration}&ref=${ref}.html">Delete</a><br/>						
		</td>
	</tr>
</c:forEach>
</table>
</div>