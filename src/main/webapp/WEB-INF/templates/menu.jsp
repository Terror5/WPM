<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<ul>
		<li><a href="${pageContext.request.contextPath}/secured/home.html">Home</a></li>
	<sec:authorize access="hasRole('ROLE_ADMIN')">	
		<li><a href="${pageContext.request.contextPath}/admin/control.html">Admin Section</a></li>
	</sec:authorize>		
	<sec:authorize access="isAnonymous()"> 
		<li><a href="${pageContext.request.contextPath}/public/register.html">Register</a></li>
		<li><a href="${pageContext.request.contextPath}/public/forgot.html">Forgotten password?</a></li>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<li><a href="${pageContext.request.contextPath}/secured/project/list.html">Projects</a></li>		
		<li><a href="${pageContext.request.contextPath}/secured/report/list.html">Reports</a></li>
		<li><a href="${pageContext.request.contextPath}/secured/profile.html">Profile</a></li>		
	</sec:authorize> 
	</ul>
