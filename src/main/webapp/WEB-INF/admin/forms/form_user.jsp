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
            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form shadow">
                    <div class="card-body ">
                        <c:if test="${empty user.id}">
                            <title>Add</title>
                        </c:if>
                        <c:if test="${!empty user.id}">
                            <title>Edit</title>
                        </c:if>
                        <legend class="card-title text-center">User
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                            </svg>
                        </legend>

                        <form:form action="/admin/add_user" method="POST" name="user"  class="was-validated">

                            <input type="hidden" name="id" class="form-control" value="${user.id}">

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Login:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="username" minlength="5" class="form-control" value="${user.username}" required>
                                </div>
                            </div>

                            <c:if test="${empty user.id}">

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Password:</label>
                                <div class="col-sm-6">
                                    <input type="password" name="password" minlength="5"  class="form-control" value="${user.password}" required>
                                </div>
                            </div>
                            </c:if>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Role:</label>
                                <div class="col-sm-6">
                                    <select name="selected_role" class="form-select">
                                        <c:forEach var="role" items="${roles}" varStatus="i">
                                            <option value="${role.id}" <c:if test="${!empty user.id && my_role.id==role.id}">
                                            selected
                                            </c:if>>${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <c:if test="${!empty user.id}">

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Linked with entity:</label>
                                    <div class="col-sm-6">
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex1" class="form-check-input" name="selected" value="1" <c:if test="${user.selected}">checked</c:if>>
                                            <label for="sex1" class="form-check-label" >Yes</label>

                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex2" class="form-check-input" name="selected" value="0"  <c:if test="${!user.selected}">checked</c:if>>
                                            <label for="sex2" class="form-check-label">No</label>
                                        </div>
                                    </div>
                                </div>

                            </c:if>

                            <center>
                                <c:if test="${!empty user.id}">
                                    <input type="hidden" name="password" class="form-control" value="${user.password}" required>
                                    <a href="/admin/change_password/${user.id}" class="btn btn_find_all">Change password</a>
                                </c:if>

                                <c:if test="${empty user.id}">
                                    <input type="submit" id="in"  class="btn btn_add" name="add_role" value="Save">
                                </c:if>
                                <c:if test="${!empty user.id}">
                                    <input type="submit" id="edit"  class="btn btn_edit_form" name="edit" value="Save changes">
                                </c:if>



                            </center>


                        </form:form>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="text-center text-justify">
                                <i class="bi bi-exclamation-triangle-fill text-danger pl-2 pt-2 dang  position-relative"></i>
                                <span class="ml-2" style="font-size:16pt;">The login is exists</span>
                            </div>
                            <center><button type="button" class="btn btn-outline-primary mt-3" data-bs-dismiss="modal">Ok</button></center>
                        </div>


                    </div>
                </div>
            </div>

        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>

<c:if test="${message!=null}">
    <script type="text/javascript">
        var myModal = new bootstrap.Modal(document.getElementById("staticBackdrop"), {});
        document.onreadystatechange = function () {
            myModal.show();
        };
    </script>
</c:if>