<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<members>
	<c:forEach var="list" items="${LIST }">
		<member>
			<num>${list.num}</num>
			<name>${list.name}</name>
			<tel>${list.tel}</tel>
			<email>${list.email}</email>
			<dept>${list.dept}</dept>
			<type>${list.type}</type>
		</member>
	</c:forEach>
</members>