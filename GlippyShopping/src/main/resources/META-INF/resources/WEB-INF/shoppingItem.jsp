<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${shoppingItem.name}</h1>
<ul>
    <li>${shoppingItem.description}</li>
    <li>${shoppingItem.supermarket}</li>
    <li>${shoppingItem.price}</li>
    <li>${shoppingItem.quantity}</li>
</ul>
TOTAL: ${total}