<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>List</title>
	<link href="css/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet">
</head>
<body class="container" style="padding-top:60px;">
	<div class="panel panel-success">
  		<div class="panel-heading">AOP list</div>
		<table class="table table-condensed">
			<tr>
				<th>Pointcut</th>
				<th>PointType</th>
				<th>Bean</th>
				<th>method</th>
				<th>删除</th>
				<th>禁用/启用</th>
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
		<div class="panel-footer">
			<a href="add"><span class="glyphicon glyphicon-plus"></span> 添加</a>
			<a href="content" target="_blank" style="margin-left:20px;"><span class="glyphicon glyphicon-play"></span> 生成配置文件</a> 
		</div>
	</div>
	