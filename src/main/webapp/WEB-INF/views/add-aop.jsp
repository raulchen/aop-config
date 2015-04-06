<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Add AOP</title>
	<link href="css/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="css/add-aop.css" type="text/css" rel="stylesheet">
	<script src="js/jquery/jquery-1.11.0.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/add-aop.js"></script>
</head>
<body class="container" style="padding-top:60px;">
	<div class="panel panel-success">
  		<div class="panel-heading">Add AOP</div>
  		<div class="panel-body">
    		<form class="form-horizontal" id="addForm" method="post" action="add">
    			<div class="col-sm-6">
					<div class="form-group">
						<label for="pointcut" class="col-sm-12">在哪儿切入</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="pointcut" id="pointcut" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10">
							<div id="methods-container">
								<ul class="list-group" id="methods">
							  	</ul>
						  	</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label for="beanId" class="col-sm-12">切入类名</label>
						<div class="col-sm-10">
							<select name="beanId" id="beanId" class="form-control">
								<c:forEach items="${beans}" var="b">
									<option value="${b.id}">${b.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="method" class="col-sm-12">切入方法名</label>
						<div class="col-sm-10">
							<input type="text" name="method" id="method" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="pointType" class="col-sm-12">切入类型</label>
						<div class="col-sm-10">
							<select name="pointType" id="pointType" class="form-control">
								<option value="before">before</option>
								<option value="after">after</option>
								<option value="after_throwing">after-throwing</option>
								<option value="after_returning">after-returning</option>
								<option value="around">around</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-10">
							<input type="submit" class="btn btn-success" class="form-control"/>
						</div>
					</div>
				</div>
			</form>
  		</div>
	</div>
	
</body>
</html>
