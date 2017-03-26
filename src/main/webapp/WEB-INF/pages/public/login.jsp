<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<form action="${loginProcessingUrl}" method="post">
    <fieldset>
        <legend>Please Login</legend>
        <!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error -->
        <c:if test="${param.error != null}">
            <div style="color: red;">
                Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </c:if>
            </div>
        </c:if>
        <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
        <c:if test="${param.logout != null}">
            <div class="success">
                You have been logged out.
            </div>
        </c:if>
        <table cellspacing="5">
        <tr>
        	<td><label for="username">Username</label></td>
        	<td><input type="text" id="username" name="username" class="inputf"/></td>
        </tr>
        <tr> 
        	<td><label for="password">Password</label></td>
        	<td><input type="password" id="password" name="password" class="inputf"/></td>
        </tr>
        </table>
        <div>
            <button type="submit" class="btn">Log in</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </fieldset>
 </form>

