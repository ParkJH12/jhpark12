<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
	<c:forEach var="list" items="${LIST }" varStatus="status">
	{num: ${list.num }, name: '${list.name }', content: '${list.content }'}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
]