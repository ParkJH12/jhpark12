<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

[
	<c:set var="cnt" value="0"/>
	<c:forEach var="list" items="${LIST}">
	{num: ${list.num }, name: '${list.name }', tel: '${list.tel }', email: '${list.email }', dept: '${list.dept }', type: ${list.type }}<c:if test="${fn:length(LIST) ne cnt }">,</c:if>
	<c:set var="cnt" value="${cnt+1 }"/>
	</c:forEach>
]
