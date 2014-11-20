<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${shoppingList.id}</h1>
<h1>${shoppingList.name}</h1>
<h3>by ${shoppingList.username}</h3>
<ul>
<c:forEach items="${shoppingList.listItems}" var="shoppingItem">
    <li>${shoppingItem.item.id}</li>
    <li>${shoppingItem.quantity} x ${shoppingItem.item.name}</li>
</c:forEach>
<h2>TOTAL: ${total}</h2>
</ul>