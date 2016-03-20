<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PISTAS</title>
</head>
<body>
	<h2>Listado de pistas</h2>
	<table border="1">
		<thead>
			<tr>
				<th>Activa</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtsList}" var="court">
				<tr>
					<td><c:if test="${court.active == true}">
						<h5>Sí</h5>
					</c:if><c:if test="${court.active == false}">
						<h5>No</h5>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>