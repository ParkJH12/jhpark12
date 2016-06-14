<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
[
	<c:forEach var='list' items='${OPENLIST }' varStatus='status'>
	{num: ${list.num }, name: '${list.name }', days: '${list.days }', times: '${list.times }', profName: '${list.profName }'}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
], 
[
	<c:forEach var='list' items='${MYLIST }' varStatus='status'>
	{num: ${list.num }, name: '${list.name }', days: '${list.days }', times: '${list.times }', profName: '${list.profName }'}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
]
]