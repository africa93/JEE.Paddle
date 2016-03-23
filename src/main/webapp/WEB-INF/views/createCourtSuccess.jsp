<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pista creada con éxito</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="icon-bar"></span><span class="icon-bar"></span> <span
					class="icon-bar"></span>Paddle
			</button>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/home"/>">Home</a></li>
				<li><a href="<c:url value="/list-courts"/>">Mostrar pistas</a></li>
				<li><a href="<c:url value="create-court"/>">Crear pistas</a></li>
			</ul>

		</div>
	</div>
	</nav>
	<h1>Pista creada con éxito</h1>
</body>
</html>