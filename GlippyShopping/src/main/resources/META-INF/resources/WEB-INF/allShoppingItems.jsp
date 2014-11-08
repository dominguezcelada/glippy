<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Shopping List Items</h1>
<ul>
<c:forEach items="${shoppingItems}" var="shoppingItem">
    <li>${shoppingItem.name}</li>
    <li>${shoppingItem.quantity}</li>
    <li>${shoppingItem.supermarket}</li>
    <li>${shoppingItem.price}</li>
</c:forEach>
</ul>