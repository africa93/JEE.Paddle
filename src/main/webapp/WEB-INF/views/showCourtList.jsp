<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/jumbotron.css' />"
	rel="stylesheet">
<title>Listado de pistas</title>
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
	<h2>Listado de pistas</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="table-responsive">
					<table class="table table-hover table-condensed">
						<thead>
							<tr>
								<th>Id</th>
								<th>Estado</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${courtsList}" var="court">
								<tr>
									<td>${court.courtId}</td>
									<td><c:if test="${court.active == true}">
											<h5>Activa</h5>
										</c:if> <c:if test="${court.active == false}">
											<h5>Desactiva</h5>
										</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="<c:url value='/static/js/bootstrap.js' />"></script>
</body>
</html>