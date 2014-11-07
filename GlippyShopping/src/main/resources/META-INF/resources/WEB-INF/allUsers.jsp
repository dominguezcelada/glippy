<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Users</h1>
<ul>
<c:forEach items="${users}" var="user">
    <li>${user.name}</li>
    <li>${user.lastname}</li>
</c:forEach>
</ul>