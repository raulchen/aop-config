<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>hehe</title>
</head>
<body>

	<form id="addForm" method="post" action="add">
		在哪儿切入：
		<input type="text" name="pointcut" id="pointcut" style="width:308px" /> <br/>
		切入什么：
		<select name="beanId" id="beanId" style="width:308px">
			<c:forEach items="${beans}" var="b">
				<option value="${b.id}">${b.name}</option>
			</c:forEach>
		</select>
		 <br/>
		方法:
		<input type="text" name="method" id="method"  style="width:308px"/>
		<br/>
		切入类型：
		<select name="pointType" id="pointType" style="width:308px">
			<option value="before">before</option>
			<option value="after">after</option>
			<option value="after_throwing">after_throwing</option>
			<option value="after_returning">after_returning</option>
			<option value="around">around</option>
		</select>
		<br/>
		<input type="submit" />
	</form>


</body>
</html>
