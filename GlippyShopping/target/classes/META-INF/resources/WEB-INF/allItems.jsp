<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Items</h1>
<ul>
<c:forEach items="${items}" var="item">
    <li>${item.name}</li>
    <li>${item.description}</li>
    <ul>
    <c:forEach items="${item.prices}" var="price">
        <li>${price.supermarket}</li>
        <li>${price.price}</li>
        <br>
    </c:forEach>
    </ul>
</c:forEach>
</ul>