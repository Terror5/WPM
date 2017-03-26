<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<h1>Edit task page</h1>
<form:form method="POST" commandName="task" action="${pageContext.request.contextPath}/secured/iterationplan/
					edit/iteration=${idIteration}&workitem=${idWorkitem}&task=${task.idTask}&ref=${ref}.html" >
<table cellspacing="5">
	<tr>
		<td>Title</td>
		<td><form:input path="title" class="inputf"/></td>
		<td><form:errors path="title" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Description</td>
		<td><form:textarea path="description" /></td>
		<td><form:errors path="description" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Priority</td>
		<td><form:input path="priority" class="inputf"/></td>
		<td><form:errors path="priority" cssStyle="color: red;" /></td>
	</tr>
	<tr>
		<td>Resolved</td>
		<td><form:checkbox path="resolved" /></td>
		<td><form:errors path="resolved" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Hours Planned</td>
		<td><form:input path="hoursPlanned" class="inputf"/></td>
		<td><form:errors path="hoursPlanned" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Hours Worked</td>
		<td><form:input path="hoursWorked" class="inputf"/></td>
		<td><form:errors path="hoursWorked" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Hours Remaining</td>
		<td><form:input path="hoursRemaining" class="inputf"/></td>
		<td><form:errors path="hoursRemaining" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Edit" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		
		<td><form:hidden path="workitem" /></td>
	</tr>
</table>
</form:form>
