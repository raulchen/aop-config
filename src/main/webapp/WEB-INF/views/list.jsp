<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>hehe</title>
</head>
<body>
	<a href="add">添加</a>
	
	<table border=1>
		<tr>
			<td>Pointcut</td>
			<td>PointType</td>
			<td>Bean</td>
			<td>method</td>
		</tr>
		<c:forEach items="${notices}" var="n">
			<tr>
				<td>${n.pointcut.expression }</td>
				<td>${n.pointType.name }</td>
				<td>${n.aspect.refBean.name }</td>
				<td>${n.method }</td>
				<td><a href="delete?id=${n.id }">删除</a></td>
				<td>
					<c:choose>
						<c:when test="${n.state }"><a href="update?id=${n.id}&state=false">禁用</a></c:when>
						<c:otherwise><a href="update?id=${n.id}&state=true">启用</a></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>

	</table>
	
	<a href="generate">生成配置文件</a> 