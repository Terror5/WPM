 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Iteration Plan</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/secured/workitem/list/project=${idProject}&iteration=${idIteration}
				&ref=1.html">Add Workitem</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>ID</td>
	<td>Type</td>
	<td>Title</td>
	<td>Priority</td>
	<td>Size Estimate</td>
	<td>Hours Planned</td>
	<td>Hours Remaining</td>
	<td>Assigned to</td>
	<td>Action</td>
</tr>
<c:forEach var="workitem" items="${workitemList}">
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
		<td></td>
		<td></td>
		<td></td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/create/iteration=${idIteration}&workitem=${workitem.idWorkitem}
							&ref=0.html">Add Task</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/updateWorkitem/project=${idProject}&iteration=${idIteration}
							&workitem=${workitem.idWorkitem}&remove=1&ref=0.html">Remove Workitem</a><br/>
			<a href="${pageContext.request.contextPath}/secured/workitem/edit/project=${idProject}&workitem=${workitem.idWorkitem}
							iteration=${idIteration}&ref=0.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/workitem//project=${idProject}&workitem=${workitem.idWorkitem}
							iteration=${idIteration}&ref=0.html">Delete</a><br/>
		</td>
	</tr>
	<c:forEach var="task" items="${workitem.tasks}">
		<tr>
			<td>${task.idTask}</td>
			<td>Task</td>		
			<td>${task.title}</td>
			<td>${task.priority}</td>
			<td></td>
			<td>${task.hoursPlanned}</td>
			<td>${task.hoursRemaining}</td>
			<td>${task.user.idUser}</td>
			<td>
				<a href="${pageContext.request.contextPath}/secured/iterationplan/edit/iteration=${idIteration}&workitem=${workitem.idWorkitem}
								&task=${task.idTask}&ref=0.html">Edit</a><br/>
				<a href="${pageContext.request.contextPath}/secured/iterationplan/delete/iteration=${idIteration}&workitem=${workitem.idWorkitem}
								&task=${task.idTask}&ref=0.html">Delete</a><br/>
			</td>
		</tr>
	</c:forEach>
</c:forEach>
</table>
</div>
