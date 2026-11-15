<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Formulaire produit</title>
</head>

<body>
    <c:choose>
        <c:when test="${empty produit.id}">
            <h1>Nouveau produit</h1>
        </c:when>

        <c:otherwise>
            <h1>Modifier le produit</h1>
        </c:otherwise>

    </c:choose>

    <form method="post" action="${ctx}/produits">
        <c:if test="${not empty produit.id}">

            <input type="hidden"
                   name="id"
                   value="${produit.id}"/>

        </c:if>
      
        <label>Nom :</label>
        <input type="text"
               name="nom"
               maxlength="50"
               value="<c:out value='${produit.nom}'/>"/>

        <c:if test="${not empty erreurs.nom}">
            <p style="color:red">
                <c:out value="${erreurs.nom}"/>
            </p>
        </c:if>

        <br><br>

        <label>Description :</label>
        <textarea name="description"
                  maxlength="255"><c:out value="${produit.description}"/></textarea>

        <c:if test="${not empty erreurs.description}">
            <p style="color:red">
                <c:out value="${erreurs.description}"/>
            </p>
        </c:if>

        <br><br>

        <label>Prix :</label>
        <input type="number"
               name="prix"
               step="0.01"
               min="0.01"
               value="${produit.prix}"/>

        <c:if test="${not empty erreurs.prix}">
            <p style="color:red">
                <c:out value="${erreurs.prix}"/>
            </p>
        </c:if>

        <br><br>

        <label>Stock :</label>
        <input type="number"
               name="stock"
               min="0"
               value="${produit.stock}"/>

        <c:if test="${not empty erreurs.stock}">
            <p style="color:red">
                <c:out value="${erreurs.stock}"/>
            </p>
        </c:if>

        <br><br>

        <button type="submit">
            Enregistrer
        </button>

    </form>

    <br>

    <a href="${ctx}/produits">
        Retour à la liste
    </a>

</body>
</html>