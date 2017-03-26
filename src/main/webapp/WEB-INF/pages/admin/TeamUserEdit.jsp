<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px">Team-ID</th><th width="25px">Title</th><th width="25px">Description</th>
</tr>
</thead>
<tbody>
	<tr>
		<td>${team.idTeam}</td>
		<td>${team.title}</td>
		<td>${team.description}</td>
	</tr>
	<form:form method="POST" commandName="teamUsers" action="${pageContext.request.contextPath}/admin/team/editTeamUser/${team.idTeam}.html">
		<tr>
			<td colspan=3>User</td>
		</tr>
		<tr>
			<td colspan=3>
				<ul>
					<form:select path="userMap"  multiple="true"> 
                		<form:options items="${teamUsers.userMap}" />  
            		</form:select>  
				</ul>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<input type="submit" value="Editieren" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</td>
		</tr>		
	</form:form>	
</tbody>
</table>
