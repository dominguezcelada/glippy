<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${shoppingItem.item.name}</h1>
<h3>x ${shoppingItem.quantity}</h3>
<ul>
    <li>${shoppingItem.item.description}</li>
<ul>
    <c:forEach items="${shoppingItem.item.prices}" var="price">
        <li>${price.supermarket}</li>
        <li>${price.price}</li>
        <br>
    </c:forEach>
</ul>
</ul>
TOTAL: ${total}