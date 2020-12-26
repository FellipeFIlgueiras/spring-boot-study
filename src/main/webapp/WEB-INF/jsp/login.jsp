<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <label style="color: red">${errorMessage}</label>
    <form method="post">
        <input type="text" name="username" id="username">
        <label for="username">Username</label>
        <input type="password" name="password" id="password">
        <label for="password">Password</label>
        <input type="submit">
    </form>
</div>
<%@ include file="common/footer.jspf" %>