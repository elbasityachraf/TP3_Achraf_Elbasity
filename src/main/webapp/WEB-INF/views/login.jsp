<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
	<head>
	    <title>Connexion</title>
	</head>

	<body>
	
	<h2>Connexion</h2>
	
	<c:if test="${not empty erreur}">
	    <p style="color:red">
	        ${erreur}
	    </p>
	</c:if>
	
	<form method="post"
	      action="${pageContext.request.contextPath}/login">
	
	    Username :
	    <input type="text"
	           name="username"
	           value="${username}">
	
	    <br><br>
	
	    Password :
	    <input type="password"
	           name="password">
	
	    <br><br>
	
	    <button type="submit">
	        Se connecter
	    </button>
	
	</form>
	
	</body>
</html>