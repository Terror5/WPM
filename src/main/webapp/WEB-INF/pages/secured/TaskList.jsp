 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Task List page</h1>
<i>${message}</i><br/>

<div class="tables" align="center" >
<table>
<tr>
	<td>Task ID</td>
	<td>Title</td>
	<td>Description</td>
	<td>Priority</td>
	<td>Resolved</td>
	<td>hours Planned</td>
	<td>hours Worked</td>
	<td>hours Remaining</td>
	<td>Assigned To</td>
	<td>Work Item</td>
	<td>Iteration</td>
	<td>Organize</td>
</tr>
<c:forEach var="task" items="${taskList}">
	<tr>
		<td>${task.idTask}</td>
		<td>${task.title}</td>
		<td>${task.description}</td>
		<td>${task.priority}</td>
		<td>${task.resolved}</td>
		<td>${task.hoursPlanned}</td>
		<td>${task.hoursWorked}</td>
		<td>${task.hoursRemaining}</td>
		<td>${task.user.eMail}</td>
		<td>${task.workitem.idWorkitem}</td>
		<td>${task.iteration.idIteration}</td>
		<td>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/edit/iteration=${task.iteration.idIteration}
						&workitem=${task.workitem.idWorkitem}&task=${task.idTask}&ref=1.html">Edit</a><br/>
			<a href="${pageContext.request.contextPath}/secured/iterationplan/delete/iteration=${task.iteration.idIteration}
						&workitem=${task.workitem.idWorkitem}&task=${task.idTask}&ref=1.html">Delete</a><br/>
		</td>
	</tr>
</c:forEach>
</table>
</div>
