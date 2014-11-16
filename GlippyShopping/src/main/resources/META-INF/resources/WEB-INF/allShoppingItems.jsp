<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Shopping List Items</h1>
<ul>
<c:forEach items="${shoppingItems}" var="shoppingItem">
    <li>${shoppingItem.name}</li>
    <li>${shoppingItem.quantity}</li>
    <ul>
    <c:forEach items="${shoppingItem.listItems}" var="item">
        <li>${item.price}</li>
        <li>${item.supermarket}</li>
    </c:forEach>
    </ul>
</c:forEach>
</ul>