<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
[
	<c:set var="cnt" value="0"/>
	<c:forEach var="list" items="${openlist}">
	{num: ${list.num }, 
	subject_num: ${list.subject_num }, 
	room: '${list.room }', 
	sub_day: '${list.sub_day }', 
	sub_time: '${list.sub_time }', 
	prof_num: ${list.prof_num }}<c:if test="${fn:length(openlist) ne cnt }">,</c:if>
	<c:set var="cnt" value="${cnt+1 }"/>
	</c:forEach>
]