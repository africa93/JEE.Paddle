<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear pista</title>
</head>
<body>
<form:form action="create-court" modelAttribute="court">
	<form:hidden path="courtId"/>
	<input type="submit" value="Crear pista">
</form:form>
</body>
</html>