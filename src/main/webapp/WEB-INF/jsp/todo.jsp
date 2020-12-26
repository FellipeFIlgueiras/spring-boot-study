<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
    <div class="container">
        <h3>Add a new Todo for ${username}</h3>
        <form:form method="post" modelAttribute="todo">
            <fieldset class="form-group">
                <form:hidden path="id"/>
                <form:label path="description" for="description">Description</form:label>
                <form:input path="description" type="text" class="form-control" required="required"/>
                <form:errors path="description" cssClass="text-warning" />
            </fieldset>
            <fieldset class="form-group">
                <form:label path="targetDate" for="targetDate">Target Date</form:label>
                <form:input path="targetDate" type="text" class="form-control" required="required"/>
                <form:errors path="targetDate" cssClass="text-warning" />
            </fieldset>
            <button type="submit" class="btn btn-success">Add</button>
        </form:form>
    </div>
<%@ include file="common/footer.jspf" %>