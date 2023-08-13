<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="../template/head.jsp" />

<body class="d-flex flex-column min-vh-100">
<jsp:include page="../template/header.jsp" />
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../template/nav.jsp" />
        <content class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <h2 class="pt-3">Users</h2>
            <c:if test="${!userList.isEmpty()}">
            <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">

                <table class="table tableFixHead table-striped">
                    <thead>
                    <tr>
                        <th>Name<a href="/admin/user/1" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Role</th>
                        <th>Status<a href="/admin/user/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${userList}" varStatus="i">
                            <tr>
                                <td><p align="left">${user.username}</p></td>
                                <td> <p align="top"><c:forEach items="${user.roles}" var="role">${role.name} </c:forEach></p></td>
                                <td>
                                    <c:if test="${user.selected}"><i class="bi bi-link text-success"></i> </c:if>
                                    <c:if test="${!user.selected}"><i class="bi bi-dash text-danger"></i></c:if>
                                </td>
                                <td><a href="/admin/${user.username}/edit_user/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                    <a href="/admin/${user.id}/delete_user" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            </c:if>
            <c:if test="${userList.isEmpty()}">
                <h2><em><center>No User</center></em></h2>
            </c:if>

            <center>
                <button onclick="document.location = '/admin/add_user';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>

</html>