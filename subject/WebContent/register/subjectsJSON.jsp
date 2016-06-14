<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
[
	<c:forEach var='list' items='${SUBLIST }' varStatus='status'>
	{num: ${list.num }, name: '${list.name }', flag: ${list.flag }}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
], 
[
	<c:forEach var='list' items='${MYLIST }' varStatus='status'>
	{num: ${list.num }, subjectName: '${list.subjectName }', room: '${list.room }', subDay: '${list.subDay}', subTime: '${list.subTime }', profNum: ${list.profNum }}<c:if test="${!status.last }">,</c:if>
	</c:forEach>
]
]