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
                                <c:if test="${empty diverPro.id}">
                                    <title>Add</title>
                                </c:if>
                                <c:if test="${!empty diverPro.id}">
                                    <title>Edit</title>
                                </c:if>
                                <legend class="card-title text-center">Instructor

                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-person" viewBox="0 0 16 16">
                                        <path d="M12 1a1 1 0 0 1 1 1v10.755S12 11 8 11s-5 1.755-5 1.755V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z"/>
                                        <path d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                                    </svg>

                                </legend>
                                <form:form action="/admin/add_diverPro" method="POST" name="diverPro"  class="was-validated">
                                    <input type="hidden" name="user_id" value="${diverPro.user.id}">
                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">ID:</label>
                                        <div class="col-sm-6">
                                            <input class="form-control " type="text" pattern="[0-9]{10}" name="id" value="${diverPro.id}" required>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Surname:</label>
                                        <div class="col-sm-6">
                                            <input type="text" name="surname" class="form-control" value="${diverPro.surname}" required>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Name:</label>
                                        <div class="col-sm-6">
                                            <input type="text" name="name" class="form-control" value="${diverPro.name}" maxlength="100" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">MidName:</label>
                                        <div class="col-sm-6">
                                            <input type="text" name="middle_name" class="form-control" value="${diverPro.middle_name}" required>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Birth Date:</label>
                                        <div class="col-sm-6">
                                            <input type="date" name="date_of_birth" class="form-control" value="${diverPro.date_of_birth}" maxlength="20" data-inputmask="'alias': 'date','placeholder': '*'" required>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Sex:</label>
                                        <div class="col-sm-6">
                                            <div class="form-check form-check-inline">
                                                <input type="radio" id="sex1" class="form-check-input" name="sex" value="0" required  <c:if test="${ (!empty diverPro.id) && (diverPro.sex== 0)}">checked</c:if>>
                                                <label for="sex1" class="form-check-label" >M</label>

                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input type="radio" id="sex2" class="form-check-input" name="sex" value="1" required  <c:if test="${ (!empty diverPro.id) && (diverPro.sex== 1)}">checked</c:if>>
                                                <label for="sex2" class="form-check-label">F</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Phone:</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" type="tel" pattern="0[0-9]{9}" name="telephone_number" value="<c:if test="${!empty diverPro.id}">0${diverPro.telephone_number}</c:if>" required>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">E-mail:</label>
                                        <div class="col-sm-6">
                                            <input type="email"  name="email" class="form-control  no-validate" value="${diverPro.email}">
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Adress:</label>
                                        <div class="col-sm-6">
                                            <input type="text"  name="address" class="form-control" value="${diverPro.address}" required>
                                        </div>
                                    </div>


                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Specialization:</label>
                                        <div class="col-sm-6">
                                            <select name="selected_spec" class="form-select">
                                                <c:if test="${!empty diverPro.specialization.id}">
                                                    <option value="${diverPro.specialization.id}" >${diverPro.specialization.name}</option>
                                                </c:if>
                                                <c:forEach var="specialization" items="${specializations}" varStatus="i">
                                                    <option value="${specialization.id}">${specialization.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <label class="col-sm-6 col-form-label ln">Date start of work:</label>
                                        <div class="col-sm-6">
                                            <input type="date" name="dateWhenStartWorking" class="form-control" value="${diverPro.dateWhenStartWorking}" maxlength="20" data-inputmask="'alias': 'date','placeholder': '*'" required>
                                        </div>
                                    </div>




                                    <center>
                                        <c:if test="${!empty diverPro.id}">
                                            <a href="/admin/${diverPro.id}/set_user_for_diverPro/" class="btn btn_find_all">Change user</a>
                                        </c:if>
                                        <c:if test="${empty diverPro.id}">
                                            <input type="submit" id="in"  class="btn btn_add" name="add_diverPro" value="Create">
                                        </c:if>
                                        <c:if test="${!empty diverPro.id}">
                                            <input type="submit" id="edit"  class="btn btn_edit_form" name="edit" value="Update">
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
                                        <span class="ml-2" style="font-size:16pt;">The diver is already exist</span>
                                    </div>
                                    <center><button type="button" class="btn btn-outline-primary mt-3" data-bs-dismiss="modal">OK</button></center>
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