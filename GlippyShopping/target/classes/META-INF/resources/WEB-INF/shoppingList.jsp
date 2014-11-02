<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${shoppingList.name}</h1>
<ul>
<c:forEach items="${shoppingList.items}" var="item">
    <li>${item.quantity} x ${item.name}</li>
</c:forEach>
</ul>