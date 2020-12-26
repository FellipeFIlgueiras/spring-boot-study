<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

    <div class="container">
        <h1>Todos of: ${username}</h1>
        <div class="button"><a href="/add-todo">Add new Todo</a></div>
        <br>

        <table class="table table-striped">
            <caption>Your todos are</caption>
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is it Done?</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
                        <td>${todo.done}</td>
                        <td><a type="button" href="/delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
                        <td><a type="button" href="/update-todo?id=${todo.id}" class="btn btn-warning">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<%@ include file="common/footer.jspf" %>