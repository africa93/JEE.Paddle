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
<title>Escuela de pádel</title>
</head>
<body>
	<h1>Bienvenido a PaddleSport</h1>
	
	<div class="btn-group btn-group-lg">
    <a class="btn btn-secondary" href="<c:url value="/list-courts"/>">Mostrar pistas</a>
    <a class="btn btn-secondary" href="<c:url value="create-court"/>">Crear pistas</a>
  </div>
</body>
</html>