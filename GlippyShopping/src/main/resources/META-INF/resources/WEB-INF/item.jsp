<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${item.name}</h1>
<h3>by ${item.description}</h3>
<ul>
    <c:forEach items="${item.prices}" var="price">
        <li>${price.supermarket}</li>
        <li>${price.price}</li>
        <br>
    </c:forEach>
</ul>