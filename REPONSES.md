-----| Question 1 – Pourquoi utilise-t-on forward quand le login échoue et sendRedirect quand il réussit ?
--> Échec : On utilise forward pour afficher le formulaire avec le message d'erreur. Il conserve les données de la requête.
--> Succès : On utilise sendRedirect pour envoyer l'utilisateur vers le tableau de bord avec une nouvelle requête.

===========================================================================================================================================

-----| Question 2 – Que se passe-t-il si l'utilisateur appuie sur F5 après avoir ajouté un produit ? Comment s'appelle ce pattern ?
--> Avec forward : Le navigateur peut renvoyer le formulaire POST. Le produit risque d'être ajouté une deuxième fois.
--> Avec redirect : Le navigateur recharge une requête GET. Le produit n'est pas ajouté une deuxième fois.

Ce fonctionnement s'appelle PRG (Post/Redirect/Get) :
~ Post : envoyer le formulaire.
~ Redirect : rediriger vers une autre URL.
~ Get : afficher la liste des produits.

===========================================================================================================================================

-----| Question 3 – Quelle est la différence entre ${produit.nom} et <c:out value="${produit.nom}"/> ?

${produit.nom} : affiche directement le contenu sans échapper automatiquement le HTML. Cela peut provoquer une attaque XSS.
<c:out> : échappe les caractères HTML spéciaux et affiche le contenu comme du texte.

--> <c:out> est plus sécurisé pour afficher les données saisies par l'utilisateur.

===========================================================================================================================================

-----| Question 4 – Quel est l'avantage du filtre par rapport à la vérification dans chaque servlet ? Citez deux autres usages classiques.

Un filtre permet de centraliser les traitements communs sans répéter le même code dans chaque servlet.

Avantages :
--> Éviter la répétition du code.
--> Centraliser la sécurité.
--> Faciliter la maintenance.

Deux autres utilisations :
--> Encodage UTF-8 : gérer correctement les caractères accentués.
--> Journalisation (Logging) : enregistrer les requêtes pour suivre l'activité de l'application.