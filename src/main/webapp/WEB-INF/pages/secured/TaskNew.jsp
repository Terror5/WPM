<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<h1>New Task page</h1>
<form:form method="POST" commandName="task" action="${pageContext.request.contextPath}/secured/iterationplan/
								create/iteration=${idIteration}&workitem=${idWorkitem}&ref=${ref}.html" >
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
		<td>Hours Planned</td>
		<td><form:input path="hoursPlanned" class="inputf"/></td>
		<td><form:errors path="hoursPlanned" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td>Assigned to:</td>
		<td>
			<form:select path="user" >
				<form:option value="-1" label="All Teammembers" /> 
                <form:options items="${userlist}" itemValue="idUser" itemLabel="fullName"/>             
            </form:select>  		
		</td>
		<td><form:errors path="user" cssStyle="color: red;"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Create" class="btn"/></td>
		<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		<td>
			<form:hidden path="workitem" />
			<form:hidden path="iteration" />
		</td>
	</tr>
</table>
</form:form>
