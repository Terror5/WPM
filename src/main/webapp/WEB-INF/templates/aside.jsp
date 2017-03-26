<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sec:authorize access="hasRole('ROLE_USER')">
		Current user: <sec:authentication property="principal.username"/>
		<br>
		<form action="<c:url value="/logout"/>" method="post">
			<input type="submit" class="btn" value="Log out" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
		</form>
</sec:authorize>

<br>
<br>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<h2>Admin Section</h2>
	<a href="${pageContext.request.contextPath}/admin/user/list.html">User Area</a><br/>
	<a href="${pageContext.request.contextPath}/admin/team/list.html">Team Area</a><br/>
	<a href="${pageContext.request.contextPath}/admin/project/list.html">Project Area</a><br/>		
</sec:authorize>
	<br>
	<br>
<sec:authorize access="hasRole('ROLE_USER')">
	<h2>Individual Queries</h2>
	<a href="${pageContext.request.contextPath}/secured/iterationplan/myIterationPlan.html">Current Iteration Plan</a><br/>
	<a href="${pageContext.request.contextPath}/secured/iterationplan/myTaskList.html">My Tasks</a><br/>
</sec:authorize>

