<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Show XML</title>
	<link href="css/bootstrap/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="css/codemirror/codemirror.css" type="text/css" rel="stylesheet">
	<script src="js/jquery/jquery-1.11.0.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/codemirror/codemirror.js"></script>
	<script src="js/codemirror/mode/xml/xml.js"></script>
	<script src="js/show-xml.js"></script>
</head>
<body class="container" style="padding-top:60px;">
	<div class="panel panel-success">
  		<div class="panel-heading">XML Content</div>
  		<div class="panel-body" style="padding:0px;">
			<textarea disabled="disabled" name="xmlContent" id="xmlContent"></textarea>
  		</div>
	</div>
	
</body>
</html>
