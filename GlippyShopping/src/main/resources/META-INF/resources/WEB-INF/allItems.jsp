<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Items</h1>
<ul>
<c:forEach items="${items}" var="shoppingItem">
    <li>${shoppingItem.name}</li>
    <li>${shoppingItem.description}</li>
    <li>${shoppingItem.prices}</li>
</c:forEach>
</ul>