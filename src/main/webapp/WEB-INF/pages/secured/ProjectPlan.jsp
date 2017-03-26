 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Project Plan</h1>
<i>${message}</i><br/>
<p></p>

<c:forEach var="iteration" items="${iterationList}">
<div class="tables" align="center" >
<table>
	<tr>
		<td>Iteration ID</td>
		<td>Start of Iteration</td>
		<td>End of Iteration</td>
		<td></td>
		<td>Project</td>
		<td>Action</td>
	</tr>
	<tr>
		<td>${iteration.idIteration}</td>
		<td><fmt:formatDate value="${iteration.startIteration}" type="date" pattern="dd.MM.yyyy" /></td>
		<td><fmt:formatDate value="${iteration.endIteration}" type="date" pattern="dd.MM.yyyy" /></td>
		<td></td>
		<td>${iteration.project.idProject}</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/iteration/edit/project=${idProject}&iteration=${iteration.idIteration}.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iteration/delete/project=${idProject}&iteration=${iteration.idIteration}.html">Delete</a><br/>
			<a href="${pageContext.request.contextPath}/secured/workitem/list/project=${idProject}&iteration=${iteration.idIteration}&ref=3.html">Add Workitem</a><br/>
		</td>
	</tr>
	</table>
	</div>
	
	<div class="tables" align="center" >
	<table>
	<tr>
		<td>Workitem ID</td>
		<td>Type</td>
		<td>Title</td>
		<td>Priority</td>
		<td>Size Estimate</td>
		<td>Action</td>
	</tr>
	<c:forEach var="workitem" items="${iteration.workitems}">
	
		<c:if test="${workitem.type == 0}">
		<tr>
			<td>${workitem.idWorkitem}</td>
			<td>
				<c:choose>
					<c:when test="${workitem.type == 0}">High-Level</c:when>
					<c:when test="${workitem.type == 1}">Use Case</c:when>
					<c:when test="${workitem.type == 2}">Systemwide Requirement</c:when>
					<c:when test="${workitem.type == 3}">Test Case</c:when>	
					<c:otherwise>${workitem.type}</c:otherwise>
				</c:choose>
			</td>		
			<td>${workitem.title}</td>
			<td>${workitem.priority}</td>
			<td>${workitem.sizeEstimate}</td>
			<td>
				<a href="${pageContext.request.contextPath}/secured/iterationplan/updateWorkitem/project=${idProject}&iteration=${iteration.idIteration}&workitem=${workitem.idWorkitem}&remove=1&ref=2.html">Remove Workitem</a><br/>
				<a href="${pageContext.request.contextPath}/secured/workitem/edit/project=${idProject}&workitem=${workitem.idWorkitem}&iteration=${iteration.idIteration}&ref=2.html">Edit</a><br/>
				<a href="${pageContext.request.contextPath}/secured/workitem/delete/project=${idProject}&workitem=${workitem.idWorkitem}&iteration=${iteration.idIteration}&ref=2.html">Delete</a><br/>
			</td>	
		</tr>
		</c:if>
	</c:forEach>
	</table>
	</div>
</c:forEach>

