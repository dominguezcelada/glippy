<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Shopping Lists</h1>
<ul>
<c:forEach items="${shoppingLists}" var="shoppingList">
    <li>${shoppingList.name}</li>
</c:forEach>
</ul>