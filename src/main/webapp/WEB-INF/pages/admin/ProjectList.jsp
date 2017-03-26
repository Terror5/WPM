 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Project List page</h1>
<i>${message}</i><br/>
<p><a href="${pageContext.request.contextPath}/admin/project/create.html">New Project</a></p>
<div class="tables" align="center" >
<table>
<tr>
	<td>Project-ID</td>
	<td>Title</td>
	<td>Description</td>
	<td>Start Date</td>
	<td>End Date</td>
	<td>Iteration Length (Days)</td>
	<td>Team ID</td>
	<td>Organize</td>
	<td>Lists</td>
</tr>
<c:forEach var="project" items="${ProjectList}">
	<tr>
		<td>${project.idProject}</td>
		<td>${project.title}</td>
		<td>${project.description}</td>
		<td><fmt:formatDate value="${project.dateBegin}" type="date" pattern="dd.MM.yyyy" /></td>
		<td><fmt:formatDate value="${project.dateEnd}" type="date" pattern="dd.MM.yyyy" /></td>
		<td>${project.iterationCycle}</td>
		<td>${project.team.idTeam}</td>
	
		<c:choose>
    		<c:when test="${test}">
       			<td><a href="${pageContext.request.contextPath}/admin/permission/add/project=${project.idProject}&user=${idUser}.html">Add Role</a><br/></td>
       			<td></td>				
   			</c:when>
    		<c:otherwise>
    			<td>
					<a href="${pageContext.request.contextPath}/admin/project/edit/${project.idProject}.html">Edit</a><br/>
					<a href="${pageContext.request.contextPath}/admin/project/delete/${project.idProject}.html">Delete</a><br/>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/secured/workitem/list/project=${project.idProject}.html">Workitemlist</a><br/>
					<a href="${pageContext.request.contextPath}/secured/iteration/list/${project.idProject}.html">Iterationlist</a><br/>
					<a href="${pageContext.request.contextPath}/secured/iterationplan/projectplan/${project.idProject}.html">Projectplan</a><br/>
				</td> 
    		</c:otherwise>
		</c:choose>			
	</tr>
	<c:if test="${test}">			
		<c:forEach var="projectRole" items="${project.userProjectRoles}">
			<tr>
				<td>${projectRole.openuprole.idOpenUpRole}</td>
				<td>${projectRole.openuprole.title}</td>
				<td>${projectRole.user.idUser}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="${pageContext.request.contextPath}/admin/permission/delete/openuprole=${projectRole.openuprole.idOpenUpRole}
						 &project=${project.idProject}&user=${projectRole.user.idUser}.html">Delete Role</a><br/></td>	
			</tr>
		</c:forEach>
	</c:if>
</c:forEach>
</tbody>
</table>
</div>
