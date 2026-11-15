<%@ page contentType="text/html; charset=UTF-8" %>>

<!DOCTYPE html>
<html>
	<head>
	    <title>Dashboard</title>
	</head>
	<body>
	
	<h1>Bienvenue ${user}</h1>
	
	<a href="${pageContext.request.contextPath}/produits">
	    Gestion des produits
	</a>
	
	<br><br>
	
	<a href="${pageContext.request.contextPath}/logout">
	    Déconnexion
	</a>
	
	</body>
</html>